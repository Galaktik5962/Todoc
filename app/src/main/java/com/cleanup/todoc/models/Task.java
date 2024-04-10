package com.cleanup.todoc.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Comparator;


/**
 * Represents the database table "Task" in the application.
 * This class defines the structure of a task and provides methods for accessing and manipulating task data.
 */
@Entity(foreignKeys = @ForeignKey(entity = Project.class,
        parentColumns = "id", // id of the project
        childColumns = "projectId")) // Attribute projectId of the Task class (Id of the project associated with the task)
// The projectId field is part of the task table (source) and points to the id of the project table (destination of the foreign key)

// onDelete = CASCADE : if a project is deleted, all tasks associated with this project will be deleted

public class Task {

    /**
     * Unique identifier of the task.
     */
    @PrimaryKey(autoGenerate = true)

    private long id;

    /**
     * ID of the project associated with the task.
     */
    private long projectId;

    /**
     * Name of the task.
     */
    @NonNull
    private String name;

    /**
     * Creation timestamp of the task.
     */
    private long creationTimestamp;

    /**
     * Indicates whether the task is selected.
     */
    private Boolean isSelected = false;

    /**
     * Constructor for creating a Task object.
     *
     * @param projectId         The ID of the project associated with the task.
     * @param name              The name of the task.
     * @param creationTimestamp The creation timestamp of the task.
     */
    public Task(long projectId, @NonNull String name, long creationTimestamp) {
        this.projectId = projectId;
        this.name = name;
        this.creationTimestamp = creationTimestamp;
    }

    // For testing
    public Task(long projectId, long taskId, @NonNull String name, long creationTimestamp) {
        this.setProjectId(projectId);
        this.setId(taskId);
        this.setName(name);
        this.setCreationTimestamp(creationTimestamp);
    }


    // --- GETTERS ---
    public long getId() {
        return id;
    }

    /**
     * Returns the project associated with the task.
     *
     * @return The project associated with the task, or null if not found.
     */
    @Nullable
    public Project getProject() {
        return Project.getProjectById(projectId);
    }

    public long getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public Boolean getSelected() {
        return isSelected;
    }


    // --- SETTERS ---
    public void setId(long id) {
        this.id = id;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }


    /**
     * Comparator to sort task from A to Z
     */
    public static class TaskAZComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return left.name.compareTo(right.name);
        }
    }

    /**
     * Comparator to sort task from Z to A
     */
    public static class TaskZAComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return right.name.compareTo(left.name);
        }
    }

    /**
     * Comparator to sort task from last created to first created
     */
    public static class TaskRecentComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (right.creationTimestamp - left.creationTimestamp);
        }
    }

    /**
     * Comparator to sort task from first created to last created
     */
    public static class TaskOldComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (left.creationTimestamp - right.creationTimestamp);
        }
    }
}
