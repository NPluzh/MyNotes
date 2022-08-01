package com.example.mynotes.model;

import java.util.Date;

public class DataNotes {
    private String headingNotes;
    private String descriptionNotes;
    private Date dateNotes;

    public DataNotes(String headingNotes, String descriptionNotes) {
        this.headingNotes = headingNotes;
        this.descriptionNotes = descriptionNotes;
        this.dateNotes = new Date();
    }
}
