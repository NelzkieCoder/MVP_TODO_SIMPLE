package com.example.skadush.mvp_todo_app.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by skadush on 25/11/17.
 */

public class Task extends RealmObject {
    String name;

    @PrimaryKey
    long ID = System.currentTimeMillis();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
