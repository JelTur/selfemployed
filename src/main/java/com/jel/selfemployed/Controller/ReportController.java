package com.jel.selfemployed.Controller;

import com.jel.selfemployed.Model.TaskTime;
import com.jel.selfemployed.Repository.TaskTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;

@Controller
public class ReportController {
    @Autowired
    private TaskTimeRepository taskTimeRepository;

    @GetMapping("/reports/report1")
    public String showReportsByTask(
        @RequestParam(name = "date_from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateFrom,
        @RequestParam(name = "date_to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateTo,
        Model model
    ) {
        if (dateFrom != null && dateTo != null) {
            HashMap<String, HashMap<String, Integer>> report = new HashMap<>();

            Iterable<TaskTime> taskTimes = taskTimeRepository.findAllBySessionDateBetween(dateFrom, dateTo);
            for (TaskTime taskTime : taskTimes) {
                String projectName = taskTime.getTask().getProject().getClient().getCompanyName() + " - " + taskTime.getTask().getProject().getProjectTitle();
                String taskName = taskTime.getTask().getTaskTitle();
                if (!report.containsKey(projectName)) {
                    report.put(projectName, new HashMap<>());
                }

                HashMap<String, Integer> projectMap = report.get(projectName);

                int hours = projectMap.getOrDefault(taskName, 0);
                projectMap.put(taskName, hours + taskTime.getSessionHours());
            }
            model.addAttribute("report", report);
            model.addAttribute("date_from", dateFrom);
            model.addAttribute("date_to", dateTo);
        }

        return "reports/report1";
    }
}