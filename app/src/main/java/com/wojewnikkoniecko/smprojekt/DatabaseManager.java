package com.wojewnikkoniecko.smprojekt;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DatabaseManager {

    List<Team> list = new ArrayList<>();

    public DatabaseManager(){
        list.add(new Team(1,"Qatar", "A"));
        list.add(new Team(2,"Ecuador", "A"));
        list.add(new Team(3,"Senegal", "A"));
        list.add(new Team(4,"Netherlands", "A"));
        list.add(new Team(5,"England", "B"));
        list.add(new Team(6,"United States", "B"));
        list.add(new Team(7,"Iran", "B"));
        list.add(new Team(8,"Wales", "B"));
        list.add(new Team(9,"Argentina", "C"));
        list.add(new Team(10,"Poland", "C"));
        list.add(new Team(11,"Mexico", "C"));
        list.add(new Team(12,"Saudi Arabia", "C"));
        list.add(new Team(13,"France", "D"));
        list.add(new Team(14,"Australia", "D"));
        list.add(new Team(15,"Tunisia", "D"));
        list.add(new Team(16,"Denmark", "D"));
        list.add(new Team(17,"Japan", "E"));
        list.add(new Team(18,"Spain", "E"));
        list.add(new Team(19,"Germany", "E"));
        list.add(new Team(20,"Costa Rica", "E"));
        list.add(new Team(21,"Morocco", "F"));
        list.add(new Team(22,"Croatia", "F"));
        list.add(new Team(23,"Belgium", "F"));
        list.add(new Team(24,"Canada", "F"));
        list.add(new Team(25,"Brazil", "G"));
        list.add(new Team(26,"Switzerland", "G"));
        list.add(new Team(27,"Cameroon", "G"));
        list.add(new Team(28,"Serbia", "G"));
        list.add(new Team(29,"Portugal", "H"));
        list.add(new Team(30,"South Korea", "H"));
        list.add(new Team(31,"Uruguay", "H"));
        list.add(new Team(32,"Ghana", "H"));
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
