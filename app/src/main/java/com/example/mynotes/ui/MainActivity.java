package com.example.mynotes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mynotes.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotesHeadingFragment headingFragment = new NotesHeadingFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,headingFragment)
                .commit();
    }
}