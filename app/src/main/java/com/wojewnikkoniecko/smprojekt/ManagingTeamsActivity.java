package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wojewnikkoniecko.smprojekt.Models.Team;

import java.util.ArrayList;

public class ManagingTeamsActivity extends AppCompatActivity {
    DatabaseManager databaseManager = new DatabaseManager(ManagingTeamsActivity.this);
    Button exit;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managing_teams);
        listView = findViewById(R.id.itemsId);
        exit = findViewById(R.id.idButtonBack);
        UpdateView();
        exit.setOnClickListener(view -> finish());
        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent i = new Intent(ManagingTeamsActivity.this, EditTeamActivity.class);
            Team team = (Team)adapterView.getItemAtPosition(position);
            i.putExtra("id",team.getId());
            i.putExtra("name",team.getName());
            i.putExtra("group",team.getGroup());
            startActivity(i);
        });
    }
    public void UpdateView() {
        ArrayList<Team> teams = databaseManager.GetAllTeams();
        ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(ManagingTeamsActivity.this, android.R.layout.simple_list_item_1,teams);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateView();
    }
}