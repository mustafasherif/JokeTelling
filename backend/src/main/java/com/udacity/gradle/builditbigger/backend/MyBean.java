package com.udacity.gradle.builditbigger.backend;


import com.example.jokeslib.JokesClass;

/** The object model for the data we are sending through endpoints */
public class MyBean {
    public String getData() {
        return JokesClass.getJoke();
    }

}