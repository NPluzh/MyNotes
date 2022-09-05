package com.example.mynotes.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynotes.R;
import com.example.mynotes.model.Note;
import com.example.mynotes.model.NoteRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HeadingFragment extends Fragment {


    View dataContainer;


    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,//создание интерфейса пользователя
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_heading, container, false);
    }

    // Этот метод вызывается, когда макет экрана создан и готов к отображениюинформации. Создаем список городов
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle//инициализация интерфейса
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO:добавть очистку бекстека

        dataContainer = view.findViewById(R.id.data_container);
        initContent(dataContainer);
        FloatingActionButton btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {//слушатель на float button
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getContext(),
                        "Создана новая заметка", Toast.LENGTH_SHORT);
                toast.show();
                Note newNote = createNewNote();//добавление новой заметки в репозиторий
                if (isLandscape()) {
                    updateData();//если в ландшафтной ориентации то обновляем фрагмент с заметками
                } else {
                    showAdvancedFragment(newNote.getIdNote());//если в вертикальной ориентации, то показзываем вновь созданную заметку
                }

            }
        });

    }

    private void updateData() {
        Log.d("MY_TAG", "call updateData()");
        HeadingFragment headingFragment = new HeadingFragment();
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, headingFragment)
                .commit();
    }

    private Note createNewNote() {
        Note newNote = new Note("Новая заметка", "");
        NoteRepository notes = NoteRepository.getInstance();
        notes.putNote(newNote);
        return newNote;
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }


    private void initContent(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        NoteRepository notes = NoteRepository.getInstance();
        for (int i = 0; i < notes.getSize(); i++) {
            TextView textHeading = new TextView(getContext());
            textHeading.setText(notes.getNoteById(i).getTitleNote());
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