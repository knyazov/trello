# trello
The task of the final project is to develop a Trello application on Spring Boot. Trello is a task manager where you can create cards and then change their statuses.

Project entities (tables):

 

TaskCategories.java

- Long id;

- Stringname;

 

Folders.java

- Long id;

- Stringname;

- List<TaskCategories> categories; // Many To Many

 

Tasks.java

- Long id;

- String title;

- String description; // TEXT

- int status; // 0 - todo, 1 - intest, 2 - done, 3 - failed

- Folders folder; // Many To One

 

Comments.java

- Long id;

- String comment;

- task task;

 

Even though we use 4 entities (5 tables), the task of the project is very easy.

Functionally, we should be able to:

 

1. Add/change/delete task category (TaskCategories.java).

2. Add/change/delete folders (Folders.java). Moreover, when deleting a folder, you will need to delete all the tasks of this folder, and clear the category and folder bundle. Just create a function in the repository that removes links by id.

3. Create/modify/delete a task. Moreover, you can’t just add a task, you must definitely select a folder (enter it) and create it inside it.

4. Change the status of a task. By default, the task status will be 0 (todo). If you change the task status to 3 (done) or 4 (failed), then it turns out that you have finally closed it, that is, after that it will not be possible to comment or change the content. It will only be available in view mode.

5. It will be possible to comment on the problem.

 

The main page will look like this, in it we will see a list of all folders.
![image](https://user-images.githubusercontent.com/98006856/178963989-d01797b0-2a46-473c-abe8-d32a739bb411.png)

By clicking on + New Folder we can add a folder:
![image](https://user-images.githubusercontent.com/98006856/178964060-f0f75327-20dc-4ed9-96f3-cfcc9448723e.png)
Once added, we can view the folder in detail:
![image](https://user-images.githubusercontent.com/98006856/178964175-a0f886f3-b215-44dc-adcd-7f531150d51f.png)
By clicking + Add Category we can add a category for the folder:![image](https://user-images.githubusercontent.com/98006856/178964251-e27aa627-b229-4a21-bb8f-9f7df947a5f8.png)
Well, after adding by clicking on the cross (x), you can delete them:
By clicking on + New Task we can add a task:![image](https://user-images.githubusercontent.com/98006856/178964340-61e6b046-4339-4bb9-83d8-9fa8cb477111.png)

After adding, the default status will be - 0 (TO DO).

Further, you can change the status of the task in the detailed view.

 

These are the basic required functionality of your project. In principle, if you want to add some features, feel free to.
