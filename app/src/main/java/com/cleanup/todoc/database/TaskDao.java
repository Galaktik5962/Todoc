package com.cleanup.todoc.database;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.models.Task;

import java.util.List;

@Dao

public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createTask(Task task);

    @Query("SELECT * FROM Task WHERE projectId = :projectId")

    LiveData<List<Task>> getTasks(long projectId);

   @Query("SELECT * FROM Task")

   LiveData<List<Task>> getAllTasks();

    @Insert

    long insertTask(Task task);

    @Update

    int updateTask(Task task);

    @Query("DELETE FROM Task WHERE id = :taskId")

    int deleteTask(long taskId);

}
