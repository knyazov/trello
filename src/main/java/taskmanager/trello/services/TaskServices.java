package taskmanager.trello.services;


import taskmanager.trello.entities.Folders;
import taskmanager.trello.entities.TaskCategories;
import taskmanager.trello.entities.Tasks;

import java.util.List;

public interface TaskServices {
    void addFolder(Folders folder);
    List<Folders> getAllFolders();
    List<TaskCategories> getAllCategories();
    List<Tasks> getAllTasks();
    List<Tasks> getTasksByFolderId(Long folderId);
    Folders getFolder(Long id);
    void assignCategory(Long folderId, Long categoryId);
    void addTask(Long folderId, Tasks task);
    void deleteCat(Long folderId, Long catId);
}
