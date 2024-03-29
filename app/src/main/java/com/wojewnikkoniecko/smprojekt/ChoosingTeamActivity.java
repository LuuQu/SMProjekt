package com.wojewnikkoniecko.smprojekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wojewnikkoniecko.smprojekt.Models.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ChoosingTeamActivity extends AppCompatActivity {
    //teams do wczytania z bazy danych
    List<String> teamsAdapter = new ArrayList<>();
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    DatabaseManager databaseManager = new DatabaseManager(this);
    Boolean backFromIntent = false;
    private String chosenteam = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_team);
        getSupportActionBar().hide();
        List<Team> teams = databaseManager.GetAllTeams();
        for(Team item : teams){
            teamsAdapter.add(item.getName());
        }
        autoCompleteTextView = findViewById(R.id.teams);
        adapterItems = new ArrayAdapter<>(this,R.layout.list_item,teamsAdapter);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            chosenteam = item;
            Toast.makeText(ChoosingTeamActivity.this,"Wybrałeś drużynę: " + item,Toast.LENGTH_SHORT).show();
            Button button = (Button)findViewById(R.id.nextButton);
            button.setEnabled(true);
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.enabledButton));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(backFromIntent) {
            databaseManager.GetAllTeams();
            backFromIntent = false;
        }
    }

    public void loadGroups(View view) {
        databaseManager.ResetMatches();
        Intent i = new Intent(this, GroupsActivity.class);
        i.putExtra("ChosenTeam", chosenteam);
        startActivity(i);
    }
    public void loadMainMenu(View view) {
        finish();
    }

    public void teamActivity(View view) {
        backFromIntent = true;
        Intent i = new Intent(this, ManagingTeamsActivity.class);
        startActivity(i);
    }
}