package com.example.skadush.mvp_todo_app.Repository;

import com.example.skadush.mvp_todo_app.Model.Task;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

/**
 * Created by skadush on 26/11/17.
 */

public class TaskRepository implements ITaskInteractor {
    Realm realm;

    public TaskRepository() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public boolean SaveTask(final Task task) {
        try{
            if(task != null){
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.insertOrUpdate(task);
                    }
                });
                return  true;
            }else{
                throw new NullPointerException();
            }

        }catch (NullPointerException e){
            return false;
        }



    }

    @Override
    public List<Task> getAllTaskFromRealm() {
        return realm.where(Task.class).findAllSorted("ID", Sort.DESCENDING);
    }

    @Override
    public void closeRealm() {
        realm.close();
    }

}
