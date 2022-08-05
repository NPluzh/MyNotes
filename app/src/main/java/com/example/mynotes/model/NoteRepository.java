package com.example.mynotes.model;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.mynotes.R;

import java.util.ArrayList;

public class NoteRepository {//один объект, который содержит информацию о всех заметках в NoteRepository
    private static NoteRepository INSTANCE;
    private ArrayList<DataNotes> noteRepository;

    private NoteRepository(){
        noteRepository = new ArrayList<DataNotes>();
    }

    public static NoteRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new NoteRepository();
        }
        return INSTANCE;
    }


    public void defaultInitialization(@NonNull Context context){
        String[] headingNotes = context.getResources().getStringArray(R.array.heading_notes);
        String[] advancedNotes = context.getResources().getStringArray(R.array.definition_notes);

        for (int i = 0; i < headingNotes.length; i++) {
            noteRepository.add(new DataNotes(headingNotes[i],advancedNotes[i]));//инициализация элементов заметок
        }
    }

    public DataNotes getNoteById(int id){//получение заметки по индексу
        return noteRepository.get(id);
    }

    public int getSize(){
        return noteRepository.size();
    }

}
