package com.wojewnikkoniecko.smprojekt.Models;

import java.util.ArrayList;

public class Match{
    public static ArrayList<Match> matchArrayList = new ArrayList<>();
    private int MatchId;
    private String Home;
    private String Away;
    private int ResultHome;
    private int ResultAway;

    public Match(int matchId, String home, String away,int resultHome, int resultAway) {
        MatchId = matchId;
        Home = home;
        Away = away;
        ResultHome = resultHome;
        ResultAway = resultAway;
    }
    public Match() {}

    public int getMatchId() {
        return MatchId;
    }

    public void setMatchId(int matchId) {
        MatchId = matchId;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }

    public String getAway() {
        return Away;
    }

    public void setAway(String away) {
        Away = away;
    }

    public int getResultHome() {
        return ResultHome;
    }

    public void setResultHome(int resultHome) {
        ResultHome = resultHome;
    }

    public int getResultAway() {
        return ResultAway;
    }

    public void setResultAway(int resultAway) {
        ResultAway = resultAway;
    }
}
