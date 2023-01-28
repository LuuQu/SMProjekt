package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
import java.util.UUID;

public class RoundOf16Activity extends AppCompatActivity {
    TextView team1, team2, team3, team4, team5, team6, team7, team8, team9, team10, team11, team12, team13, team14, team15, team16;
    Button match1, match2, match3, match4, match5, match6, match7, match8, next, back;
    List<Team> winners = new ArrayList<>();
    HashMap<Integer, MatchKnockoutStage> results = new HashMap<>();
    List<Team> winnersToRoundOfEight = new ArrayList<>();
    SaveData save;
    Gson gson = new Gson();
    Boolean loadedSave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_of16);


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
        String savejson = getIntent().getStringExtra("loadSave");
        if (savejson != null) {
            save = gson.fromJson(savejson, SaveData.class);
            results = save.getRoundOfSixteenResults();
            winnersToRoundOfEight = save.getWinnersOfRoundOfSixteen();
            loadedSave = true;
            winners = save.getWinnersOfGroupStage();
            /*match1.setEnabled(false);
            match2.setEnabled(false);
            match3.setEnabled(false);
            match4.setEnabled(false);
            match5.setEnabled(false);
            match6.setEnabled(false);
            match7.setEnabled(false);
            match8.setEnabled(false);*/
            //back.setEnabled(false);
           /* next.setEnabled(true);*/

            match1.setText("Result");
            match2.setText("Result");
            match3.setText("Result");
            match4.setText("Result");
            match5.setText("Result");
            match6.setText("Result");
            match7.setText("Result");
            match8.setText("Result");
        } else {
            String jsonSave = getIntent().getStringExtra("save");
            save = gson.fromJson(jsonSave, SaveData.class);
        }


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
        next.setEnabled(false);
        match1.setOnClickListener(view -> {
            //losuje wynik meczu 1
            match1.setEnabled(false);
            match1.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int matchId = 1;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(0).getName(), winners.get(3).getName());
            }
            MatchKnockoutStage result = results.get(matchId);
            TextView idTeam1Goals = findViewById(R.id.idTeam1Goals);
            TextView idTeam2Goals = findViewById(R.id.idTeam2Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam2Goals.setText(String.valueOf(result.getResultAway() + " (" + String.valueOf(result.getResultPenaltyAway()) + ")"));
                idTeam1Goals.setText(String.valueOf(result.getResultHome() + " (" + String.valueOf(result.getResultPenaltyHome()) + ")"));
            } else {
                idTeam2Goals.setText(String.valueOf(result.getResultAway()));
                idTeam1Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
                next.setBackgroundColor(ContextCompat.getColor(this, R.color.enabledButton));
            }
        });
        match2.setOnClickListener(view -> {
            //losuje wynik meczu 2
            match2.setEnabled(false);
            match2.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int matchId = 2;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(4).getName(), winners.get(7).getName());
            }
            MatchKnockoutStage result = results.get(matchId);
            TextView idTeam3Goals = findViewById(R.id.idTeam3Goals);
            TextView idTeam4Goals = findViewById(R.id.idTeam4Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam4Goals.setText(String.valueOf(result.getResultAway() + " (" + String.valueOf(result.getResultPenaltyAway()) + ")"));
                idTeam3Goals.setText(String.valueOf(result.getResultHome() + " (" + String.valueOf(result.getResultPenaltyHome()) + ")"));
            } else {
                idTeam4Goals.setText(String.valueOf(result.getResultAway()));
                idTeam3Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!match1.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
                next.setBackgroundColor(ContextCompat.getColor(this, R.color.enabledButton));
            }

        });
        match3.setOnClickListener(view -> {
            //losuje wynik meczu 3
            match3.setEnabled(false);
            match3.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int matchId = 3;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(8).getName(), winners.get(11).getName());
            }MatchKnockoutStage result = results.get(matchId);
            TextView idTeam5Goals = findViewById(R.id.idTeam5Goals);
            TextView idTeam6Goals = findViewById(R.id.idTeam6Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam6Goals.setText(String.valueOf(result.getResultAway() + " (" + String.valueOf(result.getResultPenaltyAway()) + ")"));
                idTeam5Goals.setText(String.valueOf(result.getResultHome() + " (" + String.valueOf(result.getResultPenaltyHome()) + ")"));
            } else {
                idTeam6Goals.setText(String.valueOf(result.getResultAway()));
                idTeam5Goals.setText(String.valueOf(result.getResultHome()));

            }
            if (!match2.isEnabled() && !match1.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
                next.setBackgroundColor(ContextCompat.getColor(this, R.color.enabledButton));
            }

        });
        match4.setOnClickListener(view -> {
            //losuje wynik meczu 4
            match4.setEnabled(false);
            match4.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int matchId = 4;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(12).getName(), winners.get(15).getName());
            }MatchKnockoutStage result = results.get(matchId);
            TextView idTeam7Goals = findViewById(R.id.idTeam7Goals);
            TextView idTeam8Goals = findViewById(R.id.idTeam8Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam8Goals.setText(String.valueOf(result.getResultAway() + "(" + String.valueOf(result.getResultPenaltyAway()) + ")"));
                idTeam7Goals.setText(String.valueOf(result.getResultHome() + "(" + String.valueOf(result.getResultPenaltyHome()) + ")"));
            } else {
                idTeam8Goals.setText(String.valueOf(result.getResultAway()));
                idTeam7Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!match2.isEnabled() && !match3.isEnabled() && !match1.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
                next.setBackgroundColor(ContextCompat.getColor(this, R.color.enabledButton));
            }

        });
        match5.setOnClickListener(view -> {
            //losuje wynik meczu 5
            match5.setEnabled(false);
            match5.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int matchId = 5;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(2).getName(), winners.get(1).getName());
            }MatchKnockoutStage result = results.get(matchId);
            TextView idTeam9Goals = findViewById(R.id.idTeam9Goals);
            TextView idTeam10Goals = findViewById(R.id.idTeam10Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam10Goals.setText("(" + String.valueOf(result.getResultPenaltyAway()) + ") " + String.valueOf(result.getResultAway()));
                idTeam9Goals.setText("(" + String.valueOf(result.getResultPenaltyHome()) + ") " + String.valueOf(result.getResultHome()));
            } else {
                idTeam10Goals.setText(String.valueOf(result.getResultAway()));
                idTeam9Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match1.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
                next.setBackgroundColor(ContextCompat.getColor(this, R.color.enabledButton));
            }

        });
        match6.setOnClickListener(view -> {
            //losuje wynik meczu 6
            match6.setEnabled(false);
            match6.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int matchId = 6;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(6).getName(), winners.get(5).getName());
            }MatchKnockoutStage result = results.get(matchId);
            TextView idTeam11Goals = findViewById(R.id.idTeam11Goals);
            TextView idTeam12Goals = findViewById(R.id.idTeam12Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam12Goals.setText("(" + String.valueOf(result.getResultPenaltyAway()) + ") " + String.valueOf(result.getResultAway()));
                idTeam11Goals.setText("(" + String.valueOf(result.getResultPenaltyHome()) + ") " + String.valueOf(result.getResultHome()));
            } else {
                idTeam12Goals.setText(String.valueOf(result.getResultAway()));
                idTeam11Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match1.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
                next.setBackgroundColor(ContextCompat.getColor(this, R.color.enabledButton));
            }

        });
        match7.setOnClickListener(view -> {
            //losuje wynik meczu 7
            match7.setEnabled(false);
            match7.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int matchId = 7;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(10).getName(), winners.get(9).getName());
            }MatchKnockoutStage result = results.get(matchId);
            TextView idTeam13Goals = findViewById(R.id.idTeam13Goals);
            TextView idTeam14Goals = findViewById(R.id.idTeam14Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam14Goals.setText(" (" + String.valueOf(result.getResultPenaltyAway()) + ") " + String.valueOf(result.getResultAway()));
                idTeam13Goals.setText(" (" + String.valueOf(result.getResultPenaltyHome()) + ") " + String.valueOf(result.getResultHome()));
            } else {
                idTeam14Goals.setText(String.valueOf(result.getResultAway()));
                idTeam13Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match1.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
                next.setBackgroundColor(ContextCompat.getColor(this, R.color.enabledButton));
            }

        });
        match8.setOnClickListener(view -> {
            //losuje wynik meczu 8
            match8.setEnabled(false);
            match8.setBackgroundColor(ContextCompat.getColor(this, R.color.disabledButton));
            int matchId = 8;
            if (!loadedSave) {
                SimulateMatch(view, matchId, winners.get(14).getName(), winners.get(13).getName());
            }MatchKnockoutStage result = results.get(matchId);
            TextView idTeam15Goals = findViewById(R.id.idTeam15Goals);
            TextView idTeam16Goals = findViewById(R.id.idTeam16Goals);
            if (result.getResultHome() == result.getResultAway()) {
                idTeam16Goals.setText("(" + String.valueOf(result.getResultPenaltyAway()) + ") " + String.valueOf(result.getResultAway()));
                idTeam15Goals.setText("(" + String.valueOf(result.getResultPenaltyHome()) + ") " + String.valueOf(result.getResultHome()));
            } else {
                idTeam16Goals.setText(String.valueOf(result.getResultAway()));
                idTeam15Goals.setText(String.valueOf(result.getResultHome()));
            }
            if (!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match1.isEnabled()) {
                next.setEnabled(true);
                next.setBackgroundColor(ContextCompat.getColor(this, R.color.enabledButton));
            }

        });
        DatabaseManager databaseManager = new DatabaseManager(this);
        next.setOnClickListener(view -> {
            Intent intent = new Intent(RoundOf16Activity.this, RoundOf8Activity.class);
            if(!loadedSave){
                List<Team> teams = databaseManager.GetAllTeams();
                for (int i = 1; i < 9; i++) {
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
                            winnersToRoundOfEight.add(team);
                        }
                    }
                }
            }
            else{
                intent.putExtra("loadSave", gson.toJson(save));
            }
            intent.putExtra("Winners", gson.toJson(winnersToRoundOfEight));
            save.setRoundOfSixteenResults(results);
            save.setWinnersOfRoundOfSixteen(winnersToRoundOfEight);
            intent.putExtra("save", gson.toJson(save));

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