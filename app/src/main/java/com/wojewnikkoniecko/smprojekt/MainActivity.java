package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wojewnikkoniecko.smprojekt.Models.Match;
import com.wojewnikkoniecko.smprojekt.Models.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseManager databaseManager = new DatabaseManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        databaseManager.ResetMatches();
        databaseManager.GetAllTeams();
        databaseManager.GetAllMatches();
        loadTeamsAndMatches();
    }
    public void loadTeamsAndMatches() {
        if(Team.teamArrayList.size() == 0) {
            databaseManager.SetTeams();
        }
        if(Match.matchArrayList.size() == 0) {
            databaseManager.SetMatches();
        }
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
}