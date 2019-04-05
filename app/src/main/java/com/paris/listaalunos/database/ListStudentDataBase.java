package com.paris.listaalunos.database;


import android.content.Context;

import com.paris.listaalunos.database.converter.ConverterCalendar;
import com.paris.listaalunos.database.converter.CoverterTelephoneType;
import com.paris.listaalunos.database.dao.StudentDao;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.model.Telephone;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import static com.paris.listaalunos.database.ListStudentMigrations.ALL_MIGRATIONS;

@Database(entities = {Student.class, Telephone.class},  version = 6, exportSchema = false)
@TypeConverters({ConverterCalendar.class, CoverterTelephoneType.class})

public abstract class ListStudentDataBase extends RoomDatabase {

    static final String AGENDA_DB = "agenda.db";

    private static ListStudentDataBase instance;

    public abstract StudentDao getRoomStudentDao();

    public static ListStudentDataBase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, ListStudentDataBase.class, AGENDA_DB)
                    .allowMainThreadQueries().addMigrations(ALL_MIGRATIONS)
                    .build();
        }
        return instance;
    }


}
