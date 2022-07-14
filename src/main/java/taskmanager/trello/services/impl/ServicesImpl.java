package taskmanager.trello.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskmanager.trello.entities.Comments;
import taskmanager.trello.entities.Folders;
import taskmanager.trello.entities.TaskCategories;
import taskmanager.trello.entities.Tasks;
import taskmanager.trello.repositories.CommentsRepository;
import taskmanager.trello.repositories.FoldersRepository;
import taskmanager.trello.repositories.TaskCategoriesRepository;
import taskmanager.trello.repositories.TasksRepository;
import taskmanager.trello.services.TaskServices;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicesImpl implements TaskServices {
    @Autowired
    private FoldersRepository foldersRepository;

    @Autowired
    private TaskCategoriesRepository categoriesRepository;

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private CommentsRepository commentsRepository;

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
    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    @Override
    public List<Tasks> getTasksByFolderId(Long folderId) {
        return tasksRepository.findTasksByFolderId(folderId);
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

    @Override
    public void addTask(Long folderId, Tasks task) {
        if (foldersRepository.existsById(folderId)) {
            task.setStatus(0);
            task.setFolder(foldersRepository.findById(folderId).orElse(null));
            tasksRepository.save(task);
        }
    }

    @Override
    public void deleteCat(Long folderId, Long catId) {
        if (foldersRepository.existsById(folderId)) {
            Folders folder = getFolder(folderId);
            List<TaskCategories> categories = folder.getCategories();
            if (categories == null) {
                categories = new ArrayList<>();
            }
            TaskCategories category = categoriesRepository.findById(catId).orElse(null);
            categories.remove(category);
            folder.setCategories(categories);
            addFolder(folder);
        }
    }

    @Override
    public Tasks getTask(Long taskId) {
        return tasksRepository.findById(taskId).orElse(null);
    }

    @Override
    public void addComment(Long taskId, String comment, Long commentId) {
        if (tasksRepository.existsById(taskId)) {
            Comments comments = commentsRepository.findById(commentId).orElse(null);
            if (comments != null) {
                comments.setComment(comment);
            } else {
                comments = new Comments();
                comments.setComment(comment);
                comments.setTask(getTask(taskId));
            }
            commentsRepository.save(comments);
        }
    }

    @Override
    public void updateTask(Long taskId, Tasks task) {
        if (tasksRepository.existsById(taskId)) {
            Tasks newTask = getTask(taskId);
            newTask.setTitle(task.getTitle());
            newTask.setDescription(task.getDescription());
            newTask.setStatus(task.getStatus());
        }
    }

    @Override
    public void deleteTask(Long taskId) {
        if (tasksRepository.existsById(taskId)) {
            tasksRepository.deleteById(taskId);
        }
    }

    @Override
    public Comments getComment(Long taskId) {
        if (tasksRepository.existsById(taskId)) {
            return commentsRepository.findCommentsByTaskId(taskId);
        } else
            return null;
    }
}
