package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = new Intent(MainActivity.this, TeamActivity.class);
        //loadFromDbToMemory();
        //startActivity(intent);
    }
    private void loadFromDbToMemory() {
        //OldSQLiteManager sqLiteManager = OldSQLiteManager.instanceOfDatabase(this);
        //sqLiteManager.populateTeamListArray();
    }
    public void newGame(View view) {
        Intent i = new Intent(this, ChoosingTeamActivity.class);
        startActivity(i);
    }
    public void loadGame(View view) {
        Intent i = new Intent(this, LoadPreviousSaveActivity.class);
        startActivity(i);
    }
    public void exit(View view) {
        System.exit(0);
    }
    public void teamActivity(View view) {
        Intent i = new Intent(this, ManagingTeamsActivity.class);
        startActivity(i);
    }
}