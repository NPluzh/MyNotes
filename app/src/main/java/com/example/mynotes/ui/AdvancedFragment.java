package com.example.mynotes.ui;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynotes.R;


public class AdvancedFragment extends Fragment {

    private static final String ARG_INDEX = "index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_advanced, container,
                false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            int index = arguments.getInt(ARG_INDEX);//получение индекса
            TextView textAdvanced = view.findViewById(R.id.text_advanced);//получение View в которую будем устанавливать текст
            String[] advancedNotes = getResources().getStringArray(R.array.definition_notes);//получение текста по индексу
            textAdvanced.setText(advancedNotes[index]);//установка текста
        }


    }

    public static AdvancedFragment newInstance(int index) {
        AdvancedFragment fragment = new AdvancedFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;

    }

}
