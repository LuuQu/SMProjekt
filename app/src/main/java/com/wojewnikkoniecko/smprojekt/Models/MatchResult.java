package com.wojewnikkoniecko.smprojekt.Models;

public class MatchResult {
    public int MatchId;
    public int homeGoals;
    public int awayGoals;
    public boolean isHomeWinner;
    public MatchResult(int MatchId, int homeGoals, int awayGoals, boolean isHomeWinner){
        this.MatchId = MatchId;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.isHomeWinner = isHomeWinner;
    }
}
