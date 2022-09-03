package com.example.mynotes.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynotes.R;
import com.example.mynotes.model.NoteRepository;

public class HeadingFragment extends Fragment {


    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,//создание интерфейса пользователя
                             Bundle savedInstanceState) {
        Log.d("TAG","Fragment (Heading):   OnCreateView()");
        return inflater.inflate(R.layout.fragment_heading, container, false);
    }

    // Этот метод вызывается, когда макет экрана создан и готов к отображениюинформации. Создаем список городов
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle//инициализация интерфейса
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO:добавть очистку бекстека

            initContent(view);

        Log.d("TAG","Fragment (Heading):   OnViewCreated()");

    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }


    // создаём список городов на экране из массива в ресурсах
    private void initContent(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        NoteRepository notes = NoteRepository.getInstance();
        for (int i = 0; i < notes.getSize(); i++) {
            TextView textHeading = new TextView(getContext());
            textHeading.setText(notes.getNoteById(i).getHeadingNotes());
            textHeading.setTextSize(30);
            layoutView.addView(textHeading);
            int finalI = i;
            textHeading.setOnClickListener(v -> {
                showAdvancedFragment(notes.getNoteById(finalI).getIdNote());//TODO:Сделать подсветку
            });
        }
    }


    private void showAdvancedFragment(int idNote) {
        if (isLandscape()) {
            showLandscapeAdvancedFragment(idNote);
        } else {
            showPortraitAdvancedFragment(idNote);
        }
    }

    private void showLandscapeAdvancedFragment(int idNote) {
        AdvancedFragment advancedFragment = AdvancedFragment.newInstance(idNote);//TODO:Попробовать применить паттерн Singleton
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detail_container, advancedFragment)
                .addToBackStack("")
                .commit();

    }

    private void showPortraitAdvancedFragment(int idNote) {
        AdvancedFragment advancedFragment = AdvancedFragment.newInstance(idNote);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, advancedFragment)
                .addToBackStack("")
                .commit();

    }




}