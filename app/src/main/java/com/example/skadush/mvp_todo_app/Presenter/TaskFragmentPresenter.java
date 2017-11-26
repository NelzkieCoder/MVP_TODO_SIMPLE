package com.example.skadush.mvp_todo_app.Presenter;

import android.util.Log;

import com.example.skadush.mvp_todo_app.Model.Task;
import com.example.skadush.mvp_todo_app.Repository.ITaskInteractor;

import com.example.skadush.mvp_todo_app.View.ITaskFragmentView;

import java.util.List;

/**
 * Created by skadush on 26/11/17.
 */

public class TaskFragmentPresenter implements IFragmentPresenter {
    private static final String TAG = TaskFragmentPresenter.class.getName();
    ITaskFragmentView taskFragmentView;
    ITaskInteractor taskRepository;

    public TaskFragmentPresenter(ITaskFragmentView taskFragmentView, ITaskInteractor taskRepository) {
        this.taskFragmentView = taskFragmentView;
        this.taskRepository = taskRepository;
    }

    @Override
    public void loadTasks() {
        List<Task> tasks  = null;
        try{

            tasks = taskRepository.getAllTaskFromRealm();
            Log.d(TAG, "loadTasks: " + tasks.size());
            if(!tasks.isEmpty()){
                taskFragmentView.displayAllTask(tasks);
            }

        }catch (Exception e){
            Log.d(TAG, "loadTasks: " + e.toString());
        }

    }
}
