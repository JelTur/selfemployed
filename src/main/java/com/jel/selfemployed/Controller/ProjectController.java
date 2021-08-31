package com.jel.selfemployed.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ProjectController {
    @GetMapping("/projects/list")
    public String showProjects(){
        return "projects/project_list";
    }

    @GetMapping("/projects/add")
    public String showAddProject(){
        return "projects/project_add";
    }
}