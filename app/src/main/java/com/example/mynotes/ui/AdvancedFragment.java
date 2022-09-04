package com.example.mynotes.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynotes.R;
import com.example.mynotes.model.Note;
import com.example.mynotes.model.NoteRepository;


public class AdvancedFragment extends Fragment {

    private static final String ARG_ID_NOTE = "note_id";

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
            int idNote = arguments.getInt(ARG_ID_NOTE);//получение индекса заметки которую нужно отобразить в фрагменте детализации заметки//
            NoteRepository notes = NoteRepository.getInstance();
            Note note = notes.getNoteById(idNote);
            setNote(view, note);

            Button buttonBack = view.findViewById(R.id.btnBack);
            buttonBack.setOnClickListener(v -> {
                requireActivity().getSupportFragmentManager().popBackStack();
            });
        }
    }

    public static AdvancedFragment newInstance(int idNote) {
        AdvancedFragment fragment = new AdvancedFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_NOTE, idNote);
        fragment.setArguments(args);
        return fragment;

    }

    public void setNote(View view, Note note){//метод устанавливает в переданное View указанную заметку
        //TextView textAdvanced = view.findViewById(R.id.text_advanced);//получение View в которую будем устанавливать текст
        //textAdvanced.setText(note.getDescriptionNotes());//установка текста

        EditText title = view.findViewById(R.id.noteTitle);
        EditText description = view.findViewById(R.id.noteDescription);

        title.setText(note.getTitleNote());
        description.setText(note.getDescriptionNote());

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    note.setTitleNote(title.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });


        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    note.setDescriptionNote(description.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {    }
        });
    }

}
