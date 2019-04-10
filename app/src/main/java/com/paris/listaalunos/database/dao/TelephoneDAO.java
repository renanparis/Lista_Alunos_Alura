package com.paris.listaalunos.database.dao;

import com.paris.listaalunos.model.Telephone;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TelephoneDAO {

    @Query("SELECT t.* FROM  Telephone t " +
            "WHERE t.studentId = :studentId LIMIT 1")
    Telephone searchFirstTelephone(int studentId);

    @Insert
    void saveTelephone(Telephone... telephones);

    @Query("SELECT * FROM  Telephone " +
            "WHERE studentId = :studentId")
    List<Telephone> searchAllPhones(int studentId);

    @Update
    void update(List<Telephone> studentPhones);
}
