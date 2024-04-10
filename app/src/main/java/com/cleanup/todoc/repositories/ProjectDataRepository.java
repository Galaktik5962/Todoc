package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.models.Project;

import java.util.List;

/**
 * Repository class for managing Project data.
 * This class acts as an intermediary between the ViewModel and the data source (ProjectDao).
 *
 * It contains methods for retrieving project data from the DAO (ProjectDao).
 * Data is encapsulated in LiveData objects to enable reactive updates and observe changes from ViewModels.
 */
public class ProjectDataRepository {

    /**
     * DAO used to access project data.
     */
    private final ProjectDao projectDao;

    /**
     * Constructor for the ProjectDataRepository class.
     *
     * @param projectDao The DAO used to access project data.
     */
    public ProjectDataRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * Retrieves a project based on its ID.
     *
     * @param projectId The ID of the project to retrieve.
     * @return A LiveData object containing the project corresponding to the specified ID.
     */
    // --- GET PROJECT ---
    public LiveData<Project> getProject (long projectId) { return this.projectDao.getProject(projectId); }

    /**
     * Retrieves all projects.
     *
     * @return A LiveData object containing a list of all projects.
     */
    public LiveData<List<Project>> getAllProjects () { return this.projectDao.getAllProjects(); }
}
