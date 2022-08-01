package com.example.mynotes;

import java.util.Date;

public class DataNotes {
    private String titleNotes;
    private String descriptionNotes;
    private Date dateNotes;

    public DataNotes(String titleNotes, String descriptionNotes) {
        this.titleNotes = titleNotes;
        this.descriptionNotes = descriptionNotes;
        this.dateNotes = new Date();
    }
}
