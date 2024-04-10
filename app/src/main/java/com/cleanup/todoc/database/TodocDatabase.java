package com.cleanup.todoc.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.models.Project;
import com.cleanup.todoc.models.Task;

/**
 * Database class representing the main database for the Todoc application.
 * This class extends RoomDatabase and serves as the entry point for accessing DAO instances.
 * It defines the entities that compose the database and provides a singleton instance to access the database.
 */
@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)

public abstract class TodocDatabase extends RoomDatabase {

    /**
     * Singleton instance of the TodocDatabase class.
     *
     * This class implements the Singleton design pattern to ensure that only one instance
     * of the TodocDatabase class is created throughout the application lifecycle. This is
     * achieved by using a volatile static field to hold the single instance, along with a
     * synchronized block to handle thread safety during instance creation.
     */
    // --- SINGLETON ---
    private static volatile TodocDatabase INSTANCE;

    // --- DAO ---

    /**
     * Provides access to the ProjectDao for performing CRUD operations on Project entities.
     *
     * @return The ProjectDao instance.
     */
    public abstract ProjectDao projectDao();

    /**
     * Provides access to the TaskDao for performing CRUD operations on Task entities.
     *
     * @return The TaskDao instance.
     */
    public abstract TaskDao taskDao();

    /**
     * Retrieves the singleton instance of TodocDatabase.
     *
     * @param context The application context.
     * @return The singleton instance of TodocDatabase.
     */
    // --- INSTANCE ---
    public static TodocDatabase getInstance(Context context) { // Always work with the same instance of the database
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "MyDatabase.db") // Read the TodocDatabase class and create a database (MyDatabase.db = file with all the information, only one file)
                            .allowMainThreadQueries() // Allow queries on the main thread
                            .addCallback(prepopulateDatabase()) // Calling the prepopulateDatabase() function after the creation of the database (allows pre-filling the database with initial data)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Prepopulates the database with a list of projects when the database is created.
     *
     * @return A callback that prepopulates the database with a list of projects.
     */
    private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                Project[] projects = Project.getAllProjects(); // Retrieve the hard-coded list of projects
                for (Project project : projects) {
                    ContentValues contentValues = project.toContentValues(); // Conversion of the project object into a ContentValues object (a set of key-value pairs)
                    db.insert("Project", OnConflictStrategy.IGNORE, contentValues); // Insertion of data (projects) into the database
                }
            }
        };
    }
}
