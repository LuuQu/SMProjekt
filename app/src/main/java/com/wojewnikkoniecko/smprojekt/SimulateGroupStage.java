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
import com.wojewnikkoniecko.smprojekt.Models.Team;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SimulateGroupStage extends AppCompatActivity {
    List<Team> teams = new ArrayList<>();
    List<Match> matches = new ArrayList<>();
    List<Match> results = new ArrayList<>();
    List<Match> playedMatches = new ArrayList<>();
    DatabaseManager databaseManager = new DatabaseManager(this);
    String chosenTeam;
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

    public void DisplayData() {
        TextView Home;
        TextView homeGoalsText;
        TextView Away;
        TextView awayGoalsText;
        homeGoalsText = findViewById(R.id.homeGoals);
        awayGoalsText = findViewById(R.id.awayGoals);
        Button Simulate = findViewById(R.id.simulate);
        teams = databaseManager.GetAllTeams();
        matches = databaseManager.GetAllMatches();
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
        if (index <= max_index) {
            nextMatch = matches.get(index);
            String TeamNameHome = null;
            String TeamNameAway = null;
            for (Team item : teams) {
                if (item.getGroup().equals(nextMatch.getHome())) {
                    TeamNameHome = item.getName();
                } else if (item.getGroup().equals(nextMatch.getAway())) {
                    TeamNameAway = item.getName();
                }
            }

            Home.setText(TeamNameHome);
            Away.setText(TeamNameAway);
            homeGoalsText.setVisibility(View.GONE);
            awayGoalsText.setVisibility(View.GONE);
            Button next = findViewById(R.id.nextMatch);
            next.setVisibility(View.GONE);
        } else {
            homeGoalsText.setVisibility(View.GONE);
            awayGoalsText.setVisibility(View.GONE);
            Home.setVisibility(View.GONE);
            Away.setVisibility(View.GONE);
            TextView versus = findViewById(R.id.versus);
            versus.setVisibility(View.GONE);
        }


        StringBuilder sb = new StringBuilder();
        for (Match item : results) {
            String homeTeamName = "undefined";
            String awayTeamName = "undefined";
            for (Team team : teams) {
                if (team.getGroup().equals(item.getHome())) {
                    homeTeamName = team.getName();
                } else if (team.getGroup().equals(item.getAway())) {
                    awayTeamName = team.getName();
                }
            }
            sb.append(homeTeamName + " " + item.getResultHome() + " : " + item.getResultAway()  + " " + awayTeamName);
            sb.append("\n");
        }
        ScrollView scrollView = findViewById(R.id.scroll);
        scrollView.postDelayed(() -> scrollView.fullScroll(View.FOCUS_DOWN), 100);
        TextView textbox = findViewById(R.id.text_box);
        ;
        textbox.setText(sb.toString());

    }

    public void SimulateMatch(View view) {
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
        int homeGoals = (int) Math.round(homeOpportunities * homeLuck);
        int awayGoals = (int) Math.round(awayOpportunities * awayLuck);
        if (homeGoals > awayGoals) {
            isHomeWinner = true;
        } else if (homeGoals > awayGoals) {
            isHomeWinner = false;
        } else {
            isDraw = true;
        }
        Simulate.setVisibility(View.GONE);
        results.add(new Match(nextMatch.getMatchId(),nextMatch.getHome(),nextMatch.getAway(),homeGoals,awayGoals));
        homeGoalsText.setVisibility(View.VISIBLE);
        homeGoalsText.setText(String.valueOf(homeGoals));
        awayGoalsText.setVisibility(View.VISIBLE);
        awayGoalsText.setText(String.valueOf(awayGoals));
        index++;
    }

    public void NextMatch(View view) {
        Button Simulate = findViewById(R.id.simulate);
        Simulate.setVisibility(View.GONE);
        if (index == max_index) {
            for(Match result : results){
                databaseManager.UpdateMatch(result);
            }
            Button goBackToTable = findViewById(R.id.toTable);
            goBackToTable.setVisibility(View.VISIBLE);
        } else {
            Simulate.setVisibility(View.VISIBLE);
            DisplayData();
        }
    }

    public void GoBackToTableView(View view) {
        finish();
//        Gson gson = new Gson();
//        Intent i = new Intent(this, GroupsActivity.class);
//        i.putExtra("ChosenTeam", chosenTeam);
//        i.putExtra("ChosenTeamMatches", gson.toJson(results));
//        i.putExtra("Completed", "true");
//        startActivity(i);
    }


}