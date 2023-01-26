package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wojewnikkoniecko.smprojekt.Models.Match;
import com.wojewnikkoniecko.smprojekt.Models.Team;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupsActivity extends AppCompatActivity {

    HashMap<Integer, ArrayList<String>> teams = new HashMap<Integer, ArrayList<String>>();
    String[] groupsNames = {"A", "B", "C", "D", "E", "F", "G", "H"};
    int[] groups = {1, 2, 3, 4, 5, 6, 7, 8};
    int activeGroup = 1;
    String chosenTeam;
    DatabaseManager databaseManager = new DatabaseManager();
    Team yourTeam;
    List<Match> chosenTeamMatches = new ArrayList<>();
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        chosenTeam = getIntent().getStringExtra("ChosenTeam");
        String json = getIntent().getStringExtra("ChosenTeamMatches");
        if (json != null && !json.isEmpty()) {
            chosenTeamMatches = gson.fromJson(json, List.class);
        }

        List<Team> allTeams = databaseManager.getTeams();
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        int group = 1;
        for (Team item : allTeams) {
            list.add(item.TeamName);
            if (chosenTeam.equals(item.TeamName)) {
                yourTeam = item;
            }
            if (i == 3) {
                i = 0;
                teams.put(group, list);
                list = new ArrayList<>();
                group++;
            } else {
                i++;
            }
        }
        int x = i * 2;

    }

    public void loadChoosingTeam(View view) {
        finish();
    }

    public void SetTeams(int group) {
        ArrayList<String> list = teams.get(group);
        TextView team1 = (TextView) findViewById(R.id.team1);
        TextView team2 = (TextView) findViewById(R.id.team2);
        TextView team3 = (TextView) findViewById(R.id.team3);
        TextView team4 = (TextView) findViewById(R.id.team4);
        TextView groupText = (TextView) findViewById(R.id.GroupName);
        groupText.setText("Grupa " + groupsNames[group - 1]);
        team1.setText(list.get(0));
        team2.setText(list.get(1));
        team3.setText(list.get(2));
        team4.setText(list.get(3));
        team1.setTextColor(Color.BLACK);
        team2.setTextColor(Color.BLACK);
        team3.setTextColor(Color.BLACK);
        team4.setTextColor(Color.BLACK);

        if (list.get(0).equals(chosenTeam)) {
            team1.setTextColor(Color.RED);
        } else if (list.get(1).equals(chosenTeam)) {
            team2.setTextColor(Color.RED);
        } else if (list.get(2).equals(chosenTeam)) {
            team3.setTextColor(Color.RED);
        } else if (list.get(3).equals(chosenTeam)) {
            team4.setTextColor(Color.RED);
        }
    }

    public void prevGroup(View view) {
        if (activeGroup == 1) {
            activeGroup = groups[groups.length - 1];
        } else {
            activeGroup--;
        }
        SetTeams(activeGroup);
    }

    public void nextGroup(View view) {
        if (activeGroup == groups[groups.length - 1]) {
            activeGroup = groups[0];
        } else {
            activeGroup++;
        }
        SetTeams(activeGroup);
    }

    public void btnPlayPressed(View view) {
        Intent i = new Intent(this, SimulateGroupStage.class);
        i.putExtra("ChosenTeam", chosenTeam);
        i.putExtra("Group", yourTeam.Group);
        startActivity(i);
    }
}