package com.paris.listaalunos.database.dao;


import com.paris.listaalunos.model.Student;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StudentDao {

    @Insert
    Long saveStudent(Student student);

    @Query("SELECT * FROM student")
    List<Student> allStudents();

    @Delete
    void remove(Student student);

    @Update
    void editStudent(Student student);
}
