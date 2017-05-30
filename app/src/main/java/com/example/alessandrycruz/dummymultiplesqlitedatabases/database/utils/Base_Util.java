package com.example.alessandrycruz.dummymultiplesqlitedatabases.database.utils;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.alessandrycruz.dummymultiplesqlitedatabases.database.helpers.Base_Helper;

import java.io.File;

/**
 * Created by alessandry.cruz on 5/26/2017.
 */

public class Base_Util {
    public String createDatabasePath() {
        String path = Base_Helper.DATABASE_PATH;
        File sd = new File(path);

        if (!sd.exists()) {
            sd.mkdirs();
        }

        return path;
    }

    public boolean checkIfDatabaseExists(String databaseName) {
        SQLiteDatabase db = null;

        try {
            db = SQLiteDatabase.openDatabase(Base_Helper.DATABASE_PATH, null, SQLiteDatabase.OPEN_READONLY);
            db.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }

        return db != null;
    }
}