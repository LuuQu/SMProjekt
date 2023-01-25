package com.wojewnikkoniecko.smprojekt;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TeamActivity  extends AppCompatActivity {
    EditText editTeamID;
    EditText editTeamName;
    EditText editGroupID;

    public DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity);
        editTeamID = (EditText) findViewById(R.id.editTextID);
        editTeamName = (EditText) findViewById(R.id.editTextTeamName);
        editGroupID = (EditText) findViewById(R.id.editTextGroupID);

        dbManager = new DatabaseManager(this);
        try{
            dbManager.open();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void btnInsertPressed(View view){
        String team = editTeamName.getText().toString();
        String GroupId = editGroupID.getText().toString();
        dbManager.insert(team,GroupId);
    }
    public void btnUpdatePressed(View view){
        dbManager.update( Long.parseLong(editTeamID.getText().toString()),editTeamName.getText().toString(), editGroupID.getText().toString());
    }
    public void btnFetchPressed(View view){
        Cursor cursor = dbManager.fetch();
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Team_ID));
                @SuppressLint("Range") String Team = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Team_Name));
                @SuppressLint("Range") String GroupID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Group_ID));
                Log.i("Database_TAG",ID + " " + Team + " " + GroupID);
            }while(cursor.moveToNext());
        }
    }
    public void btnDeletePressed(View view){
        dbManager.delete(Long.parseLong(editTeamID.getText().toString()));
    }
}
