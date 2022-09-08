package com.example.mynotes.model;

import java.util.Date;

public class Note {


    private String titleNote;
    private String descriptionNote;
    private Date dateNotes;
    private int idNote;


    private static int quantityNote;//

    static{
        quantityNote = 0;
    }

    public Note(String titleNote, String descriptionNote) {
        this.titleNote = titleNote;
        this.descriptionNote = descriptionNote;
        this.dateNotes = new Date();
        this.idNote = quantityNote;//даем заметке уникальный Id
        quantityNote+=1;
    }

    public String getTitleNote() {
        return titleNote;
    }

    public String getDescriptionNote() {
        return descriptionNote;
    }

    public Date getDateNotes() {
        return dateNotes;
    }

    public int getIdNote(){
        return idNote;
    }

    public static Note initNoteById(int id) {// инициализация заметки по id
        String headingNote = "Заметка " + id;
        String descriptionNote = "Описание заметки " + id;
        return new Note(headingNote,descriptionNote);

    }

    public void setTitleNote(String titleNote) {
        this.titleNote = titleNote;
    }

    public void setDescriptionNote(String descriptionNote) {
        this.descriptionNote = descriptionNote;
    }

}
