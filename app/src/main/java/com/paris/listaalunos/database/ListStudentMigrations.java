package com.paris.listaalunos.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

 class ListStudentMigrations {

     public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
         @Override
         public void migrate(@NonNull SupportSQLiteDatabase database) {
             database.execSQL("ALTER TABLE Student ADD COLUMN lastName  TEXT");
         }
     };
     public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
         @Override
         public void migrate(@NonNull SupportSQLiteDatabase database) {
             database.execSQL("CREATE TABLE IF NOT EXISTS `Student_new` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `phone` TEXT, `email` TEXT)");
             database.execSQL("INSERT INTO Student_new (id, name, phone, email) SELECT id, name, phone, email FROM Student");
             database.execSQL("DROP TABLE Student");
             database.execSQL("ALTER TABLE Student_new RENAME TO Student");
         }
     };
     public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
         @Override
         public void migrate(@NonNull SupportSQLiteDatabase database) {
             database.execSQL("ALTER TABLE Student ADD COLUMN registrationDate INTEGER");
         }
     };
     private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
         @Override
         public void migrate(@NonNull SupportSQLiteDatabase database) {
             database.execSQL("CREATE TABLE IF NOT EXISTS `Student_new` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `telephone` TEXT, `cellPhone` TEXT, `email` TEXT, `registrationDate` INTEGER)");


             database.execSQL("INSERT INTO Student_new (id, name, telephone, email, registrationDate) SELECT id, name, phone, email, registrationDate FROM Student");
             database.execSQL("DROP TABLE Student");
             database.execSQL("ALTER TABLE Student_new RENAME TO Student");
         }
     };


     static final Migration[] ALL_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5};
}
