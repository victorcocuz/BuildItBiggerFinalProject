package com.udacity.gradle.builditbigger.backend;

import com.example.jokejavalibrary.JokesClass;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    private JokesClass jokesClass;

    public MyBean() {jokesClass = new JokesClass(); }

    public String getData() {
        return jokesClass.getJoke();
    }

    public void setData(String data) {
        myData = jokesClass.getJoke();
    }

}