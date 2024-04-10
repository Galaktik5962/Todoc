package com.cleanup.todoc.ui;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.models.Project;
import com.cleanup.todoc.models.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * ViewModel class responsible for managing data and operations related to tasks and projects.
 */
public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository taskDataSource;

    private final ProjectDataRepository projectDataSource;

    /**
     * Executor for performing operations outside the main (UI) thread.
     */
    private final Executor executor;

    // DATA
    @Nullable

    private LiveData<List<Project>> projects;


    /**
     * Constructs a new TaskViewModel with the specified data repositories and executor.
     *
     * @param taskDataSource    The data repository for tasks.
     * @param projectDataSource The data repository for projects.
     * @param executor          The executor for performing background operations.
     */
    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    /**
     * Initializes the ViewModel by loading projects data if it's not already loaded.
     */
    public void init() {
        if (this.projects != null) {
            return;
        }
        projects = projectDataSource.getAllProjects();
    }

    // -------------
    // FOR PROJECT
    // -------------

    /**
     * Retrieves LiveData containing the list of all projects.
     *
     * @return LiveData containing the list of all projects.
     */
    public LiveData<List<Project>> getAllProjects() {
        return this.projects;
    }


    // -------------
    // FOR TASK
    // -------------

    /**
     * Retrieves LiveData containing the list of all tasks.
     *
     * @return LiveData containing the list of all tasks.
     */
    public LiveData<List<Task>> getAllTasks() {
        return taskDataSource.getAllTasks();
    }

    /**
     * Creates a new task.
     *
     * @param task The task to be created.
     */
    public void createTask(Task task) {
        executor.execute(() -> taskDataSource.createTask(task));
    }

    /**
     * Deletes the task with the specified ID.
     *
     * @param taskId The ID of the task to be deleted.
     */
    public void deleteTask(long taskId) {
        executor.execute(() -> taskDataSource.deleteTask(taskId));
    }

    /**
     * Updates the specified task.
     *
     * @param task The task to be updated.
     */
    public void updateTask(Task task) {
        executor.execute(() -> taskDataSource.updateTask(task));
    }

}
