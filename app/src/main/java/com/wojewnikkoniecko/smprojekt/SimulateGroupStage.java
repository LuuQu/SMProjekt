package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wojewnikkoniecko.smprojekt.Models.Match;
import com.wojewnikkoniecko.smprojekt.Models.MatchResult;
import com.wojewnikkoniecko.smprojekt.Models.Team;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SimulateGroupStage extends AppCompatActivity {
    List<Team> teams = new ArrayList<>();
    List<Match> matches = new ArrayList<>();
    List<Match> chosenTeamMatches = new ArrayList<>();
    List<MatchResult> results = new ArrayList<>();
    DatabaseManager databaseManager = new DatabaseManager();
    String chosenTeam;
    String group;
    String placeInGroup;
    Match nextMatch;
    int max_index = 0;
    Button Simulate;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulate_group_stage);
        DisplayData();
    }
    public void DisplayData(){
        TextView Home;
        TextView homeGoalsText;
        TextView Away;
        TextView awayGoalsText;
        homeGoalsText = findViewById(R.id.homeGoals);
        awayGoalsText = findViewById(R.id.awayGoals);
        Button Simulate = findViewById(R.id.simulate);
        teams = databaseManager.getTeams();
        matches = databaseManager.getMatches();
        chosenTeam = getIntent().getStringExtra("ChosenTeam");
        placeInGroup = getIntent().getStringExtra("Group");
        String[] tmp = placeInGroup.split("");
        group = tmp[0];
        for(Match item : matches){
            if(item.Home.equals(placeInGroup) || item.Away.equals(placeInGroup)){
                chosenTeamMatches.add(item);
            }
        }
        nextMatch = chosenTeamMatches.get(index);
        Home = findViewById(R.id.homeTeam);
        Away = findViewById(R.id.awayTeam);
        String TeamNameHome = null;
        String TeamNameAway = null;
        for(Team item : teams){
            if(item.Group.equals(nextMatch.Home)){
                TeamNameHome = item.TeamName;
            } else if(item.Group.equals(nextMatch.Away)){
                TeamNameAway = item.TeamName;
            }
        }
        Home.setText(TeamNameHome);
        Away.setText(TeamNameAway);
        homeGoalsText.setVisibility(View.GONE);
        awayGoalsText.setVisibility(View.GONE);
        Button next = findViewById(R.id.nextMatch);
        next.setVisibility(View.GONE);
    }
    public void SimulateMatch(View view){
        Button Simulate = findViewById(R.id.simulate);
        Simulate.setVisibility(View.GONE);
        Button next = findViewById(R.id.nextMatch);
        next.setVisibility(View.VISIBLE);
        TextView homeGoalsText = findViewById(R.id.homeGoals);
        TextView awayGoalsText = findViewById(R.id.awayGoals);
        Random rand = new Random();
        boolean isHomeWinner;
        int homeOpportunities = rand.nextInt(7);
        int awayOpportunities = rand.nextInt(7);
        double homeLuck = rand.nextInt(100) * 0.01;
        double awayLuck = rand.nextInt(100) * 0.01;
        int homeGoals = (int)Math.round(homeOpportunities * homeLuck);
        int awayGoals = (int)Math.round(awayOpportunities * awayLuck);
        if(homeGoals > awayGoals){
            isHomeWinner = true;
        }
        else{
            isHomeWinner = false;
        }
        Simulate.setVisibility(View.GONE);
        MatchResult result = new MatchResult(nextMatch.MatchId, homeGoals, awayGoals,isHomeWinner);
        results.add(result);
        homeGoalsText.setVisibility(View.VISIBLE);
        homeGoalsText.setText(String.valueOf(result.homeGoals));
        awayGoalsText.setVisibility(View.VISIBLE);
        awayGoalsText.setText(String.valueOf(result.awayGoals));
    }
    public void NextMatch(View view){
        Button Simulate = findViewById(R.id.simulate);
        Simulate.setVisibility(View.VISIBLE);
        index++;
        DisplayData();
    }



}