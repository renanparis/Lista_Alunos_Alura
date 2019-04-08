package com.paris.listaalunos.database.dao;

import com.paris.listaalunos.model.Telephone;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface TelephoneDAO {

    @Query("SELECT t.* FROM  Telephone t " +
            "WHERE t.studentID = :studentId LIMIT 1")
    Telephone searchFirstTelephone(int studentId);
}
