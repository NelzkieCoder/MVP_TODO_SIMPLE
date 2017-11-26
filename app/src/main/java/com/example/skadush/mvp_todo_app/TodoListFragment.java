package com.example.skadush.mvp_todo_app;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.skadush.mvp_todo_app.Adapters.ToDoListAdapter;
import com.example.skadush.mvp_todo_app.Listeners.ITodoAdapterListener;
import com.example.skadush.mvp_todo_app.Model.Task;
import com.example.skadush.mvp_todo_app.Presenter.TaskFragmentPresenter;
import com.example.skadush.mvp_todo_app.Repository.ITaskInteractor;
import com.example.skadush.mvp_todo_app.Repository.TaskRepository;
import com.example.skadush.mvp_todo_app.View.ITaskFragmentView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends Fragment implements ITodoAdapterListener, ITaskFragmentView {


    private static final String TAG = TodoListFragment.class.getName();
    RecyclerView recyclerView;
    ToDoListAdapter toDoListAdapter;

    ITaskInteractor taskrepository;
    TaskFragmentPresenter taskFragmentPresenter;
    public TodoListFragment() {
        // Required empty public constructor
        taskrepository = new TaskRepository();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_blank, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        toDoListAdapter = new ToDoListAdapter(new ArrayList<Task>(),getActivity());
        toDoListAdapter.setiTodoAdapterListener(this);
        recyclerView.setAdapter(toDoListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        taskFragmentPresenter = new TaskFragmentPresenter(this,taskrepository);
        taskFragmentPresenter.loadTasks();

        return view;
    }


    @Override
    public void OnItemClick(int position) {
        Toast.makeText(getContext(), "" +  position, Toast.LENGTH_SHORT).show();
    }


    public void refreshAdapter(){
        toDoListAdapter.notifyDataSetChanged();
    }



    @Override
    public void displayAllTask(List<Task> taskList) {
        Log.d(TAG, "displayTaskList: " + taskList.size());
        toDoListAdapter.displayAllTaskList(taskList);
    }
}


