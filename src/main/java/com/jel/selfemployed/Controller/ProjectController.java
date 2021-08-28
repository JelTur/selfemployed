package com.jel.selfemployed.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {
    @GetMapping("/projects")
    public String showProjects(){
        return "projects/project_list";
    }

}
