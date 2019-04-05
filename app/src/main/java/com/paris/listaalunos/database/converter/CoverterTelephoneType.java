package com.paris.listaalunos.database.converter;

import com.paris.listaalunos.model.TelephoneType;

import androidx.room.TypeConverter;

public class CoverterTelephoneType {

    @TypeConverter
    public String toString(TelephoneType type) {
        return type.name();
    }

    @TypeConverter
    public TelephoneType toTelephoneType(String value) {
        if (value != null) {
            return TelephoneType.valueOf(value);
        }

        return TelephoneType.TELEPHONE;

    }

}


