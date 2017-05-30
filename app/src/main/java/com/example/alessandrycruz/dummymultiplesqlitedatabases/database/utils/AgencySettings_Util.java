package com.example.alessandrycruz.dummymultiplesqlitedatabases.database.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import com.example.alessandrycruz.dummymultiplesqlitedatabases.database.columns.Base_Column;
import com.example.alessandrycruz.dummymultiplesqlitedatabases.database.helpers.Base_Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alessandry.cruz on 5/23/2017.
 */

public class AgencySettings_Util {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Base_Column.AgencySettigs.TABLE_NAME + " (" +
                    Base_Column.AgencySettigs._ID + " INTEGER PRIMARY KEY, " +
                    Base_Column.AgencySettigs.COLUMN_NAME_JSON + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Base_Column.AgencySettigs.TABLE_NAME;

    public long insertEntry(Base_Helper mBase_Helper, String json) {
        SQLiteDatabase db = mBase_Helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Base_Column.AgencySettigs.COLUMN_NAME_JSON, json);

        return db.insert(Base_Column.AgencySettigs.TABLE_NAME, null, values);
    }

    public long secureInsertEntry(Base_Helper mBase_Helper, String json) {
        final String sql = "INSERT INTO " + Base_Column.AgencySettigs.TABLE_NAME + "(" +
                Base_Column.AgencySettigs.COLUMN_NAME_JSON + ") " +
                "VALUES (?1)";

        long newRowId = -1;

        SQLiteDatabase db = mBase_Helper.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);

        db.beginTransaction();

        try {
            statement.bindString(1, json);

            newRowId = statement.executeInsert();

            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        return newRowId;
    }

    public List<String> readEntries(Base_Helper mBase_Helper) {
        SQLiteDatabase db = mBase_Helper.getReadableDatabase();

        String[] projection = {
                Base_Column.AgencySettigs._ID,
                Base_Column.AgencySettigs.COLUMN_NAME_JSON
        };

        Cursor cursor = db.query(Base_Column.AgencySettigs.TABLE_NAME, projection,
                null, null, null, null, null);

        List<String> itemsIds = new ArrayList<>();

        while (cursor.moveToNext()) {
            String itemJson = cursor.getString(cursor.getColumnIndexOrThrow(
                    Base_Column.AgencySettigs.COLUMN_NAME_JSON));

            itemsIds.add(itemJson);
        }

        cursor.close();

        return itemsIds;
    }

    public int deleteEntryById(Base_Helper mBase_Helper, String id) {
        SQLiteDatabase db = mBase_Helper.getWritableDatabase();
        String selection = Base_Column.AgencySettigs._ID + " LIKE ?";
        String[] selectionArgs = {id};

        return db.delete(Base_Column.AgencySettigs.TABLE_NAME, selection, selectionArgs);
    }

    public int secureDeleteEntryById(Base_Helper mBase_Helper, String id) {
        final String sql = "DELETE FROM " + Base_Column.AgencySettigs.TABLE_NAME +
                " WHERE " + Base_Column.AgencySettigs._ID + " = ?1";

        int count = -1;

        SQLiteDatabase db = mBase_Helper.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);

        db.beginTransaction();

        try {
            statement.bindString(1, id);
            count = statement.executeUpdateDelete();
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        return count;
    }

    public int updateEntryById(Base_Helper mBase_Helper, String json, String id) {
        SQLiteDatabase db = mBase_Helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Base_Column.AgencySettigs.COLUMN_NAME_JSON, json);

        String selection = Base_Column.AgencySettigs._ID + " LIKE ?";
        String[] selectionArgs = {"" + id};

        return db.update(Base_Column.AgencySettigs.TABLE_NAME, values, selection, selectionArgs);
    }

    public int secureUpdateEntryById(Base_Helper mBase_Helper, String json, String id) {
        final String sql = "UPDATE " + Base_Column.AgencySettigs.TABLE_NAME +
                " SET " + Base_Column.AgencySettigs.COLUMN_NAME_JSON + " = ?1" +
                " WHERE " + Base_Column.AgencySettigs._ID + " LIKE ?2";

        int count = -1;

        SQLiteDatabase db = mBase_Helper.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);

        db.beginTransaction();

        try {
            statement.bindString(1, json);
            statement.bindString(2, id);

            count = statement.executeUpdateDelete();

            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        return count;
    }
}