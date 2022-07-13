package taskmanager.trello.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskmanager.trello.entities.Folders;
import taskmanager.trello.repositories.FoldersRepository;
import taskmanager.trello.services.TaskServices;

import java.util.List;

@Service
public class ServicesImpl implements TaskServices {
    @Autowired
    private FoldersRepository foldersRepository;

    @Override
    public void addFolder(Folders folder) {
        foldersRepository.save(folder);
    }

    @Override
    public List<Folders> getAllFolders() {
        return foldersRepository.findAll();
    }

}
