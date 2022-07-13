package taskmanager.trello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import taskmanager.trello.entities.Folders;
import taskmanager.trello.repositories.FoldersRepository;
import taskmanager.trello.services.TaskServices;

@Controller
public class HomeController {
    @Autowired
    private TaskServices taskServices;

    @GetMapping(value = "/")
     private String index(Model model){
        model.addAttribute("folders", taskServices.getAllFolders());
        return "index";
    }

    @PostMapping(value = "newFolder")
    private String newFolder(@ModelAttribute(name = "folderObj")Folders folderObj){
        taskServices.addFolder(folderObj);
        return "redirect:/";
    }
}
