package com.jel.selfemployed.Controller;

import com.jel.selfemployed.Model.IAccountingDay;
import com.jel.selfemployed.Repository.TaskTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class AccountingController {
    @Autowired
    private TaskTimeRepository taskTimeRepository;

    @GetMapping("/accounting")
    public String showReportsByTask(
            @RequestParam(name = "date_from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateFrom,
            @RequestParam(name = "date_to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateTo,
            @RequestParam(name = "rate", required = false) Float rate,
            Model model
    ) {
        if (dateFrom != null && dateTo != null) {

            List<IAccountingDay> days = taskTimeRepository.findHoursSumBetwwenDates(dateFrom, dateTo);

            int totalHours = 0;
            for (IAccountingDay d: days) {
                totalHours = totalHours + d.getHours();
            }

            model.addAttribute("days", days);
            model.addAttribute("date_from", dateFrom);
            model.addAttribute("date_to", dateTo);
            model.addAttribute("rate", rate);
            model.addAttribute("total_hours", totalHours);
        }

        return "accounting/accounting";
    }
}