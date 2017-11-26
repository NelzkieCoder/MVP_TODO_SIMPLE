package com.example.skadush.mvp_todo_app;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.skadush.mvp_todo_app.Model.Task;
import com.example.skadush.mvp_todo_app.Presenter.TaskPresenter;
import com.example.skadush.mvp_todo_app.Repository.ITaskInteractor;
import com.example.skadush.mvp_todo_app.Repository.TaskRepository;
import com.example.skadush.mvp_todo_app.View.ITaskAcitivtyView;

public class MainActivity extends AppCompatActivity implements ITaskAcitivtyView {

    ITaskInteractor taskrepository;
    TaskPresenter taskPresenter;
    TodoListFragment todoFragment = new TodoListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddFragment();

        taskrepository = new TaskRepository();
        taskPresenter = new TaskPresenter(this, taskrepository);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    void AddFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.rootLayout,todoFragment);
        fragmentTransaction.commit();
    }

    public void AddNewTask(View view) {

        boolean wrapInScrollView = true;
        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("Sample")
                .customView(R.layout.custom_addtask_dialog, wrapInScrollView)
                .show();
        View v = dialog.getCustomView();
        final EditText editText = v.findViewById(R.id.custom_edit_task);
        Button button = v.findViewById(R.id.custom_dialog_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateTask(editText.getText().toString());
                dialog.dismiss();
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        taskPresenter.closeRealm();
    }

    @Override
    public void refreshList() {

        todoFragment.refreshAdapter();
    }




    private void CreateTask(String taskMessage) {
        Task task = new Task();
        task.setName(taskMessage);
        taskPresenter.CreateTask(task);
    }



}
