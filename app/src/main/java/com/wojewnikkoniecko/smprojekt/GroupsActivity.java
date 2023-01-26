package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wojewnikkoniecko.smprojekt.Models.Match;
import com.wojewnikkoniecko.smprojekt.Models.MatchResult;
import com.wojewnikkoniecko.smprojekt.Models.Statistics;
import com.wojewnikkoniecko.smprojekt.Models.Team;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupsActivity extends AppCompatActivity {

    HashMap<Integer, ArrayList<String>> teams = new HashMap<Integer, ArrayList<String>>();
    HashMap<Integer, ArrayList<Statistics>> teamsStats = new HashMap<Integer, ArrayList<Statistics>>();
    String[] groupsNames = {"A", "B", "C", "D", "E", "F", "G", "H"};
    int[] groups = {1, 2, 3, 4, 5, 6, 7, 8};
    int activeGroup = 1;
    String chosenTeam;
    DatabaseManager databaseManager = new DatabaseManager();
    List<Team> teamList = databaseManager.getTeams();
    List<Match> matchesList = databaseManager.getMatches();
    Team yourTeam;
    List<MatchResult> results = new ArrayList<>();
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        chosenTeam = getIntent().getStringExtra("ChosenTeam");
        String json = getIntent().getStringExtra("ChosenTeamMatches");
        if (json != null && !json.isEmpty()) {
            Type listType = new TypeToken<ArrayList<MatchResult>>() {
            }.getType();
            results = gson.fromJson(json, listType);
            int index = 1;
            int groupIndex = 1;
            ArrayList<Statistics> stats = new ArrayList<>();
            for (Team team : teamList) {
                Statistics statistics = new Statistics(team.TeamName, 0, 0, 0, 0, 0, 0, 0);
                for (Match match : matchesList) {
                    if (team.equals(match.Home)) {
                        for (MatchResult result : results) {
                            if (result.MatchId == match.MatchId) {
                                if (result.isHomeWinner) {
                                    //win
                                    statistics.points += 3;
                                    statistics.wins += 1;
                                    statistics.goalsScored += result.homeGoals;
                                    statistics.goalsLost += result.awayGoals;
                                    statistics.goalOutcome = statistics.goalsScored - statistics.goalsLost;
                                } else if (result.isDraw) {
                                    //remis
                                    statistics.points += 1;
                                    statistics.draws += 1;
                                    statistics.goalsScored += result.homeGoals;
                                    statistics.goalsLost += result.awayGoals;
                                    statistics.goalOutcome = statistics.goalsScored - statistics.goalsLost;
                                } else {
                                    //loss
                                    statistics.loses += 1;
                                    statistics.goalsScored += result.homeGoals;
                                    statistics.goalsLost += result.awayGoals;
                                    statistics.goalOutcome = statistics.goalsScored - statistics.goalsLost;
                                }
                            }
                        }
                    } else if (team.equals(match.Away)) {
                        for (MatchResult result : results) {
                            if (result.MatchId == match.MatchId) {
                                if (result.isDraw) {
                                    //remis
                                    statistics.points += 1;
                                    statistics.draws += 1;
                                    statistics.goalsScored += result.awayGoals;
                                    statistics.goalsLost += result.homeGoals;
                                    statistics.goalOutcome = statistics.goalsScored - statistics.goalsLost;
                                } else if (!result.isHomeWinner) {
                                    //win
                                    statistics.points += 3;
                                    statistics.wins += 1;
                                    statistics.goalsScored += result.awayGoals;
                                    statistics.goalsLost += result.homeGoals;
                                    statistics.goalOutcome = statistics.goalsScored - statistics.goalsLost;
                                } else {
                                    //loss
                                    statistics.loses += 1;
                                    statistics.goalsScored += result.awayGoals;
                                    statistics.goalsLost += result.homeGoals;
                                    statistics.goalOutcome = statistics.goalsScored - statistics.goalsLost;
                                }
                            }
                        }
                    }
                }
                stats.add(statistics);
                index++;
                if (index > 4) {
                    index = 1;
                    teamsStats.put(groupIndex, stats);
                    stats = new ArrayList<>();
                    groupIndex++;
                }

            }
        } else {
            //tu ma byc wszędzie po 0
        }
        List<Team> allTeams = databaseManager.getTeams();
        if (results != null) {
            for (Team item : allTeams) {
                if (chosenTeam.equals(item.TeamName)) {
                    yourTeam = item;
                }
            }
        } else {
            ArrayList<String> listOld = new ArrayList<>();
            int i = 0;
            int group = 1;
            for (Team item : allTeams) {
                listOld.add(item.TeamName);
                if (chosenTeam.equals(item.TeamName)) {
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


    }

    public void loadChoosingTeam(View view) {
        finish();
    }

    public void SetTeams(int group) {
        TextView team1 = findViewById(R.id.team1);
        TextView team2 = findViewById(R.id.team2);
        TextView team3 = findViewById(R.id.team3);
        TextView team4 = findViewById(R.id.team4);
        if (results != null) {

            ArrayList<Statistics> list = teamsStats.get(group);
            team1 = findViewById(R.id.team1);
            team2 = findViewById(R.id.team2);
            team3 = findViewById(R.id.team3);
            team4 = findViewById(R.id.team4);
            TextView groupText = findViewById(R.id.GroupName);

            TextView points1 = findViewById(R.id.points1);
            points1.setText(String.valueOf(list.get(0).points));
            TextView points2 = findViewById(R.id.points2);
            points2.setText(String.valueOf(list.get(1).points));
            TextView points3 = findViewById(R.id.points3);
            points3.setText(String.valueOf(list.get(2).points));
            TextView points4 = findViewById(R.id.points4);
            points4.setText(String.valueOf(list.get(3).points));

            TextView wins1 = findViewById(R.id.wins1);
            wins1.setText(String.valueOf(list.get(0).wins));
            TextView wins2 = findViewById(R.id.wins2);
            wins2.setText(String.valueOf(list.get(1).wins));
            TextView wins3 = findViewById(R.id.wins3);
            wins3.setText(String.valueOf(list.get(2).wins));
            TextView wins4 = findViewById(R.id.wins4);
            wins4.setText(String.valueOf(list.get(3).wins));

            TextView ties1 = findViewById(R.id.ties1);
            ties1.setText(String.valueOf(list.get(0).draws));
            TextView ties2 = findViewById(R.id.ties2);
            ties2.setText(String.valueOf(list.get(1).draws));
            TextView ties3 = findViewById(R.id.ties3);
            ties3.setText(String.valueOf(list.get(2).draws));
            TextView ties4 = findViewById(R.id.ties4);
            ties4.setText(String.valueOf(list.get(3).draws));

            TextView loss1 = findViewById(R.id.loss1);
            loss1.setText(String.valueOf(list.get(0).loses));
            TextView loss2 = findViewById(R.id.loss2);
            loss2.setText(String.valueOf(list.get(1).loses));
            TextView loss3 = findViewById(R.id.loss3);
            loss3.setText(String.valueOf(list.get(2).loses));
            TextView loss4 = findViewById(R.id.loss4);
            loss4.setText(String.valueOf(list.get(3).loses));

            TextView goalScored1 = findViewById(R.id.goalScored1);
            goalScored1.setText(String.valueOf(list.get(0).goalsScored));
            TextView goalScored2 = findViewById(R.id.goalScored2);
            goalScored2.setText(String.valueOf(list.get(1).goalsScored));
            TextView goalScored3 = findViewById(R.id.goalScored3);
            goalScored3.setText(String.valueOf(list.get(2).goalsScored));
            TextView goalScored4 = findViewById(R.id.goalScored4);
            goalScored4.setText(String.valueOf(list.get(3).goalsScored));

            TextView goalConceded1 = findViewById(R.id.goalConceded1);
            goalConceded1.setText(String.valueOf(list.get(0).goalsLost));
            TextView goalConceded2 = findViewById(R.id.goalConceded2);
            goalConceded2.setText(String.valueOf(list.get(1).goalsLost));
            TextView goalConceded3 = findViewById(R.id.goalConceded3);
            goalConceded3.setText(String.valueOf(list.get(2).goalsLost));
            TextView goalConceded4 = findViewById(R.id.goalConceded4);
            goalConceded4.setText(String.valueOf(list.get(3).goalsLost));

            TextView goalDifference1 = findViewById(R.id.goalDifference1);
            goalDifference1.setText(String.valueOf(list.get(0).goalOutcome));
            TextView goalDifference2 = findViewById(R.id.goalDifference2);
            goalDifference2.setText(String.valueOf(list.get(1).goalOutcome));
            TextView goalDifference3 = findViewById(R.id.goalDifference3);
            goalDifference3.setText(String.valueOf(list.get(2).goalOutcome));
            TextView goalDifference4 = findViewById(R.id.goalDifference4);
            goalDifference4.setText(String.valueOf(list.get(3).goalOutcome));

            groupText.setText("Grupa " + groupsNames[group - 1]);
            team1.setText(list.get(0).teamName);
            team2.setText(list.get(1).teamName);
            team3.setText(list.get(2).teamName);
            team4.setText(list.get(3).teamName);
            team1.setTextColor(Color.BLACK);
            team2.setTextColor(Color.BLACK);
            team3.setTextColor(Color.BLACK);
            team4.setTextColor(Color.BLACK);
            if (list.get(0).teamName.equals(chosenTeam)) {
                team1.setTextColor(Color.RED);
            } else if (list.get(1).teamName.equals(chosenTeam)) {
                team2.setTextColor(Color.RED);
            } else if (list.get(2).teamName.equals(chosenTeam)) {
                team3.setTextColor(Color.RED);
            } else if (list.get(3).teamName.equals(chosenTeam)) {
                team4.setTextColor(Color.RED);
            }
        } else {
            ArrayList<String> listOld = teams.get(group);
            TextView groupText = findViewById(R.id.GroupName);
            groupText.setText("Grupa " + groupsNames[group - 1]);
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
        Intent i = new Intent(this, SimulateGroupStage.class);
        i.putExtra("ChosenTeam", chosenTeam);
        i.putExtra("Group", yourTeam.Group);
        startActivity(i);
    }
}