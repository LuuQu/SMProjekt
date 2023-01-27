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
    Button addButton;
    TextView name, group;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managing_teams);
        addButton = findViewById(R.id.buttonId);
        name = findViewById(R.id.nameId);
        group = findViewById(R.id.groupId);
        listView = findViewById(R.id.itemsId);
        UpdateView();
        addButton.setOnClickListener(view -> {
            Team team = new Team(1,name.getText().toString(),group.getText().toString());
            //Toast.makeText(ManagingTeamsActivity.this,team.toString(),Toast.LENGTH_SHORT).show();
            Boolean aBoolean = databaseManager.AddTeam(team);
            Toast.makeText(ManagingTeamsActivity.this,"Success: " + aBoolean,Toast.LENGTH_SHORT).show();
            UpdateView();
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(ManagingTeamsActivity.this, EditTeamActivity.class);
                Team team = (Team)adapterView.getItemAtPosition(position);
                i.putExtra("id",team.getId());
                i.putExtra("name",team.getName());
                i.putExtra("group",team.getGroup());
                startActivity(i);
                //databaseManager.DeleteOneTeam(team);
            }
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