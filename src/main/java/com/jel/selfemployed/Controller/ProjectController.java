package com.jel.selfemployed.Controller;

import com.jel.selfemployed.Model.Client;
import com.jel.selfemployed.Model.Project;
import com.jel.selfemployed.Repository.ClientRepository;
import com.jel.selfemployed.Repository.ProjectRepository;
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
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/projects/list")
    public String showProjects(Model model) {
        Iterable<Project> projects = projectRepository.findAll();

        model.addAttribute("projects", projects);

        return "projects/project_list";
    }

    @GetMapping("/projects/add")
    public String showAddProject(Model model) {
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);

        return "projects/project_add";
    }

    @PostMapping("/projects/add")
    public RedirectView submitAddProject(
            @RequestParam(name = "project_title", required = true) String projectTitle,
            @RequestParam(name = "client_id", required = false) int clientId,
            @RequestParam(name = "project_start_date", required = false) String projectStartDate
    ) {
        Client client = clientRepository.findById(clientId).get();

        Project project = new Project();
        project.setProjectTitle(projectTitle);
        project.setProjectStartDate(projectStartDate);
        project.setClient(client);

        projectRepository.save(project);

        return new RedirectView("/projects/list");
    }

    @GetMapping("/projects/edit/{id}")
    public String showEditProject(@PathVariable int id, Model model) {
        Optional<Project> projectOptional = projectRepository.findById(id);

        if (!projectOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);

        Project project = projectOptional.get();
        model.addAttribute("project", project);

        return "projects/project_edit";
    }

    @PostMapping("/projects/edit/{id}")
    public RedirectView submitEditProject(
            @PathVariable int id,
            @RequestParam(name = "project_title", required = true) String projectTitle,
            @RequestParam(name = "project_client", required = false) String projectClient,
            @RequestParam(name = "project_start_date", required = false) String projectStartDate
    ) {
        Optional<Project> projectOptional = projectRepository.findById(id);

        if (!projectOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Project project = projectOptional.get();
        project.setProjectTitle(projectTitle);
        project.setProjectStartDate(projectStartDate);

        projectRepository.save(project);

        return new RedirectView("/projects/edit/" + id);
    }
}