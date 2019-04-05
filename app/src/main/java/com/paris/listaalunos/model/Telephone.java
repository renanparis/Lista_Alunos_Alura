package com.paris.listaalunos.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Telephone {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String number;
    private TelephoneType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TelephoneType getType() {
        return type;
    }

    public void setType(TelephoneType type) {
        this.type = type;
    }
}
