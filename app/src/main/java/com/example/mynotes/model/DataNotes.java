package com.example.mynotes.model;

import java.util.Date;

public class DataNotes {//TODO: массив записей
    private String headingNotes;
    private String descriptionNotes;
    private Date dateNotes;//TODO: использование класса DataPick

    public DataNotes(String headingNotes, String descriptionNotes) {
        this.headingNotes = headingNotes;
        this.descriptionNotes = descriptionNotes;
        this.dateNotes = new Date();
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

}
