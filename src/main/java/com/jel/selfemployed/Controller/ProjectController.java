package com.jel.selfemployed.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

    @GetMapping("/")
    public String showIndex(){
        return "index";
    }
}
