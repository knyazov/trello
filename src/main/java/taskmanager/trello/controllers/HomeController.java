package taskmanager.trello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taskmanager.trello.entities.Folders;
import taskmanager.trello.entities.TaskCategories;
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

    @GetMapping(value = "/details/folders/{id}")
    private String detailsFolders(@PathVariable(name = "id") Long folderId,
                                  Model model){
        model.addAttribute("folder", taskServices.getFolder(folderId));
        model.addAttribute("categories", taskServices.getAllCategories());
        return "detailsFolder";
    }

    @PostMapping(value = "/assignCategory")
    private String assignCategory(@RequestParam(name = "folderId") Long folderId,
                                  @RequestParam(name = "categoryId") Long categoryId){
        taskServices.assignCategory(folderId, categoryId);
        return "redirect:/details/folders/"+folderId;
    }
}
