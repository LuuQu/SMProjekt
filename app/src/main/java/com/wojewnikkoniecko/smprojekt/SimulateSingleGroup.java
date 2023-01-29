package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wojewnikkoniecko.smprojekt.Models.Match;
import com.wojewnikkoniecko.smprojekt.Models.Team;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class SimulateSingleGroup extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12;
    TextView goal1,goal2,goal3,goal4,goal5,goal6,goal7,goal8,goal9,goal10,goal11,goal12;
    Button button1,button2,button3,button4,button5,button6,simulateAll,back;
    DatabaseManager databaseManager = new DatabaseManager(this);
    ArrayList<Match> matches = new ArrayList<>();
    String group, favTeam;
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulate_single_group);
        getSupportActionBar().hide();
        setVariables();
        loadData();
        setClickListeners();
    }
    private void setVariables() {
        group = getIntent().getStringExtra("group");
        favTeam = getIntent().getStringExtra("favTeam");
        textView1 = findViewById(R.id.idTeam1);
        textView2 = findViewById(R.id.idTeam2);
        textView3 = findViewById(R.id.idTeam3);
        textView4 = findViewById(R.id.idTeam4);
        textView5 = findViewById(R.id.idTeam5);
        textView6 = findViewById(R.id.idTeam6);
        textView7 = findViewById(R.id.idTeam7);
        textView8 = findViewById(R.id.idTeam8);
        textView9 = findViewById(R.id.idTeam9);
        textView10 = findViewById(R.id.idTeam10);
        textView11 = findViewById(R.id.idTeam11);
        textView12 = findViewById(R.id.idTeam12);
        goal1 = findViewById(R.id.idTeam1Goals);
        goal2 = findViewById(R.id.idTeam2Goals);
        goal3 = findViewById(R.id.idTeam3Goals);
        goal4 = findViewById(R.id.idTeam4Goals);
        goal5 = findViewById(R.id.idTeam5Goals);
        goal6 = findViewById(R.id.idTeam6Goals);
        goal7 = findViewById(R.id.idTeam7Goals);
        goal8 = findViewById(R.id.idTeam8Goals);
        goal9 = findViewById(R.id.idTeam9Goals);
        goal10 = findViewById(R.id.idTeam10Goals);
        goal11 = findViewById(R.id.idTeam11Goals);
        goal12 = findViewById(R.id.idTeam12Goals);
        button1 = findViewById(R.id.idButton1);
        button2 = findViewById(R.id.idButton2);
        button3 = findViewById(R.id.idButton3);
        button4 = findViewById(R.id.idButton4);
        button5 = findViewById(R.id.idButton5);
        button6 = findViewById(R.id.idButton6);
        simulateAll = findViewById(R.id.idButtonSimulateAll);
        back = findViewById(R.id.idButtonBack);
    }
    private void loadData() {
        ArrayList<Team> teams = databaseManager.GetAllTeams();
        for(Match match : databaseManager.GetAllMatches()) {
            if(match.getHome().contains(group)) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    String home = teams.stream().filter(team -> team.getGroup().equals(match.getHome())).findFirst().orElse(new Team()).getName();
                    String away = teams.stream().filter(team -> team.getGroup().equals(match.getAway())).findFirst().orElse(new Team()).getName();
                    match.setHome(home);
                    match.setAway(away);
                }
                matches.add(match);
            }
        }
        textView1.setText(matches.get(0).getHome());
        if(textView1.getText().equals(favTeam)) {
            textView1.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        textView2.setText(matches.get(0).getAway());
        if(textView2.getText().equals(favTeam)) {
            textView2.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        if(matches.get(0).getResultHome() != -1 && matches.get(0).getResultAway() != -1) {
            goal1.setText(String.valueOf(matches.get(0).getResultHome()));
            goal2.setText(String.valueOf(matches.get(0).getResultAway()));
            button1.setEnabled(false);
            button1.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
        }
        textView3.setText(matches.get(1).getHome());
        if(textView3.getText().equals(favTeam)) {
            textView3.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        textView4.setText(matches.get(1).getAway());
        if(textView4.getText().equals(favTeam)) {
            textView4.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        if(matches.get(1).getResultHome() != -1 && matches.get(1).getResultAway() != -1) {
            goal3.setText(String.valueOf(matches.get(1).getResultHome()));
            goal4.setText(String.valueOf(matches.get(1).getResultAway()));
            button2.setEnabled(false);
            button2.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
        }
        textView5.setText(matches.get(2).getHome());
        if(textView5.getText().equals(favTeam)) {
            textView5.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        textView6.setText(matches.get(2).getAway());
        if(textView6.getText().equals(favTeam)) {
            textView6.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        if(matches.get(2).getResultHome() != -1 && matches.get(2).getResultAway() != -1) {
            goal5.setText(String.valueOf(matches.get(2).getResultHome()));
            goal6.setText(String.valueOf(matches.get(2).getResultAway()));
            button3.setEnabled(false);
            button3.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
        }
        textView7.setText(matches.get(3).getHome());
        if(textView7.getText().equals(favTeam)) {
            textView7.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        textView8.setText(matches.get(3).getAway());
        if(textView8.getText().equals(favTeam)) {
            textView8.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        if(matches.get(3).getResultHome() != -1 && matches.get(3).getResultAway() != -1) {
            goal7.setText(String.valueOf(matches.get(3).getResultHome()));
            goal8.setText(String.valueOf(matches.get(3).getResultAway()));
            button4.setEnabled(false);
            button4.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
        }
        textView9.setText(matches.get(4).getHome());
        if(textView9.getText().equals(favTeam)) {
            textView9.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        textView10.setText(matches.get(4).getAway());
        if(textView10.getText().equals(favTeam)) {
            textView10.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        if(matches.get(4).getResultHome() != -1 && matches.get(4).getResultAway() != -1) {
            goal9.setText(String.valueOf(matches.get(4).getResultHome()));
            goal10.setText(String.valueOf(matches.get(4).getResultAway()));
            button5.setEnabled(false);
            button5.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
        }
        textView11.setText(matches.get(5).getHome());
        if(textView11.getText().equals(favTeam)) {
            textView11.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        textView12.setText(matches.get(5).getAway());
        if(textView12.getText().equals(favTeam)) {
            textView12.setTextColor(ContextCompat.getColor(this, R.color.favTeam));
        }
        if(matches.get(5).getResultHome() != -1 && matches.get(5).getResultAway() != -1) {
            goal11.setText(String.valueOf(matches.get(5).getResultHome()));
            goal12.setText(String.valueOf(matches.get(5).getResultAway()));
            button6.setEnabled(false);
            button6.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
        }
        isAnyMatchEnabled();
    }
    private void isAnyMatchEnabled() {
        if(!button1.isEnabled() && !button2.isEnabled() && !button3.isEnabled() && !button4.isEnabled() && !button5.isEnabled() && !button6.isEnabled()){
            simulateAll.setEnabled(false);
            simulateAll.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
        }
    }
    private int getRandomGoals() {
        int Opportunities = rand.nextInt(7);
        double Luck = rand.nextInt(100) * 0.01;
        return  (int) Math.round(Opportunities * Luck);
    }
    private void simulateAllMatches() {
        if(button1.isEnabled()) {
            button1.setEnabled(false);
            button1.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal1.setText(String.valueOf(goals1));
            goal2.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(0).getMatchId(),matches.get(0).getHome(),matches.get(0).getAway(),goals1,goals2));
        }
        if(button2.isEnabled()) {
            button2.setEnabled(false);
            button2.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal3.setText(String.valueOf(goals1));
            goal4.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(1).getMatchId(),matches.get(1).getHome(),matches.get(1).getAway(),goals1,goals2));
        }
        if(button3.isEnabled()) {
            button3.setEnabled(false);
            button3.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal5.setText(String.valueOf(goals1));
            goal6.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(2).getMatchId(),matches.get(2).getHome(),matches.get(2).getAway(),goals1,goals2));
        }
        if(button4.isEnabled()) {
            button4.setEnabled(false);
            button4.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal7.setText(String.valueOf(goals1));
            goal8.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(3).getMatchId(),matches.get(3).getHome(),matches.get(3).getAway(),goals1,goals2));
        }
        if(button5.isEnabled()) {
            button5.setEnabled(false);
            button5.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal9.setText(String.valueOf(goals1));
            goal10.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(4).getMatchId(),matches.get(4).getHome(),matches.get(4).getAway(),goals1,goals2));
        }
        if(button6.isEnabled()) {
            button6.setEnabled(false);
            button6.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal11.setText(String.valueOf(goals1));
            goal12.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(5).getMatchId(),matches.get(5).getHome(),matches.get(5).getAway(),goals1,goals2));
        }
    }
    private void setClickListeners() {
        button1.setOnClickListener(view -> {
            button1.setEnabled(false);
            button1.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal1.setText(String.valueOf(goals1));
            goal2.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(0).getMatchId(),matches.get(0).getHome(),matches.get(0).getAway(),goals1,goals2));
            isAnyMatchEnabled();
        });
        button2.setOnClickListener(view -> {
            button2.setEnabled(false);
            button2.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal3.setText(String.valueOf(goals1));
            goal4.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(1).getMatchId(),matches.get(0).getHome(),matches.get(0).getAway(),goals1,goals2));
            isAnyMatchEnabled();

        });
        button3.setOnClickListener(view -> {
            button3.setEnabled(false);
            button3.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal5.setText(String.valueOf(goals1));
            goal6.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(2).getMatchId(),matches.get(0).getHome(),matches.get(0).getAway(),goals1,goals2));
            isAnyMatchEnabled();

        });
        button4.setOnClickListener(view -> {
            button4.setEnabled(false);
            button4.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal7.setText(String.valueOf(goals1));
            goal8.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(3).getMatchId(),matches.get(0).getHome(),matches.get(0).getAway(),goals1,goals2));
            isAnyMatchEnabled();

        });
        button5.setOnClickListener(view -> {
            button5.setEnabled(false);
            button5.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal9.setText(String.valueOf(goals1));
            goal10.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(4).getMatchId(),matches.get(0).getHome(),matches.get(0).getAway(),goals1,goals2));
            isAnyMatchEnabled();

        });
        button6.setOnClickListener(view -> {
            button6.setEnabled(false);
            button6.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int goals1 = getRandomGoals();
            int goals2 = getRandomGoals();
            goal11.setText(String.valueOf(goals1));
            goal12.setText(String.valueOf(goals2));
            databaseManager.UpdateMatch(new Match(matches.get(5).getMatchId(),matches.get(0).getHome(),matches.get(0).getAway(),goals1,goals2));
            isAnyMatchEnabled();

        });
        simulateAll.setOnClickListener(view -> {
            simulateAll.setEnabled(false);
            simulateAll.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            simulateAllMatches();
        });
        back.setOnClickListener(view -> {
            finish();
        });
    }
}