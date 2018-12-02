package com.example.jokeandroidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//import com.example.jokejavalibrary.JokesClass;

public class JokeActivity extends AppCompatActivity {

    private static final String INTENT_JOKE_KEY = "jokeKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke;
        TextView jokeTextView = findViewById(R.id.joke_text_view);

        if (getIntent().getExtras() != null){
            joke = getIntent().getExtras().getString(INTENT_JOKE_KEY);
            jokeTextView.setText(joke);
        }

//        JokesClass jokesClass = new JokesClass();

    }
}
