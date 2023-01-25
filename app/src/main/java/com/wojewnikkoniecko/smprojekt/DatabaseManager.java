package com.wojewnikkoniecko.smprojekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;
    public DatabaseManager(Context context){
        this.context = context;
    }
    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public void insert (String team, String groupID){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.Team_Name, team);
        contentValues.put(DatabaseHelper.Group_ID, groupID);
        db.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch(){
        String[] columns = new String[] {
                DatabaseHelper.Team_ID, DatabaseHelper.Team_Name, DatabaseHelper.Group_ID
        };
        Cursor cursor = db.query(DatabaseHelper.DATABASE_TABLE, columns, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int update(long _id, String team, String groupID){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.Team_Name, team);
        contentValues.put(DatabaseHelper.Group_ID, groupID);
        int ret = db.update(DatabaseHelper.DATABASE_TABLE, contentValues, DatabaseHelper.Team_ID + "=" + _id, null);
        return ret;
    }
    public void delete(long _id){
        db.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.Team_ID + "=" + _id, null);
    }
}
