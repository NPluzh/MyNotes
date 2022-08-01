package com.example.mynotes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynotes.R;

public class NotesHeadingFragment extends Fragment {
    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_noties_heading, container, false);
    }

    // Этот метод вызывается, когда макет экрана создан и готов к отображениюинформации. Создаем список городов
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    // создаём список городов на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] headingNotes = getResources().getStringArray(R.array.heading_notes);
// В этом цикле создаём элемент TextView,
// заполняем его значениями,
// и добавляем на экран.
        for (String headingNote : headingNotes) {
            TextView textHeading = new TextView(getContext());
            textHeading.setText(headingNote);
            textHeading.setTextSize(30);
            layoutView.addView(textHeading);
        }
    }
}