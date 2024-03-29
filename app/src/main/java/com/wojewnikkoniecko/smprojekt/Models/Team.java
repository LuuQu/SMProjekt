package com.wojewnikkoniecko.smprojekt.Models;

import java.util.ArrayList;

public class Team {
    public static ArrayList<Team> teamArrayList = new ArrayList<>();
    private int id;
    private String name;
    private String group;

    @Override
    public String toString() {
        return "Team: " + name + " Group: " + group;
    }

    public Team(int id, String name, String group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }
    public Team() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
