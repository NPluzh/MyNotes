package com.example.mynotes.model;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.mynotes.R;

import java.util.ArrayList;

public class NoteRepository {//один объект, который содержит информацию о всех заметках в NoteRepository
    private static NoteRepository INSTANCE;
    private ArrayList<Note> noteListRepository;

    private NoteRepository(){
        noteListRepository = new ArrayList<Note>();
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
            noteListRepository.add(new Note(headingNotes[i],advancedNotes[i]));//инициализация элементов заметок
        }
    }

    public Note getNoteById(int id) {//получение заметки по индексу
        for (Note note : noteListRepository) {
            if (note.getIdNote() == id) {
                return note;
            }
        }
        return null;//если по индексу не нашлось заметки, значит не будем её возвращать
    }

    public int getSize(){
        return noteListRepository.size();
    }

    public void inizializationByCicle(int quantityNotes){
        NoteRepository notes = getInstance();//получение или создание нового репозитория, который может быть не пустым из-за неправильного применения
        for (int i = 0; i < quantityNotes; i++) {
            notes.putNote(Note.initNoteById(i)); // инициализация заметки по индексу по индексу
        }
    }

    public void putNote(Note note){
        noteListRepository.add(note);
    }


    public ArrayList<Note> getListNotes(){
        return noteListRepository;
    }


    public void deleteNote(int idNote) {
        noteListRepository.remove(getNoteById(idNote));
    }
}
