package com.example.mynotes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mynotes.R;
import com.example.mynotes.model.NoteRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Log.d("TAG","Activity:   OnCreate()");

        if(savedInstanceState == null){
            NoteRepository notes = NoteRepository.getInstance();
            //notes.defaultInitialization(this);// инициализация через массив в ресурсах
            notes.inizializationByCicle(10);
            HeadingFragment headingFragment = new HeadingFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,headingFragment)
                    .commit();
        }

    }
}