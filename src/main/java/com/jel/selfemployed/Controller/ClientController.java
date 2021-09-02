package com.jel.selfemployed.Controller;

import com.jel.selfemployed.Model.Client;
import com.jel.selfemployed.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients/list")
    public String showClients(Model model){
        Iterable<Client> clients = clientRepository.findAll();

        model.addAttribute("clients", clients);

        return "clients/client_list";
    }

    @GetMapping("/clients/add")
    public String showAddClient(){
        return "clients/client_add";
    }

    @PostMapping("/clients/add")
    public RedirectView submitAddClient(
            @RequestParam(name = "company_name", required = true) String companyName,
            @RequestParam(name = "agreement_number", required = true) String agreementNumber
    ){
        Client client = new Client();
        client.setCompanyName(companyName);
        client.setAgreementNumber(agreementNumber);

        clientRepository.save(client);

        return new RedirectView("/clients/list");
    }
}
