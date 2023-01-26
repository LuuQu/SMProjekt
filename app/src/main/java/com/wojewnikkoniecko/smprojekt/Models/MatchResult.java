package com.wojewnikkoniecko.smprojekt.Models;

public class MatchResult {
    public int MatchId;
    public int homeGoals;
    public int awayGoals;
    public boolean isHomeWinner;
    public boolean isDraw;
    public int homeOpportunities;
    public int awayOpportunities;
    public MatchResult(int MatchId, int homeGoals, int awayGoals, boolean isHomeWinner, boolean isDraw, int homeOpportunities, int awayOpportunities){
        this.MatchId = MatchId;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.isHomeWinner = isHomeWinner;
        this.isDraw = isDraw;
        this.homeOpportunities = homeOpportunities;
        this.awayOpportunities = awayOpportunities;
    }
}
