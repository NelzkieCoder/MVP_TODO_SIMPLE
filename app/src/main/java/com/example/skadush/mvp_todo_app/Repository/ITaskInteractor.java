package com.example.skadush.mvp_todo_app.Repository;

import com.example.skadush.mvp_todo_app.Model.Task;

import java.util.List;

/**
 * Created by skadush on 26/11/17.
 */

public interface ITaskInteractor {
    boolean SaveTask(Task task);
    List<Task> getAllTaskFromRealm();
    void closeRealm();

}
