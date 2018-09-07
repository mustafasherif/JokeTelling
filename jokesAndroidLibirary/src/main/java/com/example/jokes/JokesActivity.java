package com.example.jokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        String joke=getIntent().getStringExtra("joke");
        TextView jokeTextView=(TextView)findViewById(R.id.joke_tv);
        jokeTextView.setText(joke);
    }
}
