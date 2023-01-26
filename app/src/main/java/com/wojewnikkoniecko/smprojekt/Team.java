package com.wojewnikkoniecko.smprojekt;

public class Team {
    public int TeamID;
    public String TeamName;
    public String Group;
    public Team(int TeamID, String TeamName, String Group){
        this.TeamID = TeamID;
        this.TeamName = TeamName;
        this.Group = Group;
    }
    public int getTeamID(){
        return this.TeamID;
    }
}
