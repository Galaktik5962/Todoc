package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.models.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    // --- GET PROJECT ---
    public LiveData<Project> getProject (long projectId) { return this.projectDao.getProject(projectId); }

    public LiveData<List<Project>> getAllProjects () { return this.projectDao.getAllProjects(); }
}
