package com.jel.selfemployed.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
    @GetMapping("/clients")
    public String showClients(){
        return "clients/client_list";
    }

}
