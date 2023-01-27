package com.wojewnikkoniecko.smprojekt.Models;

public class Statistics {
    private int id;
    private String teamName;
    private int points;
    private int wins;
    private int draws;
    private int loses;
    private int goalsScored;
    private int goalsLost;
    private int goalOutcome;
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
    public Statistics() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsLost() {
        return goalsLost;
    }

    public void setGoalsLost(int goalsLost) {
        this.goalsLost = goalsLost;
    }

    public int getGoalOutcome() {
        return goalOutcome;
    }

    public void setGoalOutcome(int goalOutcome) {
        this.goalOutcome = goalOutcome;
    }
}
