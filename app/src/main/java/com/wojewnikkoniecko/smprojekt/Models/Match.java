package com.wojewnikkoniecko.smprojekt.Models;

public class Match {
    public int MatchId;
    public String Home;
    public String Away;

    public Match(int MatchId, String Home,
                 String Away){
        this.MatchId = MatchId;
        this.Home = Home;
        this.Away = Away;
    }
}
