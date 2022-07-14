package taskmanager.trello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taskmanager.trello.entities.Folders;
import taskmanager.trello.entities.TaskCategories;
import taskmanager.trello.entities.Tasks;
import taskmanager.trello.repositories.FoldersRepository;
import taskmanager.trello.services.TaskServices;

@Controller
public class HomeController {
    @Autowired
    private TaskServices taskServices;

    @GetMapping(value = "/")
    private String index(Model model) {
        model.addAttribute("folders", taskServices.getAllFolders());
        return "index";
    }

    @PostMapping(value = "newFolder")
    private String newFolder(@ModelAttribute(name = "folderObj") Folders folderObj) {
        taskServices.addFolder(folderObj);
        return "redirect:/";
    }

    @GetMapping(value = "/details/folders/{id}")
    private String detailsFolders(@PathVariable(name = "id") Long folderId,
                                  Model model) {
        model.addAttribute("folder", taskServices.getFolder(folderId));
        model.addAttribute("categories", taskServices.getAllCategories());
        model.addAttribute("tasks", taskServices.getTasksByFolderId(folderId));
        return "detailsFolder";
    }

    @PostMapping(value = "/assignCategory")
    private String assignCategory(@RequestParam(name = "folderId") Long folderId,
                                  @RequestParam(name = "categoryId") Long categoryId) {
        taskServices.assignCategory(folderId, categoryId);
        return "redirect:/details/folders/" + folderId;
    }

    @PostMapping(value = "/newTask")
    private String newTask(@RequestParam(name = "folderId") Long folderId,
                           @ModelAttribute(name = "taskObj") Tasks task) {
        taskServices.addTask(folderId, task);
        return "redirect:/details/folders/" + folderId;
    }

    @PostMapping(value = "/unAssignCat")
    private String unAssignCategory(@RequestParam(name = "catId") Long catId,
                                    @RequestParam(name = "folderId") Long folderId) {
        taskServices.deleteCat(folderId, catId);
        return "redirect:/details/folders/" + folderId;
    }

    @GetMapping(value = "/detailsOfTasks/{taskId}")
    private String detailsOfTasks(@PathVariable(name = "taskId") Long taskId,
                                  Model model) {
        model.addAttribute("folder", taskServices.getFolder((taskServices.getTask(taskId)).getFolder().getId()));
        model.addAttribute("categories", taskServices.getAllCategories());
        model.addAttribute("task", taskServices.getTask(taskId));
        model.addAttribute("comments", taskServices.getComment(taskId));
        return "detailsTask";
    }

    @PostMapping(value = "/upDateTask")
    private String upDateTask(@RequestParam(name = "taskId") Long taskId,
                              @ModelAttribute(name = "taskObj") Tasks taskObj,
                              @RequestParam(name = "comment") String comment,
                              @RequestParam(name = "commentId") Long commentId) {
        taskServices.updateTask(taskId, taskObj);
        taskServices.addComment(taskId, comment, commentId);
        return "redirect:/details/folders/" + taskServices.getTask(taskId).getFolder().getId();
    }

    @GetMapping(value = "/deleteTask/{taskId}/{folderId}")
    private String deleteTask(@PathVariable(name = "taskId") Long taskId,
                              @PathVariable(name = "folderId") Long folderId){
        taskServices.deleteTask(taskId);
        return "redirect:/details/folders/" + folderId;
    }
}