package com.paris.listaalunos.database.dao;

import com.paris.listaalunos.model.Telephone;

import androidx.room.Dao;

@Dao
public interface TelephoneDAO {

    Telephone searchFirstTelephone();
}
