package com.jel.selfemployed.Controller;

import com.jel.selfemployed.Model.Client;
import com.jel.selfemployed.Repository.ClientRepository;
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
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients/list")
    public String showClients(Model model) {
        Iterable<Client> clients = clientRepository.findAll();

        model.addAttribute("clients", clients);

        return "clients/client_list";
    }

    @GetMapping("/clients/add")
    public String showAddClient() {
        return "clients/client_add";
    }

    @PostMapping("/clients/add")
    public RedirectView submitAddClient(
            @RequestParam(name = "company_name", required = true) String companyName,
            @RequestParam(name = "agreement_number", required = false) String agreementNumber,
            @RequestParam(name = "company_description", required = false) String companyDescription,
            @RequestParam(name = "contact_first_name", required = false) String contactFirstName,
            @RequestParam(name = "contact_last_name", required = false) String contactLastName,
            @RequestParam(name = "contact_email", required = false) String contactEmail,
            @RequestParam(name = "contact_phone_number", required = false) String contactPhoneNumber,
            @RequestParam(name = "company_address", required = false) String companyAddress,
            @RequestParam(name = "company_address2", required = false) String companyAddress2,
            @RequestParam(name = "company_city", required = false) String companyCity,
            @RequestParam(name = "company_country", required = false) String companyCountry,
            @RequestParam(name = "company_zip", required = false) String companyZip
    ) {
        Client client = new Client();
        client.setCompanyName(companyName);
        client.setAgreementNumber(agreementNumber);
        client.setCompanyDescription(companyDescription);
        client.setContactFirstName(contactFirstName);
        client.setContactLastName(contactLastName);
        client.setContactEmail(contactEmail);
        client.setContactPhoneNumber(contactPhoneNumber);
        client.setCompanyAddress(companyAddress);
        client.setCompanyAddress2(companyAddress2);
        client.setCompanyCity(companyCity);
        client.setCompanyCountry(companyCountry);
        client.setCompanyZip(companyZip);

        clientRepository.save(client);

        return new RedirectView("/clients/list");
    }

    @GetMapping("/clients/edit/{id}")
    public String showEditClient(@PathVariable int id, Model model) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (!clientOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Client client = clientOptional.get();
        model.addAttribute("client", client);

        return "clients/client_edit";
    }

    @PostMapping("/clients/edit/{id}")
    public RedirectView submitEditClient(
            @PathVariable int id,
            @RequestParam(name = "company_name", required = true) String companyName,
            @RequestParam(name = "agreement_number", required = false) String agreementNumber,
            @RequestParam(name = "company_description", required = false) String companyDescription,
            @RequestParam(name = "contact_first_name", required = false) String contactFirstName,
            @RequestParam(name = "contact_last_name", required = false) String contactLastName,
            @RequestParam(name = "contact_email", required = false) String contactEmail,
            @RequestParam(name = "contact_phone_number", required = false) String contactPhoneNumber,
            @RequestParam(name = "company_address", required = false) String companyAddress,
            @RequestParam(name = "company_address2", required = false) String companyAddress2,
            @RequestParam(name = "company_city", required = false) String companyCity,
            @RequestParam(name = "company_country", required = false) String companyCountry,
            @RequestParam(name = "company_zip", required = false) String companyZip
    ) {
        Client client = new Client();
        client.setId(id);
        client.setCompanyName(companyName);
        client.setAgreementNumber(agreementNumber);
        client.setCompanyDescription(companyDescription);
        client.setContactFirstName(contactFirstName);
        client.setContactLastName(contactLastName);
        client.setContactEmail(contactEmail);
        client.setContactPhoneNumber(contactPhoneNumber);
        client.setCompanyAddress(companyAddress);
        client.setCompanyAddress2(companyAddress2);
        client.setCompanyCity(companyCity);
        client.setCompanyCountry(companyCountry);
        client.setCompanyZip(companyZip);

        clientRepository.save(client);

        return new RedirectView("/clients/edit/" + id);
    }
}
