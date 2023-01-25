package com.wojewnikkoniecko.smprojekt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "WC_DB";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_TABLE ="Teams";
    static final String Team_ID = "_ID";
    static final String Team_Name = "Team";
    static final String Group_ID = "Group_ID";

    private static final String CREATE_DB_QUERY =
            "CREATE TABLE " + DATABASE_TABLE+"("+Team_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "+Team_Name + " TEXT NOT NULL,"+Group_ID + " TEXT NOT NULL );";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
    }
}
