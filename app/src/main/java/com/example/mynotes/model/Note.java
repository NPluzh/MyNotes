package com.example.mynotes.model;

import java.util.Date;

public class Note {
    private String headingNotes;
    private String descriptionNotes;
    private Date dateNotes;
    private int idNote;


    private static int quantityNote;//

    static{
        quantityNote = 0;
    }

    public Note(String headingNotes, String descriptionNotes) {
        this.headingNotes = headingNotes;
        this.descriptionNotes = descriptionNotes;
        this.dateNotes = new Date();
        this.idNote = quantityNote;//даем заметке уникальный Id
        quantityNote+=1;
    }

    public String getHeadingNotes() {
        return headingNotes;
    }

    public String getDescriptionNotes() {
        return descriptionNotes;
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

}
