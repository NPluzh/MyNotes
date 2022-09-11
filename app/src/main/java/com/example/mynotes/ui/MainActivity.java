package com.example.mynotes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mynotes.R;
import com.example.mynotes.model.NoteRepository;
import com.google.android.material.navigation.NavigationView;

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

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.burger_open, R.string.burger_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case(R.id.action_drawer_about):{
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container,new AboutFragment())
                                .addToBackStack("")
                                .commit();
                        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                        drawerLayout.close();
                        return true;
                    }

                    case (R.id.action_drawer_exit):
                    {
                        finish();
                        return true;
                    }
            }
            return true;
        }
        });
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