package com.wojewnikkoniecko.smprojekt;

import android.os.Build;

import androidx.annotation.RequiresApi;


import com.wojewnikkoniecko.smprojekt.Models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DatabaseManager {

    List<Team> list = new ArrayList<>();
    List<Match> matches = new ArrayList<>();
    public DatabaseManager(){
        list.add(new Team(1,"Qatar", "A1"));
        list.add(new Team(2,"Ecuador", "A2"));
        list.add(new Team(3,"Senegal", "A3"));
        list.add(new Team(4,"Netherlands", "A4"));
        list.add(new Team(5,"England", "B1"));
        list.add(new Team(6,"United States", "B2"));
        list.add(new Team(7,"Iran", "B3"));
        list.add(new Team(8,"Wales", "B4"));
        list.add(new Team(9,"Argentina", "C1"));
        list.add(new Team(10,"Poland", "C2"));
        list.add(new Team(11,"Mexico", "C3"));
        list.add(new Team(12,"Saudi Arabia", "C4"));
        list.add(new Team(13,"France", "D1"));
        list.add(new Team(14,"Australia", "D2"));
        list.add(new Team(15,"Tunisia", "D3"));
        list.add(new Team(16,"Denmark", "D4"));
        list.add(new Team(17,"Japan", "E1"));
        list.add(new Team(18,"Spain", "E2"));
        list.add(new Team(19,"Germany", "E3"));
        list.add(new Team(20,"Costa Rica", "E4"));
        list.add(new Team(21,"Morocco", "F1"));
        list.add(new Team(22,"Croatia", "F2"));
        list.add(new Team(23,"Belgium", "F3"));
        list.add(new Team(24,"Canada", "F4"));
        list.add(new Team(25,"Brazil", "G1"));
        list.add(new Team(26,"Switzerland", "G2"));
        list.add(new Team(27,"Cameroon", "G3"));
        list.add(new Team(28,"Serbia", "G4"));
        list.add(new Team(29,"Portugal", "H1"));
        list.add(new Team(30,"South Korea", "H2"));
        list.add(new Team(31,"Uruguay", "H3"));
        list.add(new Team(32,"Ghana", "H4"));


        matches.add(new Match(1, "A1", "A2"));
        matches.add(new Match(2, "A3", "A4"));
        matches.add(new Match(3, "A1", "A3"));
        matches.add(new Match(4, "A4", "A2"));
        matches.add(new Match(5, "A2", "A3"));
        matches.add(new Match(6, "A4", "A1"));
        matches.add(new Match(7, "B1", "B2"));
        matches.add(new Match(8, "B3", "B4"));
        matches.add(new Match(9, "B1", "B3"));
        matches.add(new Match(10, "B4", "B2"));
        matches.add(new Match(11, "B2", "B3"));
        matches.add(new Match(12, "B4", "B1"));
        matches.add(new Match(13, "C1", "C2"));
        matches.add(new Match(14, "C3", "C4"));
        matches.add(new Match(15, "C1", "C3"));
        matches.add(new Match(16, "C4", "C2"));
        matches.add(new Match(17, "C2", "C3"));
        matches.add(new Match(18, "C4", "C1"));
        matches.add(new Match(19, "D1", "D2"));
        matches.add(new Match(20, "D3", "D4"));
        matches.add(new Match(21, "D1", "D3"));
        matches.add(new Match(22, "D4", "D2"));
        matches.add(new Match(23, "D2", "D3"));
        matches.add(new Match(24, "D4", "D1"));
        matches.add(new Match(25, "E1", "E2"));
        matches.add(new Match(26, "E3", "E4"));
        matches.add(new Match(27, "E1", "E3"));
        matches.add(new Match(28, "E4", "E2"));
        matches.add(new Match(29, "E2", "E3"));
        matches.add(new Match(30, "E4", "E1"));
        matches.add(new Match(31, "F1", "F2"));
        matches.add(new Match(32, "F3", "F4"));
        matches.add(new Match(33, "F1", "F3"));
        matches.add(new Match(34, "F4", "F2"));
        matches.add(new Match(35, "F2", "F3"));
        matches.add(new Match(36, "F4", "F1"));
        matches.add(new Match(37, "G1", "G2"));
        matches.add(new Match(38, "G3", "G4"));
        matches.add(new Match(39, "G1", "G3"));
        matches.add(new Match(40, "G4", "G2"));
        matches.add(new Match(41, "G2", "G3"));
        matches.add(new Match(42, "G4", "G1"));
        matches.add(new Match(43, "H1", "H2"));
        matches.add(new Match(44, "H3", "H4"));
        matches.add(new Match(45, "H1", "H3"));
        matches.add(new Match(46, "H4", "H2"));
        matches.add(new Match(47, "H2", "H3"));
        matches.add(new Match(48, "H4", "H1"));
    }
    public List<Match> getMatches(){
        return this.matches;
    }
    public List<Team> getTeams(){
        return this.list;
    }
    public void insert (String team, String groupID){
        int lenght = list.size();
        Team newTeam = new Team(lenght + 1, team,groupID);
        list.add(newTeam);
    }

    public List<Team> fetch(){
        return list;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void update(int id, String team, String group){
        this.list = fetch();
        Team teamToUpdate = new Team(id, team, group);
        teamToUpdate.TeamName = team;
        teamToUpdate.Group = group;
        try {
            list.remove(id);
            list.add(teamToUpdate);
            list.sort(Comparator.comparing(Team::getTeamID));
            Collections.reverse(list);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void delete(int id){
        try{
            list.remove(id - 1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
