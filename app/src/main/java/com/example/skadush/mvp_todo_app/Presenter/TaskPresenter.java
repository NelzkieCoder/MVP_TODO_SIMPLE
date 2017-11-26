package com.example.skadush.mvp_todo_app.Presenter;

import com.example.skadush.mvp_todo_app.Model.Task;
import com.example.skadush.mvp_todo_app.Repository.ITaskInteractor;
import com.example.skadush.mvp_todo_app.View.ITaskAcitivtyView;

/**
 * Created by skadush on 26/11/17.
 */

public class TaskPresenter implements IPresenter {

    ITaskAcitivtyView taskAcitivtyView;
    ITaskInteractor taskRepository;

    public TaskPresenter(ITaskAcitivtyView taskAcitivtyView, ITaskInteractor taskRepository) {
        this.taskAcitivtyView = taskAcitivtyView;
        this.taskRepository = taskRepository;
    }

    @Override
    public void CreateTask(Task task) {
        if(taskRepository.SaveTask(task)){
            taskAcitivtyView.refreshList();
        }
    }


    public void closeRealm(){
        taskRepository.closeRealm();
    }
}
