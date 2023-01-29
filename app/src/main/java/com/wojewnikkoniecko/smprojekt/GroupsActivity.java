package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wojewnikkoniecko.smprojekt.Models.Match;
import com.wojewnikkoniecko.smprojekt.Models.SaveData;
import com.wojewnikkoniecko.smprojekt.Models.Statistics;
import com.wojewnikkoniecko.smprojekt.Models.Team;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GroupsActivity extends AppCompatActivity {

    HashMap<Integer, ArrayList<String>> teams = new HashMap<Integer, ArrayList<String>>();
    HashMap<Integer, ArrayList<Statistics>> teamsStats = new HashMap<Integer, ArrayList<Statistics>>();
    String[] groupsNames = {"A", "B", "C", "D", "E", "F", "G", "H"};
    int[] groups = {1, 2, 3, 4, 5, 6, 7, 8};
    int activeGroup = 1;
    String chosenTeam;
    DatabaseManager databaseManager = new DatabaseManager(this);
    List<Team> teamList;
    List<Match> matchesList;
    Team yourTeam;
    Gson gson = new Gson();
    Boolean isSimulated = false;
    Boolean buttonIsMoved = false;
    String jsonToSave;
    UUID uuid = UUID.randomUUID();
    SaveData save = new SaveData();
    List<Team> winners = new ArrayList<>();
    String savejson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamList = databaseManager.GetAllTeams();
        setContentView(R.layout.activity_groups);

        matchesList = databaseManager.GetAllMatches();
        savejson = getIntent().getStringExtra("save");
        Button RoundOfSixteen = findViewById(R.id.RoundOfSixteen);
        if (savejson != null) {
            Button play = findViewById(R.id.Play);
            play.setVisibility(View.GONE);
            RoundOfSixteen.setVisibility(View.VISIBLE);
            isSimulated = true;
            save = gson.fromJson(savejson, SaveData.class);
            matchesList = save.getGroupResults();
            for(Match match : matchesList) {
                databaseManager.UpdateMatch(match);
            }
            winners = save.getWinnersOfGroupStage();
            IsSimulated();
            SetTeams(activeGroup);

        } else {
            chosenTeam = getIntent().getStringExtra("ChosenTeam");
            String json = getIntent().getStringExtra("ChosenTeamMatches");
            if (matchesList.get(0).getResultHome() != -1) {

            }
            ArrayList<String> listOld = new ArrayList<>();
            int i = 0;
            int group = 1;
            for (Team item : teamList) {
                listOld.add(item.getName());
                if (chosenTeam.equals(item.getName())) {
                    yourTeam = item;
                }
                if (i == 3) {
                    i = 0;
                    teams.put(group, listOld);
                    listOld = new ArrayList<>();
                    group++;
                } else {
                    i++;
                }
            }
            int x = i * 2;
        }
        RoundOfSixteen.setVisibility(View.GONE);
        SetTeams(activeGroup);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isSimulated) {
            matchesList = databaseManager.GetAllMatches();
            IsSimulated();
            SetTeams(activeGroup);
        }
    }

    public void loadChoosingTeam(View view) {
        finish();
    }

    public void IsSimulated() {
        int index = 1;
        int groupIndex = 1;
        ArrayList<Statistics> stats = new ArrayList<>();
        for (Team team : teamList) {
            Statistics statistics = new Statistics();
            statistics.setTeamName(team.getName());
            for (Match match : matchesList) {
                if(match.getResultHome() == -1) {
                    continue;
                }
                if (team.getGroup().equals(match.getHome())) {
                    if (match.getResultHome() > match.getResultAway()) {
                        //win
                        statistics.setPoints(statistics.getPoints() + 3);
                        statistics.setWins(statistics.getWins() + 1);
                        statistics.setGoalsScored(statistics.getGoalsScored() + match.getResultHome());
                        statistics.setGoalsLost(statistics.getGoalsLost() + match.getResultAway());
                        statistics.setGoalOutcome(statistics.getGoalsScored() - statistics.getGoalsLost());
                    } else if (match.getResultHome() == match.getResultAway()) {
                        //remis
                        statistics.setPoints(statistics.getPoints() + 1);
                        statistics.setDraws(statistics.getDraws() + 1);
                        statistics.setGoalsScored(statistics.getGoalsScored() + match.getResultHome());
                        statistics.setGoalsLost(statistics.getGoalsLost() + match.getResultAway());
                        statistics.setGoalOutcome(statistics.getGoalsScored() - statistics.getGoalsLost());
                    } else {
                        //loss
                        statistics.setLoses(statistics.getLoses() + 1);
                        statistics.setGoalsScored(statistics.getGoalsScored() + match.getResultHome());
                        statistics.setGoalsLost(statistics.getGoalsLost() + match.getResultAway());
                        statistics.setGoalOutcome(statistics.getGoalsScored() - statistics.getGoalsLost());
                    }
                } else if (team.getGroup().equals(match.getAway())) {
                    if (match.getResultHome() == match.getResultAway()) {
                        //remis
                        statistics.setPoints(statistics.getPoints() + 1);
                        statistics.setDraws(statistics.getDraws() + 1);
                        statistics.setGoalsScored(statistics.getGoalsScored() + match.getResultAway());
                        statistics.setGoalsLost(statistics.getGoalsLost() + match.getResultHome());
                        statistics.setGoalOutcome(statistics.getGoalsScored() - statistics.getGoalsLost());
                    } else if (match.getResultHome() < match.getResultAway()) {
                        //win
                        statistics.setPoints(statistics.getPoints() + 3);
                        statistics.setWins(statistics.getWins() + 1);
                        statistics.setGoalsScored(statistics.getGoalsScored() + match.getResultAway());
                        statistics.setGoalsLost(statistics.getGoalsLost() + match.getResultHome());
                        statistics.setGoalOutcome(statistics.getGoalsScored() - statistics.getGoalsLost());
                    } else {
                        //loss
                        statistics.setLoses(statistics.getLoses() + 1);
                        statistics.setGoalsScored(statistics.getGoalsScored() + match.getResultAway());
                        statistics.setGoalsLost(statistics.getGoalsLost() + match.getResultHome());
                        statistics.setGoalOutcome(statistics.getGoalsScored() - statistics.getGoalsLost());
                    }
                }
            }
            stats.add(statistics);
            index++;
            if (index > 4) {
                index = 1;
                Collections.sort(stats, (o1, o2) -> {
                    if (o1.getPoints() != o2.getPoints()) {
                        return o2.getPoints() - o1.getPoints();
                    }
                    return o2.getGoalOutcome() - o1.getGoalOutcome();
                });
                teamsStats.put(groupIndex, stats);
                stats = new ArrayList<>();
                groupIndex++;
            }
        }
    }

    public void SetTeams(int group) {
        TextView team1 = findViewById(R.id.team1);
        TextView team2 = findViewById(R.id.team2);
        TextView team3 = findViewById(R.id.team3);
        TextView team4 = findViewById(R.id.team4);
        if (isSimulated == true) {
            Button RoundOfSixteen = findViewById(R.id.RoundOfSixteen);
            RoundOfSixteen.setVisibility(View.VISIBLE);
            ArrayList<Statistics> list = teamsStats.get(group);
            team1 = findViewById(R.id.team1);
            team2 = findViewById(R.id.team2);
            team3 = findViewById(R.id.team3);
            team4 = findViewById(R.id.team4);
            TextView groupText = findViewById(R.id.GroupName);

            TextView points1 = findViewById(R.id.points1);
            points1.setText(String.valueOf(list.get(0).getPoints()));
            TextView points2 = findViewById(R.id.points2);
            points2.setText(String.valueOf(list.get(1).getPoints()));
            TextView points3 = findViewById(R.id.points3);
            points3.setText(String.valueOf(list.get(2).getPoints()));
            TextView points4 = findViewById(R.id.points4);
            points4.setText(String.valueOf(list.get(3).getPoints()));

            TextView wins1 = findViewById(R.id.wins1);
            wins1.setText(String.valueOf(list.get(0).getWins()));
            TextView wins2 = findViewById(R.id.wins2);
            wins2.setText(String.valueOf(list.get(1).getWins()));
            TextView wins3 = findViewById(R.id.wins3);
            wins3.setText(String.valueOf(list.get(2).getWins()));
            TextView wins4 = findViewById(R.id.wins4);
            wins4.setText(String.valueOf(list.get(3).getWins()));

            TextView ties1 = findViewById(R.id.ties1);
            ties1.setText(String.valueOf(list.get(0).getDraws()));
            TextView ties2 = findViewById(R.id.ties2);
            ties2.setText(String.valueOf(list.get(1).getDraws()));
            TextView ties3 = findViewById(R.id.ties3);
            ties3.setText(String.valueOf(list.get(2).getDraws()));
            TextView ties4 = findViewById(R.id.ties4);
            ties4.setText(String.valueOf(list.get(3).getDraws()));

            TextView loss1 = findViewById(R.id.loss1);
            loss1.setText(String.valueOf(list.get(0).getLoses()));
            TextView loss2 = findViewById(R.id.loss2);
            loss2.setText(String.valueOf(list.get(1).getLoses()));
            TextView loss3 = findViewById(R.id.loss3);
            loss3.setText(String.valueOf(list.get(2).getLoses()));
            TextView loss4 = findViewById(R.id.loss4);
            loss4.setText(String.valueOf(list.get(3).getLoses()));

            TextView goalScored1 = findViewById(R.id.goalScored1);
            goalScored1.setText(String.valueOf(list.get(0).getGoalsScored()));
            TextView goalScored2 = findViewById(R.id.goalScored2);
            goalScored2.setText(String.valueOf(list.get(1).getGoalsScored()));
            TextView goalScored3 = findViewById(R.id.goalScored3);
            goalScored3.setText(String.valueOf(list.get(2).getGoalsScored()));
            TextView goalScored4 = findViewById(R.id.goalScored4);
            goalScored4.setText(String.valueOf(list.get(3).getGoalsScored()));

            TextView goalConceded1 = findViewById(R.id.goalConceded1);
            goalConceded1.setText(String.valueOf(list.get(0).getGoalsLost()));
            TextView goalConceded2 = findViewById(R.id.goalConceded2);
            goalConceded2.setText(String.valueOf(list.get(1).getGoalsLost()));
            TextView goalConceded3 = findViewById(R.id.goalConceded3);
            goalConceded3.setText(String.valueOf(list.get(2).getGoalsLost()));
            TextView goalConceded4 = findViewById(R.id.goalConceded4);
            goalConceded4.setText(String.valueOf(list.get(3).getGoalsLost()));

            TextView goalDifference1 = findViewById(R.id.goalDifference1);
            goalDifference1.setText(String.valueOf(list.get(0).getGoalOutcome()));
            TextView goalDifference2 = findViewById(R.id.goalDifference2);
            goalDifference2.setText(String.valueOf(list.get(1).getGoalOutcome()));
            TextView goalDifference3 = findViewById(R.id.goalDifference3);
            goalDifference3.setText(String.valueOf(list.get(2).getGoalOutcome()));
            TextView goalDifference4 = findViewById(R.id.goalDifference4);
            goalDifference4.setText(String.valueOf(list.get(3).getGoalOutcome()));

            groupText.setText("Group " + groupsNames[group - 1]);
            team1.setText(list.get(0).getTeamName());
            team2.setText(list.get(1).getTeamName());
            team3.setText(list.get(2).getTeamName());
            team4.setText(list.get(3).getTeamName());
            team1.setTextColor(Color.BLACK);
            team2.setTextColor(Color.BLACK);
            team3.setTextColor(Color.BLACK);
            team4.setTextColor(Color.BLACK);
            if (list.get(0).getTeamName().equals(chosenTeam)) {
                team1.setTextColor(Color.RED);
            } else if (list.get(1).getTeamName().equals(chosenTeam)) {
                team2.setTextColor(Color.RED);
            } else if (list.get(2).getTeamName().equals(chosenTeam)) {
                team3.setTextColor(Color.RED);
            } else if (list.get(3).getTeamName().equals(chosenTeam)) {
                team4.setTextColor(Color.RED);
            }
            if (!buttonIsMoved) {
                Button play = findViewById(R.id.Play);
                play.setText("PLay again");
                buttonIsMoved = true;
            }
        } else {

            ArrayList<String> listOld = teams.get(group);
            TextView groupText = findViewById(R.id.GroupName);
            groupText.setText("Group " + groupsNames[group - 1]);
            team1.setText(listOld.get(0));
            team2.setText(listOld.get(1));
            team3.setText(listOld.get(2));
            team4.setText(listOld.get(3));
            team1.setTextColor(Color.BLACK);
            team2.setTextColor(Color.BLACK);
            team3.setTextColor(Color.BLACK);
            team4.setTextColor(Color.BLACK);
            if (listOld.get(0).equals(chosenTeam)) {
                team1.setTextColor(Color.RED);
            } else if (listOld.get(1).equals(chosenTeam)) {
                team2.setTextColor(Color.RED);
            } else if (listOld.get(2).equals(chosenTeam)) {
                team3.setTextColor(Color.RED);
            } else if (listOld.get(3).equals(chosenTeam)) {
                team4.setTextColor(Color.RED);
            }
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
        Intent intent = new Intent(this, SimulateGroupStage.class);
        intent.putExtra("ChosenTeam", chosenTeam);
        intent.putExtra("Group", yourTeam.getGroup());
        isSimulated = true;
        startActivity(intent);
    }

    public void btnRoundOfSixteenPressed(View view) {
        Intent intent = new Intent(this, RoundOf16Activity.class);
        if (savejson == null) {
            for (int i = 1; i < 9; i++) {
                ArrayList<Statistics> list = teamsStats.get(i);
                Statistics team1 = list.get(0);
                Statistics team2 = list.get(1);
                for (Team team : teamList) {
                    if (team1.getTeamName().equals(team.getName())) {
                        winners.add(team);
                    } else if (team2.getTeamName().equals(team.getName())) {
                        winners.add(team);
                    }
                }
            }
            Gson gson = new Gson();
            save.setWinnersOfGroupStage(winners);
            save.setGroupResults(databaseManager.GetAllMatches());
            save.setChosenTeam(chosenTeam);
        }
        else{
            intent.putExtra("loadSave", gson.toJson(save));
        }
        intent.putExtra("Winners", gson.toJson(winners));
        intent.putExtra("save", gson.toJson(save));
        intent.putExtra("uuid", uuid.toString());
        startActivity(intent);
    }
    public void simulateSingleGroup(View view) {
        isSimulated = true;
        Intent intent = new Intent(this,SimulateSingleGroup.class);
        intent.putExtra("group", groupsNames[activeGroup-1]);
        intent.putExtra("favTeam", chosenTeam);
        startActivity(intent);
    }
}