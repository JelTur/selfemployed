package com.jel.selfemployed.Controller;

import com.jel.selfemployed.Model.Task;
import com.jel.selfemployed.Repository.TaskRepository;
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

import java.util.Optional;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/list")
    public String showTasks(Model model) {
        Iterable<Task> tasks = taskRepository.findAll();

        model.addAttribute("tasks", tasks);

        return "tasks/task_list";
    }

    @GetMapping("/tasks/add")
    public String showAddTask() {
        return "tasks/task_add";
    }

    @PostMapping("/tasks/add")
    public RedirectView submitAddTask(
            @RequestParam(name = "task_title", required = true) String taskTitle,
            @RequestParam(name = "task_project", required = false) String taskProject,
            @RequestParam(name = "task_client", required = false) String taskClient,
            @RequestParam(name = "task_status", required = false) String taskStatus,
            @RequestParam(name = "task_start_date", required = false) String taskStartDate
    ) {
        Task task = new Task();
        task.setTaskTitle(taskTitle);
        task.setTaskProject(taskProject);
        task.setTaskClient(taskClient);
        task.setTaskStatus(taskStatus);
        task.setTaskStartDate(taskStartDate);

        taskRepository.save(task);

        return new RedirectView("/tasks/list");
    }

    @GetMapping("/tasks/edit/{id}")
    public String showEditTask(@PathVariable int id, Model model) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (!taskOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Task task = taskOptional.get();
        model.addAttribute("task", task);

        return "tasks/task_edit";
    }

    @PostMapping("/tasks/edit/{id}")
    public RedirectView submitEditTask(
            @PathVariable int id,
            @RequestParam(name = "task_title", required = true) String taskTitle,
            @RequestParam(name = "task_project", required = false) String taskProject,
            @RequestParam(name = "task_client", required = false) String taskClient,
            @RequestParam(name = "task_status", required = false) String taskStatus,
            @RequestParam(name = "task_start_date", required = false) String taskStartDate
    ) {
        Task task = new Task();
        task.setId(id);
        task.setTaskTitle(taskTitle);
        task.setTaskProject(taskProject);
        task.setTaskClient(taskClient);
        task.setTaskStatus(taskStatus);
        task.setTaskStartDate(taskStartDate);

        taskRepository.save(task);

        return new RedirectView("/tasks/edit/" + id);
    }
}