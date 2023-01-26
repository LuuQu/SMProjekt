package com.wojewnikkoniecko.smprojekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private final String TABLE_NAME = "Teams";
    private final String TEAM_NAME = "Name";
    private final String TEAM_GROUP = "TeamGroup";
    private final String TEAM_ID = "Id";
    public DatabaseManager(@Nullable Context context) {
        super(context, "WcDataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String statement = "CREATE TABLE "+ TABLE_NAME + " (" + TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TEAM_GROUP + " TEXT, "+ TEAM_NAME + " TEXT)";
        sqLiteDatabase.execSQL(statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public Boolean AddTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEAM_GROUP,team.getGroup());
        contentValues.put(TEAM_NAME,team.getName());
        Team.teamArrayList.add(team);
        long insert = db.insert(TABLE_NAME, null, contentValues);
        if(insert == -1) {
            return false;
        }
        return true;
    }
    public ArrayList<Team> GetAllTeams() {
        ArrayList<Team> result = new ArrayList<Team>();
        String querystring = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(querystring,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String group = cursor.getString(1);
                String name = cursor.getString(2);
                Team team = new Team(id,name,group);
                result.add(team);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return result;
    }
    public void UpdateTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + TABLE_NAME + " SET " + TEAM_NAME + " = '" + team.getName() + "', " + TEAM_GROUP + " = '" + team.getGroup() +
                "' WHERE " + TEAM_ID + " = " + team.getId();
        db.execSQL(queryString);

    }
    public boolean DeleteOneTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + TEAM_ID + " = " + team.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            return true;
        }
        return false;
    }
}
