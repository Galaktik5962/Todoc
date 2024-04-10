package com.cleanup.todoc.models;

import android.content.ContentValues;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * Represents the database table "Project" in the application.
 * This class defines the structure of a project and provides methods for accessing and manipulating project data.
 */
@Entity

public class Project {

    /**
     * Unique identifier of the project.
     */
    @PrimaryKey

    private long id;

    /**
     * Name of the project.
     */
    @NonNull
    private String name;

    /**
     * Color of the project.
     */
    @ColorInt
    private int color;

    /**
     * Constructor for creating a Project object.
     *
     * @param id    Unique identifier of the project.
     * @param name  Name of the project.
     * @param color Color of the project.
     */
    public Project(long id, @NonNull String name, @ColorInt int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    /**
     * Retrieves an array containing all available projects.
     * This method is used only for database initialization.
     *
     * @return An array of Project objects representing all available projects.
     */
    @NonNull
    public static Project[] getAllProjects() {
        return new Project[]{
                new Project(1L, "Projet Tartampion", 0xFFEADAD1),
                new Project(2L, "Projet Lucidia", 0xFFB4CDBA),
                new Project(3L, "Projet Circus", 0xFFA3CED2),
        };
    }

    /**
     * Retrieves the project with the specified identifier.
     *
     * @param id The unique identifier of the project.
     * @return The Project object with the specified identifier, or null if no project is found.
     */
    @Nullable
    public static Project getProjectById(long id) {
        for (Project project : getAllProjects()) {
            if (project.id == id)
                return project;
        }
        return null;
    }

    /**
     * Converts the Project object into a ContentValues object for use in the database.
     * The attributes of the Project object are inserted as key-value pairs into ContentValues.
     *
     * @return ContentValues containing the values of the attributes of the Project object.
     */
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", getId());
        contentValues.put("name", getName());
        contentValues.put("color", getColor());
        return contentValues;
    }


    // --- GETTERS ---
    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Provides a textual representation of the project.
     *
     * @return The name of the project.
     */
    @Override
    @NonNull
    public String toString() {
        return getName();
    }

    public int getColor() {
        return color;
    }


    // --- SETTERS ---
    public void setId(long id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
