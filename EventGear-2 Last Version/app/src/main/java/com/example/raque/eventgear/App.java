package com.example.raque.eventgear;


import android.app.Application;

/**
 * Created by Sanchez on 03/01/17.
 */

public class App extends Application {

    private DataBase db;

    @Override
    public void onCreate(){
        super.onCreate();
        this.db= new DataBase(this.getApplicationContext());
    }

    public DataBase getDb() {
        return this.db;
    }
}

