package com.example.mynotes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        TextView textAdvanced = view.findViewById(R.id.text_advanced);//получение View в которую будем устанавливать текст
        textAdvanced.setText(note.getDescriptionNotes());//установка текста
    }

}
