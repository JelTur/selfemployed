package com.jel.selfemployed.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
    @GetMapping("/clients/list")
    public String showClients(){
        return "clients/client_list";
    }

    @GetMapping("/clients/add")
    public String showAddClient(){
        return "clients/client_add";
    }
}
