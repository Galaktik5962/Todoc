package com.cleanup.todoc.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.models.Task;

import java.util.List;

/**
 * Data Access Object (DAO) interface for accessing Task entities from the database.
 * This interface provides methods for CRUD operations related to Task entities.
 * It is used by the database to generate the queries to interact with the Task table.
 */
@Dao

public interface TaskDao {

    /**
     * Inserts or replaces a Task entity into the database.
     * If a task with the same ID already exists in the database, it will be replaced.
     *
     * @param task The task entity to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createTask(Task task);

    /**
     * Retrieves a LiveData object containing a list of Task entities with the specified project ID from the database.
     *
     * @param projectId The ID of the project to retrieve tasks for.
     * @return A LiveData object containing a list of Task entities with the specified project ID, or null if no tasks with the given project ID exist.
     */
    @Query("SELECT * FROM Task WHERE projectId = :projectId")

    LiveData<List<Task>> getTasks(long projectId);

    /**
     * Retrieves a LiveData object containing a list of all Task entities stored in the database.
     *
     * @return A LiveData object containing a list of all Task entities stored in the database.
     */
   @Query("SELECT * FROM Task")

   LiveData<List<Task>> getAllTasks();

    /**
     * Inserts a new task into the database.
     *
     * @param task The task to be inserted into the database.
     * @return The ID of the newly inserted row in the database.
     */
    @Insert

    long insertTask(Task task);

    /**
     * Updates an existing task in the database.
     *
     * @param task The task to be updated in the database.
     * @return The number of rows affected by the update operation.
     */
    @Update

    int updateTask(Task task);

    /**
     * Deletes a task with the specified ID from the database.
     *
     * @param taskId The ID of the task to be deleted from the database.
     * @return The number of rows affected by the delete operation.
     */
    @Query("DELETE FROM Task WHERE id = :taskId")

    int deleteTask(long taskId);

}
