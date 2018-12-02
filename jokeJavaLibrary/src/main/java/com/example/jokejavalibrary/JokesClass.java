package com.example.jokejavalibrary;

import java.util.Random;

public class JokesClass {
    String[] jokes = {"this is joke one", "this is joke two", "this is joke three"};

    public String getJoke() {
        String joke = jokes[new Random().nextInt(jokes.length)];
        return joke;
    }
}

