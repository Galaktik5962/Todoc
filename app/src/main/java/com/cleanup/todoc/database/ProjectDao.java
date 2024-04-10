package com.cleanup.todoc.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.models.Project;

import java.util.List;

/**
 * Data Access Object (DAO) interface for accessing Project entities from the database.
 * This interface provides methods for CRUD operations related to Project entities.
 * It is used by the database to generate the queries to interact with the Project table.
 */
@Dao

public interface ProjectDao {

    /**
     * Inserts or replaces a Project entity into the database.
     * If a project with the same ID already exists in the database, it will be replaced.
     *
     * @param project The project entity to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);


    /**
     * Retrieves a LiveData object containing a Project entity with the specified project ID from the database.
     *
     * @param projectId The ID of the project to retrieve.
     * @return A LiveData object containing the Project entity with the specified ID, or null if no project with the given ID exists.
     */

    // 1. getProject called with projectId=1 for example
    // 2. DAO (Room) will execute the SQL query SELECT * FROM Project WHERE id = 1
    // 3. SQLite database will return the project with id 1 to the DAO (retrieves the raw row from the Project table)
    // 4. DAO will return the Project entity to the repository, via a LiveData object
    @Query("SELECT * FROM Project WHERE id = :projectId")

    LiveData<Project> getProject(long projectId);

    /**
     * Retrieves a LiveData object containing a list of all Project entities stored in the database.
     *
     * @return A LiveData object containing a list of all Project entities stored in the database.
     */
    @Query("SELECT * FROM Project")

    LiveData<List<Project>> getAllProjects();
}
