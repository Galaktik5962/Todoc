package com.cleanup.todoc.injection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.TaskViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Factory class for creating ViewModels.
 * This class implements the ViewModelProvider.Factory interface to provide customized creation logic for ViewModels.
 * It ensures that ViewModels are created with the required dependencies.
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    /**
     * Data repository for tasks, providing access to task data.
     */
   private final TaskDataRepository taskDataSource;

    /**
     * Data repository for projects, providing access to project data.
     */
   private final ProjectDataRepository projectDataSource;

    /**
     * Executor used for background tasks.
     */
   private final Executor executor;

    /**
     * Singleton instance of the ViewModelFactory.
     */
   private static ViewModelFactory factory;

    /**
     * Returns the singleton instance of the ViewModelFactory.
     * If the instance does not exist, it is created.
     * @param context the application context
     * @return the singleton instance of the ViewModelFactory
     */
   public static ViewModelFactory getInstance (Context context) {

         if (factory == null) {
              synchronized (ViewModelFactory.class) {
                if (factory == null) {
                     factory = new ViewModelFactory(context);
                }
              }
         }
         return factory;
   }

    /**
     * Constructs a new ViewModelFactory with the required dependencies.
     *
     * @param context The application context.
     */
    public ViewModelFactory(Context context) {
        TodocDatabase database = TodocDatabase.getInstance(context);
         this.taskDataSource = new TaskDataRepository(database.taskDao());
         this.projectDataSource = new ProjectDataRepository(database.projectDao());
         this.executor = Executors.newSingleThreadExecutor();
    }

    /**
     * Creates a ViewModel of the specified class.
     *
     * @param modelClass The class of the ViewModel to create.
     * @param <T>        The type of the ViewModel.
     * @return A new instance of the specified ViewModel.
     * @throws IllegalArgumentException if the ViewModel class is unknown.
     */
    @Override
    @NonNull

    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(taskDataSource, projectDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
