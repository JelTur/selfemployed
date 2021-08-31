package com.jel.selfemployed.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
    @GetMapping("/tasks/list")
    public String showTasks(){
        return "tasks/task_list";
    }

    @GetMapping("/tasks/add")
    public String showAddTasks(){
        return "tasks/task_add";
    }
}
