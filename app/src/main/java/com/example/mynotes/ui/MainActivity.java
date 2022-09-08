package com.example.mynotes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
            notes.inizializationByCicle(3);
            HeadingFragment headingFragment = new HeadingFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,headingFragment)
                    .commit();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.action_about): {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,new AboutFragment())
                        .addToBackStack("")
                        .commit();
                return false;
            }
            case (R.id.action_exit):{
                finish();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}