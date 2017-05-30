package com.example.alessandrycruz.dummymultiplesqlitedatabases.database.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.example.alessandrycruz.dummymultiplesqlitedatabases.database.utils.AgencySettings_Util;

/**
 * Created by alessandry.cruz on 5/23/2017.
 */

public class Base_Helper extends SQLiteOpenHelper {
    public static final boolean DEBUG = false;

    private static final int DATABASE_VERSION = 1;
    // private static final String DATABASE_NAME = "Main.db";
    public static final String DATABASE_EXTENSION = ".db";
    private static final String DATABASE_FOLDER = "MvvBlue";

    public static final String DATABASE_PATH = Environment.getExternalStorageDirectory() + "/" +
            DATABASE_FOLDER;

    public Base_Helper(Context context, String databaseName) {
        // Creates the database in sandbox or specific path
        super(context, DEBUG ? DATABASE_PATH + "/" + databaseName + DATABASE_EXTENSION :
                databaseName + DATABASE_EXTENSION, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AgencySettings_Util.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AgencySettings_Util.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}