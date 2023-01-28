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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RoundOf4Activity extends AppCompatActivity {
    Button finalmatch, thirdplace, semi1, semi2, next, back;
    TextView team1, team2, team3, team4, team5, team6, team7, team8;
    TextView idTeam1Goals, idTeam2Goals, idTeam3Goals, idTeam4Goals, idTeam5Goals, idTeam6Goals, idTeam7Goals, idTeam8Goals;
    TextView championText;
    DatabaseManager databaseManager = new DatabaseManager(this);
    List<Team> winners = new ArrayList<>();
    HashMap<Integer, MatchKnockoutStage> results = new HashMap<>();
    String loser1;
    String loser2;
    String winner1;
    String winner2;
    String champion;
    SaveData save;
    String uuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_of4);
        finalmatch = findViewById(R.id.buttonFinal);
        thirdplace = findViewById(R.id.button3rdPlace);
        semi1 = findViewById(R.id.buttonSemifinal1);
        semi2 = findViewById(R.id.buttonSemifinal2);
        next = findViewById(R.id.idButtonNext);
        back = findViewById(R.id.idButtonBack);
        next.setEnabled(false);
        thirdplace.setEnabled(false);
        finalmatch.setEnabled(false);
        Gson gson = new Gson();
        String json = getIntent().getStringExtra("Winners");
        Type listType = new TypeToken<List<Team>>() {
        }.getType();
        winners = gson.fromJson(json, listType);

        String jsonSave = getIntent().getStringExtra("save");
        save = gson.fromJson(jsonSave, SaveData.class);
        uuid = getIntent().getStringExtra("uuid");

        championText = findViewById(R.id.idChampion);

        team1 = findViewById(R.id.idTeam1);
        team2 = findViewById(R.id.idTeam2);
        team3 = findViewById(R.id.idTeam3);
        team4 = findViewById(R.id.idTeam4);

        team5 = findViewById(R.id.idTeam5);
        team6 = findViewById(R.id.idTeam6);
        team7 = findViewById(R.id.idTeam7);
        team8 = findViewById(R.id.idTeam8);

        team5.setText(winners.get(0).getName());
        team6.setText(winners.get(1).getName());
        team7.setText(winners.get(2).getName());
        team8.setText(winners.get(3).getName());

        finalmatch.setOnClickListener(view -> {
            //losuje wynik meczu 1
            finalmatch.setEnabled(false);
            int matchId = 1;
            SimulateMatch(view, matchId, winner1, winner2);
            MatchKnockoutStage result = results.get(matchId);
            if(result.getResultHome() < result.getResultAway()){
                champion = result.getAway();
            } else if(result.getResultHome() > result.getResultAway()){
                champion = result.getHome();
            }else{
                if(result.getResultPenaltyHome() < result.getResultPenaltyAway()){
                    champion = result.getAway();
                }
                else{
                    champion = result.getHome();
                }
            }
            idTeam1Goals = findViewById(R.id.idTeam1Goals);
            idTeam2Goals = findViewById(R.id.idTeam2Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam2Goals.setText(String.valueOf(result.getResultAway() + " (" + String.valueOf(result.getResultPenaltyAway()) + ")"));
                idTeam1Goals.setText(String.valueOf(result.getResultHome() + " (" + String.valueOf(result.getResultPenaltyHome()) + ")"));
            } else {
                idTeam2Goals.setText(String.valueOf(result.getResultAway()));
                idTeam1Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!thirdplace.isEnabled()) {
                next.setEnabled(true);
            }
            championText.setText(champion);
        });
        thirdplace.setOnClickListener(view -> {
            //losuje wynik meczu 2
            thirdplace.setEnabled(false);
            int matchId = 2;
            SimulateMatch(view, matchId, loser1, loser2);
            MatchKnockoutStage result = results.get(matchId);
            idTeam3Goals = findViewById(R.id.idTeam3Goals);
            idTeam4Goals = findViewById(R.id.idTeam4Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam4Goals.setText("(" + String.valueOf(result.getResultPenaltyAway()) + ") " + String.valueOf(result.getResultAway()));
                idTeam3Goals.setText("(" + String.valueOf(result.getResultPenaltyHome()) + ") " + String.valueOf(result.getResultHome()));
            } else {
                idTeam4Goals.setText(String.valueOf(result.getResultAway()));
                idTeam3Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!finalmatch.isEnabled()) {
                next.setEnabled(true);
            }

        });
        semi1.setOnClickListener(view -> {
            //losuje wynik meczu półfinałowego 1
            semi1.setEnabled(false);
            int matchId = 3;
            SimulateMatch(view, matchId, winners.get(0).getName(), winners.get(1).getName());
            MatchKnockoutStage result = results.get(matchId);
            if(result.getResultHome() < result.getResultAway()){
                winner1 = result.getAway();
                loser1 = result.getHome();
            } else if(result.getResultHome() > result.getResultAway()){
                winner1 = result.getHome();
                loser1 = result.getAway();
            }else{
                if(result.getResultPenaltyHome() < result.getResultPenaltyAway()){
                    winner1 = result.getAway();
                    loser1 = result.getHome();
                }
                else{
                    winner1 = result.getHome();
                    loser1 = result.getAway();
                }
            }
            idTeam5Goals = findViewById(R.id.idTeam5Goals);
            idTeam6Goals = findViewById(R.id.idTeam6Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam6Goals.setText(String.valueOf(result.getResultAway() + " (" + String.valueOf(result.getResultPenaltyAway()) + ")"));
                idTeam5Goals.setText(String.valueOf(result.getResultHome() + " (" + String.valueOf(result.getResultPenaltyHome()) + ")"));
            } else {
                idTeam6Goals.setText(String.valueOf(result.getResultAway()));
                idTeam5Goals.setText(String.valueOf(result.getResultHome()));

            }
            team1.setText(winner1);
            team3.setText(loser1);
            if (!semi2.isEnabled()) {
                finalmatch.setEnabled(true);
                thirdplace.setEnabled(true);
            }
        });
        semi2.setOnClickListener(view -> {
            //losuje wynik meczu półfinałowego 2
            semi2.setEnabled(false);
            int matchId = 4;
            SimulateMatch(view, matchId, winners.get(2).getName(), winners.get(3).getName());
            MatchKnockoutStage result = results.get(matchId);
            if(result.getResultHome() < result.getResultAway()){
                winner2 = result.getAway();
                loser2 = result.getHome();
            } else if(result.getResultHome() > result.getResultAway()){
                winner2 = result.getHome();
                loser2 = result.getAway();
            }else{
                if(result.getResultPenaltyHome() < result.getResultPenaltyAway()){
                    winner2 = result.getAway();
                    loser2 = result.getHome();
                }
                else{
                    winner2 = result.getHome();
                    loser2 = result.getAway();
                }
            }
            idTeam7Goals = findViewById(R.id.idTeam7Goals);
            idTeam8Goals = findViewById(R.id.idTeam8Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam8Goals.setText("(" + String.valueOf(result.getResultPenaltyAway()) + ") " + String.valueOf(result.getResultAway()));
                idTeam7Goals.setText("(" + String.valueOf(result.getResultPenaltyHome()) + ") " + String.valueOf(result.getResultHome()));
            } else {
                idTeam8Goals.setText(String.valueOf(result.getResultAway()));
                idTeam7Goals.setText(String.valueOf(result.getResultHome()));
            }
            team2.setText(winner2);
            team4.setText(loser2);
            if (!semi1.isEnabled()) {
                finalmatch.setEnabled(true);
                thirdplace.setEnabled(true);
            }
        });
        next.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            save.setRoundOfFourResults(results);
            save.setLoser1(loser1);
            save.setLoser2(loser2);
            save.setWinner1(winner1);
            save.setWinner2(winner2);
            save.setChampion(champion);

            String savejson = gson.toJson(save);
            Calendar cal = Calendar.getInstance();
            databaseManager.SetSave(savejson,cal.getTime().toString());
            //uuid

            startActivity(intent);
            finish();
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