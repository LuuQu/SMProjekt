package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wojewnikkoniecko.smprojekt.Models.MatchKnockoutStage;
import com.wojewnikkoniecko.smprojekt.Models.SaveData;
import com.wojewnikkoniecko.smprojekt.Models.Team;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RoundOf8Activity extends AppCompatActivity {
    TextView team1, team2, team3, team4, team5, team6, team7, team8;
    TextView idTeam1Goals, idTeam2Goals, idTeam3Goals, idTeam4Goals, idTeam5Goals, idTeam6Goals, idTeam7Goals, idTeam8Goals;
    Button match1, match2, match3, match4, next, back;
    List<Team> winners = new ArrayList<>();
    HashMap<Integer, MatchKnockoutStage> results = new HashMap<>();
    List<Team> winnersToRoundOfFour = new ArrayList<>();
    SaveData save;
    String uuid;
    Gson gson = new Gson();
    Boolean loadedSave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_of8);
        match1 = findViewById(R.id.idButton1);
        match2 = findViewById(R.id.idButton2);
        match3 = findViewById(R.id.idButton3);
        match4 = findViewById(R.id.idButton4);
        next = findViewById(R.id.idButtonNext);
        back = findViewById(R.id.idButtonBack);
        next.setEnabled(false);

        String savejson = getIntent().getStringExtra("loadSave");
        if (savejson != null) {
            save = gson.fromJson(savejson, SaveData.class);
            results = save.getRoundOfEightResults();
            winners = save.getWinnersOfRoundOfSixteen();
            winnersToRoundOfFour = save.getWinnersOfRoundOfEight();
            loadedSave = true;
            match1.setText("Result");
            match2.setText("Result");
            match3.setText("Result");
            match4.setText("Result");
        } else {
            Gson gson = new Gson();
            String json = getIntent().getStringExtra("Winners");
            Type listType = new TypeToken<List<Team>>() {
            }.getType();
            winners = gson.fromJson(json, listType);

            String jsonSave = getIntent().getStringExtra("save");
            save = gson.fromJson(jsonSave, SaveData.class);
            uuid = getIntent().getStringExtra("uuid");
        }


        team1 = findViewById(R.id.idTeam1);
        team2 = findViewById(R.id.idTeam2);
        team3 = findViewById(R.id.idTeam3);
        team4 = findViewById(R.id.idTeam4);
        team5 = findViewById(R.id.idTeam5);
        team6 = findViewById(R.id.idTeam6);
        team7 = findViewById(R.id.idTeam7);
        team8 = findViewById(R.id.idTeam8);

        team1.setText(winners.get(0).getName());
        team2.setText(winners.get(1).getName());
        team3.setText(winners.get(2).getName());
        team4.setText(winners.get(3).getName());
        team5.setText(winners.get(4).getName());
        team6.setText(winners.get(5).getName());
        team7.setText(winners.get(6).getName());
        team8.setText(winners.get(7).getName());


        match1.setOnClickListener(view -> {
            //losuje wynik meczu 1
            match1.setEnabled(false);
            int matchId = 1;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(0).getName(), winners.get(1).getName());
            }
            MatchKnockoutStage result = results.get(matchId);
            idTeam1Goals = findViewById(R.id.idTeam1Goals);
            idTeam2Goals = findViewById(R.id.idTeam2Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam2Goals.setText(String.valueOf(result.getResultAway() + " (" + String.valueOf(result.getResultPenaltyAway()) + ")"));
                idTeam1Goals.setText(String.valueOf(result.getResultHome() + " (" + String.valueOf(result.getResultPenaltyHome()) + ")"));
            } else {
                idTeam2Goals.setText(String.valueOf(result.getResultAway()));
                idTeam1Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled()) {
                next.setEnabled(true);
            }
        });
        match2.setOnClickListener(view -> {
            //losuje wynik meczu 2
            match2.setEnabled(false);
            int matchId = 2;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(2).getName(), winners.get(3).getName());
            }
            MatchKnockoutStage result = results.get(matchId);
            idTeam3Goals = findViewById(R.id.idTeam3Goals);
            idTeam4Goals = findViewById(R.id.idTeam4Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam4Goals.setText(String.valueOf(result.getResultAway() + " (" + String.valueOf(result.getResultPenaltyAway()) + ")"));
                idTeam3Goals.setText(String.valueOf(result.getResultHome() + " (" + String.valueOf(result.getResultPenaltyHome()) + ")"));
            } else {
                idTeam4Goals.setText(String.valueOf(result.getResultAway()));
                idTeam3Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!match1.isEnabled() && !match3.isEnabled() && !match4.isEnabled()) {
                next.setEnabled(true);
            }
        });
        match3.setOnClickListener(view -> {
            //losuje wynik meczu 3
            match3.setEnabled(false);
            int matchId = 3;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(4).getName(), winners.get(5).getName());
            }
            MatchKnockoutStage result = results.get(matchId);
            idTeam5Goals = findViewById(R.id.idTeam5Goals);
            idTeam6Goals = findViewById(R.id.idTeam6Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam6Goals.setText("(" + String.valueOf(result.getResultPenaltyAway()) + ") " + String.valueOf(result.getResultAway()));
                idTeam5Goals.setText("(" + String.valueOf(result.getResultPenaltyHome()) + ") " + String.valueOf(result.getResultHome()));
            } else {
                idTeam6Goals.setText(String.valueOf(result.getResultAway()));
                idTeam5Goals.setText(String.valueOf(result.getResultHome()));

            }
            if (!match2.isEnabled() && !match1.isEnabled() && !match4.isEnabled()) {
                next.setEnabled(true);
            }
        });
        match4.setOnClickListener(view -> {
            //losuje wynik meczu 4
            match4.setEnabled(false);
            int matchId = 4;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(6).getName(), winners.get(7).getName());
            }MatchKnockoutStage result = results.get(matchId);
            idTeam7Goals = findViewById(R.id.idTeam7Goals);
            idTeam8Goals = findViewById(R.id.idTeam8Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam8Goals.setText("(" + String.valueOf(result.getResultPenaltyAway()) + ") " + String.valueOf(result.getResultAway()));
                idTeam7Goals.setText("(" + String.valueOf(result.getResultPenaltyHome()) + ") " + String.valueOf(result.getResultHome()));
            } else {
                idTeam8Goals.setText(String.valueOf(result.getResultAway()));
                idTeam7Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!match2.isEnabled() && !match3.isEnabled() && !match1.isEnabled()) {
                next.setEnabled(true);
            }
        });
        DatabaseManager databaseManager = new DatabaseManager(this);
        next.setOnClickListener(view -> {
            Intent intent = new Intent(RoundOf8Activity.this, RoundOf4Activity.class);
            if(!loadedSave){
                List<Team> teams = databaseManager.GetAllTeams();
                for (int i = 1; i < 5; i++) {
                    MatchKnockoutStage result = results.get(i);
                    String winner;
                    if (result.getResultHome() < result.getResultAway()) {
                        winner = result.getAway();
                    } else if (result.getResultHome() > result.getResultAway()) {
                        winner = result.getHome();
                    } else {
                        if (result.getResultPenaltyHome() < result.getResultPenaltyAway()) {
                            winner = result.getAway();
                        } else {
                            winner = result.getHome();
                        }
                    }
                    for (Team team : teams) {
                        if (team.getName().equals(winner)) {
                            winnersToRoundOfFour.add(team);
                        }
                    }
                }
            }else{
                intent.putExtra("loadSave", gson.toJson(save));
            }

            intent.putExtra("Winners", gson.toJson(winnersToRoundOfFour));
            save.setRoundOfEightResults(results);
            save.setWinnersOfRoundOfEight(winnersToRoundOfFour);
            intent.putExtra("save", gson.toJson(save));
            intent.putExtra("uuid", uuid);

            startActivity(intent);
        });
        back.setOnClickListener(view -> {
            finish();
        });
    }

    public void SimulateMatch(View view, int matchId, String homeName, String awayName) {
        Random rand = new Random();
        int homeOpportunities = rand.nextInt(7);
        int awayOpportunities = rand.nextInt(7);
        double homeLuck = rand.nextInt(100) * 0.01;
        double awayLuck = rand.nextInt(100) * 0.01;
        int homeGoals = (int) Math.round(homeOpportunities * homeLuck);
        int awayGoals = (int) Math.round(awayOpportunities * awayLuck);
        int homePenalty = 0;
        int awayPenalty = 0;
        if (homeGoals == awayGoals) {
            homePenalty = rand.nextInt(6);
            awayPenalty = rand.nextInt(6);
            while (homePenalty == awayPenalty) {
                homePenalty = rand.nextInt(6);
                awayPenalty = rand.nextInt(6);
            }
        }
        results.put(matchId, new MatchKnockoutStage(matchId, homeName, awayName, homeGoals, awayGoals, homePenalty, awayPenalty));
    }
}