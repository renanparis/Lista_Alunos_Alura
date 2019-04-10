package com.paris.listaalunos.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Telephone {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String number;
    private TelephoneType type;

    @ForeignKey(entity = Student.class,
            parentColumns = "id",
            childColumns = "studentId",
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE)
    public int studentId;

    public Telephone(String number, TelephoneType type, int studentId) {
        this.number = number;
        this.type = type;
        this.studentId = studentId;
    }

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

    public int getStudentId() {
        return studentId;
    }


    public void setType(TelephoneType type) {
        this.type = type;
    }
}
