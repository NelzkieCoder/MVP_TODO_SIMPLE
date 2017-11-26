package com.example.skadush.mvp_todo_app.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skadush.mvp_todo_app.Listeners.ITodoAdapterListener;
import com.example.skadush.mvp_todo_app.Model.Task;
import com.example.skadush.mvp_todo_app.R;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by skadush on 26/11/17.
 */

public class ToDoListAdapter extends Adapter {

    private static final String TAG = ToDoListAdapter.class.getName();
    ITodoAdapterListener iTodoAdapterListener;

    List<Task> taskList = new RealmList<>();
    Context context;

    public ToDoListAdapter(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_todolist_layout,parent,false);

        MyViewHolderTodoList myViewHolderTodoList = new MyViewHolderTodoList(view);

        return myViewHolderTodoList;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolderTodoList myViewHolderTodoList = (MyViewHolderTodoList) holder;
        myViewHolderTodoList.textView.setText(taskList.get(position).getName());

    }

    public void setiTodoAdapterListener(ITodoAdapterListener iTodoAdapterListener) {
        this.iTodoAdapterListener = iTodoAdapterListener;
    }


    @Override
    public int getItemCount() {

        return taskList.size();
    }


    class MyViewHolderTodoList extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView textView;

        public MyViewHolderTodoList(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txtAdapterTask);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iTodoAdapterListener.OnItemClick(getAdapterPosition());
        }
    }

    public void displayAllTaskList(List<Task> taskList){
        this.taskList = taskList;

        notifyDataSetChanged();
    }
}
