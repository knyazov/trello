package taskmanager.trello.services;


import taskmanager.trello.entities.Folders;
import taskmanager.trello.entities.TaskCategories;

import java.util.List;

public interface TaskServices {
    void addFolder(Folders folder);
    List<Folders> getAllFolders();
    List<TaskCategories> getAllCategories();
    Folders getFolder(Long id);
    void assignCategory(Long folderId, Long categoryId);
}
