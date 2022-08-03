package com.example.mynotes.ui;

import android.os.Bundle;
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

public class HeadingFragment extends Fragment {
    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_heading, container, false);
    }

    // Этот метод вызывается, когда макет экрана создан и готов к отображениюинформации. Создаем список городов
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initContent(view);
    }

    // создаём список городов на экране из массива в ресурсах
    private void initContent(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] headingNotes = getResources().getStringArray(R.array.heading_notes);
        for (int i = 0; i < headingNotes.length; i++) {
            TextView textHeading = new TextView(getContext());
            textHeading.setText(headingNotes[i]);
            textHeading.setTextSize(30);
            layoutView.addView(textHeading);
            int index = i;
            textHeading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAdvancedFragment(index);
                }
            });
        }
    }

    private void showAdvancedFragment(int index){
        AdvancedFragment advancedFragment = AdvancedFragment.newInstance(index);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,advancedFragment)
                .addToBackStack("")
                .commit();

    }


}