package com.paris.listaalunos.database.converter;

import java.util.Calendar;

import androidx.room.TypeConverter;

public class ConverterCalendar {
    @TypeConverter
    public long toLong(Calendar value) {

        if (value != null){

            return value.getTimeInMillis();
        }
        return Long.parseLong(null);
    }

    @TypeConverter
    public Calendar toCalendar(Long value) {
        Calendar nowadays = Calendar.getInstance();

        if (value != null) {

            nowadays.setTimeInMillis(value);
        }

        return nowadays;
    }
}
