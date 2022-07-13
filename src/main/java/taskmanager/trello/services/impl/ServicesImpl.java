package taskmanager.trello.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskmanager.trello.entities.Folders;
import taskmanager.trello.entities.TaskCategories;
import taskmanager.trello.repositories.FoldersRepository;
import taskmanager.trello.repositories.TaskCategoriesRepository;
import taskmanager.trello.services.TaskServices;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicesImpl implements TaskServices {
    @Autowired
    private FoldersRepository foldersRepository;

    @Autowired
    private TaskCategoriesRepository categoriesRepository;

    @Override
    public void addFolder(Folders folder) {
        foldersRepository.save(folder);
    }

    @Override
    public List<Folders> getAllFolders() {
        return foldersRepository.findAll();
    }

    @Override
    public List<TaskCategories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public Folders getFolder(Long id) {
        return foldersRepository.findById(id).orElse(null);
    }

    @Override
    public void assignCategory(Long folderId, Long categoryId) {
        if (foldersRepository.existsById(folderId)) {
            Folders folder = foldersRepository.findById(folderId).orElse(null);
            List<TaskCategories> categories = folder.getCategories();
            if (categories == null) {
                categories = new ArrayList<>();
            }
            TaskCategories category = categoriesRepository.findById(categoryId).orElse(null);
            categories.add(category);
            folder.setCategories(categories);
            addFolder(folder);
        }
    }

}
