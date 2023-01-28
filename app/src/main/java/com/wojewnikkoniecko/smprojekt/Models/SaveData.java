package com.wojewnikkoniecko.smprojekt.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SaveData {
    HashMap<Integer, ArrayList<Statistics>> groupResults;
    HashMap<Integer, MatchKnockoutStage> roundOfSixteenResults;
    List<Team> winnersOfRoundOfSixteen;
    HashMap<Integer, MatchKnockoutStage> roundOfEightResults;
    List<Team> winnersOfRoundOfEight;
    HashMap<Integer, MatchKnockoutStage> roundOfFourResults;
    String loser1;
    String loser2;
    String winner1;
    String winner2;
    String champion;

    public String getLoser1() {
        return loser1;
    }

    public void setLoser1(String loser1) {
        this.loser1 = loser1;
    }

    public String getLoser2() {
        return loser2;
    }

    public void setLoser2(String loser2) {
        this.loser2 = loser2;
    }

    public String getWinner1() {
        return winner1;
    }

    public void setWinner1(String winner1) {
        this.winner1 = winner1;
    }

    public String getWinner2() {
        return winner2;
    }

    public void setWinner2(String winner2) {
        this.winner2 = winner2;
    }

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public HashMap<Integer, ArrayList<Statistics>> getGroupResults() {
        return groupResults;
    }

    public void setGroupResults(HashMap<Integer, ArrayList<Statistics>> groupResults) {
        this.groupResults = groupResults;
    }

    public HashMap<Integer, MatchKnockoutStage> getRoundOfSixteenResults() {
        return roundOfSixteenResults;
    }

    public void setRoundOfSixteenResults(HashMap<Integer, MatchKnockoutStage> roundOfSixteenResults) {
        this.roundOfSixteenResults = roundOfSixteenResults;
    }

    public List<Team> getWinnersOfRoundOfSixteen() {
        return winnersOfRoundOfSixteen;
    }

    public void setWinnersOfRoundOfSixteen(List<Team> winnersOfRoundOfSixteen) {
        this.winnersOfRoundOfSixteen = winnersOfRoundOfSixteen;
    }

    public HashMap<Integer, MatchKnockoutStage> getRoundOfEightResults() {
        return roundOfEightResults;
    }

    public void setRoundOfEightResults(HashMap<Integer, MatchKnockoutStage> roundOfEightResults) {
        this.roundOfEightResults = roundOfEightResults;
    }

    public List<Team> getWinnersOfRoundOfEight() {
        return winnersOfRoundOfEight;
    }

    public void setWinnersOfRoundOfEight(List<Team> winnersOfRoundOfEight) {
        this.winnersOfRoundOfEight = winnersOfRoundOfEight;
    }

    public HashMap<Integer, MatchKnockoutStage> getRoundOfFourResults() {
        return roundOfFourResults;
    }

    public void setRoundOfFourResults(HashMap<Integer, MatchKnockoutStage> roundOfFourResults) {
        this.roundOfFourResults = roundOfFourResults;
    }


    public List<Team> getWinnersOfGroupStage() {
        return winnersOfGroupStage;
    }

    public void setWinnersOfGroupStage(List<Team> winnersOfGroupStage) {
        this.winnersOfGroupStage = winnersOfGroupStage;
    }

    List<Team> winnersOfGroupStage;
}
