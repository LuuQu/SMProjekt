package com.wojewnikkoniecko.smprojekt.Models;

public class Statistics {
    public String teamName;
    public int points;
    public int wins;
    public int draws;
    public int loses;
    public int goalsScored;
    public int goalsLost;
    public int goalOutcome;
    public Statistics(String teamName, int points, int wins,
                      int draws, int loses, int goalsScored,
                      int goalsLost, int goalOutcome)
    {
        this.teamName = teamName;
        this.points = points;
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
        this.goalsScored = goalsScored;
        this.goalsLost = goalsLost;
        this.goalOutcome = goalOutcome;
    }

}
