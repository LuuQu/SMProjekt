package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wojewnikkoniecko.smprojekt.Models.Match;
import com.wojewnikkoniecko.smprojekt.Models.Team;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoundOf16Activity extends AppCompatActivity {
    TextView team1,team2,team3,team4,team5,team6,team7,team8,team9,team10,team11,team12,team13,team14,team15,team16;
    Button match1,match2,match3,match4,match5,match6,match7,match8,next,back;
    List<Team> winners = new ArrayList<>();
    List<Match> results = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_of16);
        Gson gson = new Gson();
        String json = getIntent().getStringExtra("Winners");
        Type listType = new TypeToken<List<Team>>() {
        }.getType();
        winners = gson.fromJson(json, listType);
        team1 = findViewById(R.id.idTeam1);
        team2 = findViewById(R.id.idTeam2);
        team3 = findViewById(R.id.idTeam3);
        team4 = findViewById(R.id.idTeam4);
        team5 = findViewById(R.id.idTeam5);
        team6 = findViewById(R.id.idTeam6);
        team7 = findViewById(R.id.idTeam7);
        team8 = findViewById(R.id.idTeam8);
        team9 = findViewById(R.id.idTeam9);
        team10 = findViewById(R.id.idTeam10);
        team11 = findViewById(R.id.idTeam11);
        team12 = findViewById(R.id.idTeam12);
        team13 = findViewById(R.id.idTeam13);
        team14 = findViewById(R.id.idTeam14);
        team15 = findViewById(R.id.idTeam15);
        team16 = findViewById(R.id.idTeam16);

        team1.setText(winners.get(0).getName());
        team2.setText(winners.get(3).getName());
        team3.setText(winners.get(4).getName());
        team4.setText(winners.get(7).getName());
        team5.setText(winners.get(8).getName());
        team6.setText(winners.get(11).getName());
        team7.setText(winners.get(12).getName());
        team8.setText(winners.get(15).getName());
        team9.setText(winners.get(2).getName());
        team10.setText(winners.get(1).getName());
        team11.setText(winners.get(6).getName());
        team12.setText(winners.get(5).getName());
        team13.setText(winners.get(10).getName());
        team14.setText(winners.get(9).getName());
        team15.setText(winners.get(14).getName());
        team16.setText(winners.get(13).getName());
        match1 = findViewById(R.id.idButton1);
        match2 = findViewById(R.id.idButton2);
        match3 = findViewById(R.id.idButton3);
        match4 = findViewById(R.id.idButton4);
        match5 = findViewById(R.id.idButton5);
        match6 = findViewById(R.id.idButton6);
        match7 = findViewById(R.id.idButton7);
        match8 = findViewById(R.id.idButton8);
        next = findViewById(R.id.idButtonNext);
        back = findViewById(R.id.idButtonBack);
        next.setEnabled(false);

        match1.setOnClickListener(view -> {
            //losuje wynik meczu 1
            match1.setEnabled(false);

            if(!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }
        });
        match2.setOnClickListener(view -> {
            //losuje wynik meczu 2
            match2.setEnabled(false);
            if(!match1.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match3.setOnClickListener(view -> {
            //losuje wynik meczu 3
            match3.setEnabled(false);
            if(!match2.isEnabled() && !match1.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match4.setOnClickListener(view -> {
            //losuje wynik meczu 4
            match4.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match1.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match5.setOnClickListener(view -> {
            //losuje wynik meczu 5
            match5.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match1.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match6.setOnClickListener(view -> {
            //losuje wynik meczu 6
            match6.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match1.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match7.setOnClickListener(view -> {
            //losuje wynik meczu 7
            match7.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match1.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match8.setOnClickListener(view -> {
            //losuje wynik meczu 8
            match8.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match1.isEnabled()) {
                next.setEnabled(true);
            }

        });
        next.setOnClickListener(view -> {
            Intent intent = new Intent(RoundOf16Activity.this, RoundOf8Activity.class);
            startActivity(intent);
        });
        back.setOnClickListener(view -> {
            finish();
        });
    }
    public void SimulateMatch(View view, int matchId, String homeName, String awayName) {
        Button Simulate = findViewById(R.id.simulate);
        Simulate.setVisibility(View.GONE);
        Button next = findViewById(R.id.nextMatch);
        next.setVisibility(View.VISIBLE);
        TextView homeGoalsText = findViewById(R.id.homeGoals);
        TextView awayGoalsText = findViewById(R.id.awayGoals);
        Random rand = new Random();
        int homeOpportunities = rand.nextInt(7);
        int awayOpportunities = rand.nextInt(7);
        double homeLuck = rand.nextInt(100) * 0.01;
        double awayLuck = rand.nextInt(100) * 0.01;
        int homeGoals = (int) Math.round(homeOpportunities * homeLuck);
        int awayGoals = (int) Math.round(awayOpportunities * awayLuck);
        Simulate.setVisibility(View.GONE);
        //results.add(new Match(nextMatch.getMatchId(),nextMatch.getHome(),nextMatch.getAway(),homeGoals,awayGoals));
        homeGoalsText.setVisibility(View.VISIBLE);
        homeGoalsText.setText(String.valueOf(homeGoals));
        awayGoalsText.setVisibility(View.VISIBLE);
        awayGoalsText.setText(String.valueOf(awayGoals));
        //index++;
    }
}