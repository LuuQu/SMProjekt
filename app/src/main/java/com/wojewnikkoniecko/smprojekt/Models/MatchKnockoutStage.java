package com.wojewnikkoniecko.smprojekt.Models;

public class MatchKnockoutStage {
    private int MatchId;
    private String Home;
    private String Away;
    private int ResultHome;
    private int ResultAway;
    private int ResultPenaltyHome;
    private int ResultPenaltyAway;

    public MatchKnockoutStage(int matchId, String home, String away, int resultHome, int resultAway, int resultPenaltyHome, int resultPenaltyAway) {
        MatchId = matchId;
        Home = home;
        Away = away;
        ResultHome = resultHome;
        ResultAway = resultAway;
        ResultPenaltyHome = resultPenaltyHome;
        ResultPenaltyAway = resultPenaltyAway;
    }


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

    public int getResultPenaltyHome() {
        return ResultPenaltyHome;
    }

    public void setResultPenaltyHome(int resultPenaltyHome) {
        ResultPenaltyHome = resultPenaltyHome;
    }

    public int getResultPenaltyAway() {
        return ResultPenaltyAway;
    }

    public void setResultPenaltyAway(int resultPenaltyAway) {
        ResultPenaltyAway = resultPenaltyAway;
    }
}
