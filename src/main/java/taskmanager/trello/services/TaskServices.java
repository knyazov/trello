package taskmanager.trello.services;


import taskmanager.trello.entities.Folders;

import java.util.List;

public interface TaskServices {
    void addFolder(Folders folder);
    List<Folders> getAllFolders();
}
