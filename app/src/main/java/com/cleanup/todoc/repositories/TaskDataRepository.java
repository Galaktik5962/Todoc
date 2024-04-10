package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.TaskDao;
import com.cleanup.todoc.models.Task;

import java.util.List;

/**
 * Repository class for managing Task data.
 * This class acts as an intermediary between the ViewModel and the data source (TaskDao).
 *
 * It contains methods for retrieving task data from the DAO (TaskDao).
 * Data is encapsulated in LiveData objects to enable reactive updates and observe changes from ViewModels.
 */
public class TaskDataRepository {

    /**
     * DAO used to access task data.
     */
    private final TaskDao taskDao;

    /**
     * Constructor for the TaskDataRepository class.
     *
     * @param taskDao The DAO used to access task data.
     */
    public TaskDataRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    /**
     * Retrieves tasks associated with a specific project.
     *
     * @param projectId The ID of the project to retrieve tasks for.
     * @return A LiveData object containing a list of tasks associated with the specified project.
     */
    // --- GET ---
    public LiveData<List<Task>> getTasks(long projectId) { return this.taskDao.getTasks(projectId); }

    /**
     * Retrieves all tasks.
     *
     * @return A LiveData object containing a list of all tasks.
     */
    public LiveData<List<Task>> getAllTasks() { return this.taskDao.getAllTasks(); }

    /**
     * Inserts a new task into the database.
     *
     * @param task The task to be inserted.
     */
    // --- CREATE ---
    public void createTask(Task task) { taskDao.insertTask(task); }

    /**
     * Deletes a task from the database.
     *
     * @param taskId The ID of the task to delete.
     */
    // --- DELETE ---
    public void deleteTask(long taskId) { taskDao.deleteTask(taskId); }

    /**
     * Updates a task in the database.
     *
     * @param task The task to update.
     */
    // --- UPDATE ---
    public void updateTask(Task task) { taskDao.updateTask(task); }
}
