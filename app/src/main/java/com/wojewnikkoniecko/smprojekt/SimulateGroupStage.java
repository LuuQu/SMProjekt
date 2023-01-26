package com.wojewnikkoniecko.smprojekt;

import static android.os.Build.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wojewnikkoniecko.smprojekt.Models.Match;
import com.wojewnikkoniecko.smprojekt.Models.MatchResult;
import com.wojewnikkoniecko.smprojekt.Models.Team;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SimulateGroupStage extends AppCompatActivity {
    List<Team> teams = new ArrayList<>();
    List<Match> matches = new ArrayList<>();
    List<Match> playedMatches = new ArrayList<>();
    List<MatchResult> results = new ArrayList<>();
    DatabaseManager databaseManager = new DatabaseManager(this);
    String chosenTeam;
    String group;
    String placeInGroup;
    Match nextMatch;
    int max_index = 48;
    Button Simulate;
    int index = 0;
    boolean isLoaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulate_group_stage);
        DisplayData();
        Button goBackToTable = findViewById(R.id.toTable);
        goBackToTable.setVisibility(View.GONE);
    }
    public void DisplayData(){
        TextView Home;
        TextView homeGoalsText;
        TextView Away;
        TextView awayGoalsText;
        homeGoalsText = findViewById(R.id.homeGoals);
        awayGoalsText = findViewById(R.id.awayGoals);
        Button Simulate = findViewById(R.id.simulate);
        teams = databaseManager.GetAllTeams();
        //matches = databaseManager.getMatches();
        chosenTeam = getIntent().getStringExtra("ChosenTeam");
        placeInGroup = getIntent().getStringExtra("Group");
        String[] tmp = placeInGroup.split("");
        group = tmp[0];
        /*if(!isLoaded){
            for(Match item : matches){
                if(item.Home.equals(placeInGroup) || item.Away.equals(placeInGroup)){
                    chosenTeamMatches.add(item);
                }
            }
            isLoaded = true;
        }*/
        Home = findViewById(R.id.homeTeam);
        Away = findViewById(R.id.awayTeam);
        if(index <= max_index){
            nextMatch = matches.get(index);
            String TeamNameHome = null;
            String TeamNameAway = null;
            for(Team item : teams){
                if(item.getGroup().equals(nextMatch.Home)){
                    TeamNameHome = item.getName();
                } else if(item.getGroup().equals(nextMatch.Away)){
                    TeamNameAway = item.getName();
                }
            }

            Home.setText(TeamNameHome);
            Away.setText(TeamNameAway);
            homeGoalsText.setVisibility(View.GONE);
            awayGoalsText.setVisibility(View.GONE);
            Button next = findViewById(R.id.nextMatch);
            next.setVisibility(View.GONE);
        }
        else{
            homeGoalsText.setVisibility(View.GONE);
            awayGoalsText.setVisibility(View.GONE);
            Home.setVisibility(View.GONE);
            Away.setVisibility(View.GONE);
            TextView versus = findViewById(R.id.versus);
            versus.setVisibility(View.GONE);
        }


        StringBuilder sb = new StringBuilder();
        for (MatchResult item : results) {
            for(Match match : matches){
                if(item.MatchId == match.MatchId){
                    String homeTeamName = "undefined";
                    String awayTeamName = "undefined";
                    for(Team team : teams){
                        if(team.getGroup().equals(match.Home)){
                            homeTeamName = team.getName();
                        } else if(team.getGroup().equals(match.Away)){
                            awayTeamName = team.getName();
                        }
                    }
                    sb.append(homeTeamName + " " + item.homeGoals + " : " + item.awayGoals  + " " + awayTeamName);
                }
            }
            sb.append("\n");
        }
        ScrollView scrollView = findViewById(R.id.scroll);
        scrollView.postDelayed(() -> scrollView.fullScroll(View.FOCUS_DOWN), 100);
        TextView textbox = findViewById(R.id.text_box);;
        textbox.setText(sb.toString());

    }
    public void SimulateMatch(View view){
        Button Simulate = findViewById(R.id.simulate);
        Simulate.setVisibility(View.GONE);
        Button next = findViewById(R.id.nextMatch);
        next.setVisibility(View.VISIBLE);
        TextView homeGoalsText = findViewById(R.id.homeGoals);
        TextView awayGoalsText = findViewById(R.id.awayGoals);
        Random rand = new Random();
        boolean isHomeWinner = false;
        boolean isDraw = false;
        int homeOpportunities = rand.nextInt(7);
        int awayOpportunities = rand.nextInt(7);
        double homeLuck = rand.nextInt(100) * 0.01;
        double awayLuck = rand.nextInt(100) * 0.01;
        int homeGoals = (int)Math.round(homeOpportunities * homeLuck);
        int awayGoals = (int)Math.round(awayOpportunities * awayLuck);
        if(homeGoals > awayGoals){
            isHomeWinner = true;
        }
        else if (homeGoals > awayGoals){
            isHomeWinner = false;
        }
        else{
            isDraw = true;
        }
        Simulate.setVisibility(View.GONE);
        MatchResult result = new MatchResult(nextMatch.MatchId, homeGoals, awayGoals, isHomeWinner, isDraw, homeOpportunities, awayOpportunities);
        results.add(result);
        homeGoalsText.setVisibility(View.VISIBLE);
        homeGoalsText.setText(String.valueOf(result.homeGoals));
        awayGoalsText.setVisibility(View.VISIBLE);
        awayGoalsText.setText(String.valueOf(result.awayGoals));
        index++;
    }
    public void NextMatch(View view){
        Button Simulate = findViewById(R.id.simulate);
        Simulate.setVisibility(View.GONE);
        if(index == max_index){
            Button goBackToTable = findViewById(R.id.toTable);
            goBackToTable.setVisibility(View.VISIBLE);
        }
        else{
            Simulate.setVisibility(View.VISIBLE);
            DisplayData();
        }
    }
    public void GoBackToTableView(View view){
        Gson gson = new Gson();
        Intent i = new Intent(this, GroupsActivity.class);
        i.putExtra("ChosenTeam", chosenTeam);
        i.putExtra("ChosenTeamMatches", gson.toJson(results));
        i.putExtra("Completed", "true");
        startActivity(i);
    }



}