package com.jel.selfemployed.Controller;

import com.jel.selfemployed.Model.Task;
import com.jel.selfemployed.Model.Project;
import com.jel.selfemployed.Model.TaskTime;
import com.jel.selfemployed.Repository.TaskRepository;
import com.jel.selfemployed.Repository.ProjectRepository;
import com.jel.selfemployed.Repository.TaskTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.Optional;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskTimeRepository taskTimeRepository;

    @GetMapping("/tasks/list")
    public String showTasks(Model model) {
        Iterable<Task> tasks = taskRepository.findAll();

        model.addAttribute("tasks", tasks);

        return "tasks/task_list";
    }

    @GetMapping("/tasks/add")
    public String showAddTask(Model model) {
        Iterable<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "tasks/task_add";
    }

    @PostMapping("/tasks/add")
    public RedirectView submitAddTask(
            @RequestParam(name = "task_title", required = true) String taskTitle,
            @RequestParam(name = "project_id", required = false) int projectId,
            @RequestParam(name = "task_status", required = false) String taskStatus,
            @RequestParam(name = "activity_type", required = false) String activityType
    ) {
        Project project = projectRepository.findById(projectId).get();

        Task task = new Task();
        task.setTaskTitle(taskTitle);
        task.setTaskStatus(taskStatus);
        task.setTaskStartDate(new Date());
        task.setActivityType(activityType);
        task.setProject(project);

        taskRepository.save(task);

        System.out.println(java.time.LocalDate.now());

        return new RedirectView("/tasks/list");
    }

    @GetMapping("/tasks/edit/{id}")
    public String showEditTask(@PathVariable int id, Model model) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (!taskOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Iterable<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);

        Task task = taskOptional.get();
        model.addAttribute("task", task);

        return "tasks/task_edit";
    }

    @PostMapping("/tasks/edit/{id}")
    public RedirectView submitEditTask(
            @PathVariable int id,
            @RequestParam(name = "task_title", required = true) String taskTitle,
            @RequestParam(name = "task_status", required = false) String taskStatus,
            @RequestParam(name = "activity_type", required = false) String activityType,
            @RequestParam(name = "project_id", required = false) int projectId
    ) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Project project = projectRepository.findById(projectId).get();

        Task task = taskOptional.get();
        task.setId(id);
        task.setTaskTitle(taskTitle);
        task.setTaskStatus(taskStatus);
        task.setActivityType(activityType);
        task.setProject(project);

        taskRepository.save(task);

        return new RedirectView("/tasks/list/");
    }

    @PostMapping("/tasks/add_hours/{id}")
    public RedirectView submitAddTaskHours(
            @PathVariable int id,
            @RequestParam(name = "hours", required = true) int hours
    ) {
        Optional<TaskTime> taskTimeOptional = taskTimeRepository.findByTaskIdAndSessionDate(id, new Date());

        TaskTime taskTime;
        if (taskTimeOptional.isPresent()) {
            taskTime = taskTimeOptional.get();
            taskTime.setSessionHours(hours + taskTime.getSessionHours());
        } else {
            Task task = taskRepository.findById(id).get();

            taskTime = new TaskTime();
            taskTime.setSessionHours(hours);
            taskTime.setTask(task);
            taskTime.setSessionDate(new Date());
        }

        taskTimeRepository.save(taskTime);

        return new RedirectView("/tasks/list");
    }


}