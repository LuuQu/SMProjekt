package com.wojewnikkoniecko.smprojekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.wojewnikkoniecko.smprojekt.Models.Match;
import com.wojewnikkoniecko.smprojekt.Models.Team;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    private final String TABLE_TEAM_NAME = "Teams";
    private final String TEAM_NAME = "Name";
    private final String TEAM_GROUP = "TeamGroup";
    private final String TEAM_ID = "Id";


    private final String TABLE_MATCH_NAME = "Matches";
    private final String MATCH_TEAM_A = "Home";
    private final String MATCH_TEAM_B = "Away";
    private final String MATCH_RESULT_TEAM_A = "HomeResult";
    private final String MATCH_RESULT_TEAM_B = "AwayResult";
    private final String MATCH_ID = "Id";
    public DatabaseManager(@Nullable Context context) {
        super(context, "WcDataBase", null, 1);
    }

    public List<Match> getMatches(){
        List<Match> matches = new ArrayList<>();
        matches.add(new Match(1, "A1", "A2",-1,-1));
        matches.add(new Match(2, "A3", "A4",-1,-1));
        matches.add(new Match(3, "A1", "A3",-1,-1));
        matches.add(new Match(4, "A4", "A2",-1,-1));
        matches.add(new Match(5, "A2", "A3",-1,-1));
        matches.add(new Match(6, "A4", "A1",-1,-1));
        matches.add(new Match(7, "B1", "B2",-1,-1));
        matches.add(new Match(8, "B3", "B4",-1,-1));
        matches.add(new Match(9, "B1", "B3",-1,-1));
        matches.add(new Match(10, "B4", "B2",-1,-1));
        matches.add(new Match(11, "B2", "B3",-1,-1));
        matches.add(new Match(12, "B4", "B1",-1,-1));
        matches.add(new Match(13, "C1", "C2",-1,-1));
        matches.add(new Match(14, "C3", "C4",-1,-1));
        matches.add(new Match(15, "C1", "C3",-1,-1));
        matches.add(new Match(16, "C4", "C2",-1,-1));
        matches.add(new Match(17, "C2", "C3",-1,-1));
        matches.add(new Match(18, "C4", "C1",-1,-1));
        matches.add(new Match(19, "D1", "D2",-1,-1));
        matches.add(new Match(20, "D3", "D4",-1,-1));
        matches.add(new Match(21, "D1", "D3",-1,-1));
        matches.add(new Match(22, "D4", "D2",-1,-1));
        matches.add(new Match(23, "D2", "D3",-1,-1));
        matches.add(new Match(24, "D4", "D1",-1,-1));
        matches.add(new Match(25, "E1", "E2",-1,-1));
        matches.add(new Match(26, "E3", "E4",-1,-1));
        matches.add(new Match(27, "E1", "E3",-1,-1));
        matches.add(new Match(28, "E4", "E2",-1,-1));
        matches.add(new Match(29, "E2", "E3",-1,-1));
        matches.add(new Match(30, "E4", "E1",-1,-1));
        matches.add(new Match(31, "F1", "F2",-1,-1));
        matches.add(new Match(32, "F3", "F4",-1,-1));
        matches.add(new Match(33, "F1", "F3",-1,-1));
        matches.add(new Match(34, "F4", "F2",-1,-1));
        matches.add(new Match(35, "F2", "F3",-1,-1));
        matches.add(new Match(36, "F4", "F1",-1,-1));
        matches.add(new Match(37, "G1", "G2",-1,-1));
        matches.add(new Match(38, "G3", "G4",-1,-1));
        matches.add(new Match(39, "G1", "G3",-1,-1));
        matches.add(new Match(40, "G4", "G2",-1,-1));
        matches.add(new Match(41, "G2", "G3",-1,-1));
        matches.add(new Match(42, "G4", "G1",-1,-1));
        matches.add(new Match(43, "H1", "H2",-1,-1));
        matches.add(new Match(44, "H3", "H4",-1,-1));
        matches.add(new Match(45, "H1", "H3",-1,-1));
        matches.add(new Match(46, "H4", "H2",-1,-1));
        matches.add(new Match(47, "H2", "H3",-1,-1));
        matches.add(new Match(48, "H4", "H1",-1,-1));
        return matches;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String teamStatement = "CREATE TABLE "+ TABLE_TEAM_NAME + " (" + TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TEAM_GROUP + " TEXT, "+ TEAM_NAME + " TEXT)";
        String matchesStatement = "CREATE TABLE " + TABLE_MATCH_NAME +" (" + MATCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                    + MATCH_TEAM_A + " TEXT, " + MATCH_TEAM_B + " TEXT, "
                                    + MATCH_RESULT_TEAM_A + " INTEGER, " + MATCH_RESULT_TEAM_B + " INTEGER)";
                                    ;
        sqLiteDatabase.execSQL(teamStatement);
        sqLiteDatabase.execSQL(matchesStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void SetTeams() {
        AddTeam(new Team(1,"Qatar", "A1"));
        AddTeam(new Team(2,"Ecuador", "A2"));
        AddTeam(new Team(3,"Senegal", "A3"));
        AddTeam(new Team(4,"Netherlands", "A4"));
        AddTeam(new Team(5,"England", "B1"));
        AddTeam(new Team(6,"United States", "B2"));
        AddTeam(new Team(7,"Iran", "B3"));
        AddTeam(new Team(8,"Wales", "B4"));
        AddTeam(new Team(9,"Argentina", "C1"));
        AddTeam(new Team(10,"Poland", "C2"));
        AddTeam(new Team(11,"Mexico", "C3"));
        AddTeam(new Team(12,"Saudi Arabia", "C4"));
        AddTeam(new Team(13,"France", "D1"));
        AddTeam(new Team(14,"Australia", "D2"));
        AddTeam(new Team(15,"Tunisia", "D3"));
        AddTeam(new Team(16,"Denmark", "D4"));
        AddTeam(new Team(17,"Japan", "E1"));
        AddTeam(new Team(18,"Spain", "E2"));
        AddTeam(new Team(19,"Germany", "E3"));
        AddTeam(new Team(20,"Costa Rica", "E4"));
        AddTeam(new Team(21,"Morocco", "F1"));
        AddTeam(new Team(22,"Croatia", "F2"));
        AddTeam(new Team(23,"Belgium", "F3"));
        AddTeam(new Team(24,"Canada", "F4"));
        AddTeam(new Team(25,"Brazil", "G1"));
        AddTeam(new Team(26,"Switzerland", "G2"));
        AddTeam(new Team(27,"Cameroon", "G3"));
        AddTeam(new Team(28,"Serbia", "G4"));
        AddTeam(new Team(29,"Portugal", "H1"));
        AddTeam(new Team(30,"South Korea", "H2"));
        AddTeam(new Team(31,"Uruguay", "H3"));
        AddTeam(new Team(32,"Ghana", "H4"));
    }
    public Boolean AddTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEAM_GROUP,team.getGroup());
        contentValues.put(TEAM_NAME,team.getName());
        Team.teamArrayList.add(team);
        long insert = db.insert(TABLE_TEAM_NAME, null, contentValues);
        if(insert == -1) {
            return false;
        }
        return true;
    }
    public ArrayList<Team> GetAllTeams() {
        ArrayList<Team> result = new ArrayList<Team>();
        String querystring = "SELECT * FROM " + TABLE_TEAM_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(querystring,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String group = cursor.getString(1);
                String name = cursor.getString(2);
                Team team = new Team(id,name,group);
                Team.teamArrayList.add(team);
                result.add(team);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
    public void UpdateTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + TABLE_TEAM_NAME + " SET " + TEAM_NAME + " = '" + team.getName() + "', " + TEAM_GROUP + " = '" + team.getGroup() +
                "' WHERE " + TEAM_ID + " = " + team.getId();
        db.execSQL(queryString);

    }
    public boolean DeleteOneTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_TEAM_NAME + " WHERE " + TEAM_ID + " = " + team.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

    public void SetMatches() {
        AddMatch(new Match(1, "A1", "A2",-1,-1));
        AddMatch(new Match(2, "A3", "A4",-1,-1));
        AddMatch(new Match(3, "A1", "A3",-1,-1));
        AddMatch(new Match(4, "A4", "A2",-1,-1));
        AddMatch(new Match(5, "A2", "A3",-1,-1));
        AddMatch(new Match(6, "A4", "A1",-1,-1));
        AddMatch(new Match(7, "B1", "B2",-1,-1));
        AddMatch(new Match(8, "B3", "B4",-1,-1));
        AddMatch(new Match(9, "B1", "B3",-1,-1));
        AddMatch(new Match(10, "B4", "B2",-1,-1));
        AddMatch(new Match(11, "B2", "B3",-1,-1));
        AddMatch(new Match(12, "B4", "B1",-1,-1));
        AddMatch(new Match(13, "C1", "C2",-1,-1));
        AddMatch(new Match(14, "C3", "C4",-1,-1));
        AddMatch(new Match(15, "C1", "C3",-1,-1));
        AddMatch(new Match(16, "C4", "C2",-1,-1));
        AddMatch(new Match(17, "C2", "C3",-1,-1));
        AddMatch(new Match(18, "C4", "C1",-1,-1));
        AddMatch(new Match(19, "D1", "D2",-1,-1));
        AddMatch(new Match(20, "D3", "D4",-1,-1));
        AddMatch(new Match(21, "D1", "D3",-1,-1));
        AddMatch(new Match(22, "D4", "D2",-1,-1));
        AddMatch(new Match(23, "D2", "D3",-1,-1));
        AddMatch(new Match(24, "D4", "D1",-1,-1));
        AddMatch(new Match(25, "E1", "E2",-1,-1));
        AddMatch(new Match(26, "E3", "E4",-1,-1));
        AddMatch(new Match(27, "E1", "E3",-1,-1));
        AddMatch(new Match(28, "E4", "E2",-1,-1));
        AddMatch(new Match(29, "E2", "E3",-1,-1));
        AddMatch(new Match(30, "E4", "E1",-1,-1));
        AddMatch(new Match(31, "F1", "F2",-1,-1));
        AddMatch(new Match(32, "F3", "F4",-1,-1));
        AddMatch(new Match(33, "F1", "F3",-1,-1));
        AddMatch(new Match(34, "F4", "F2",-1,-1));
        AddMatch(new Match(35, "F2", "F3",-1,-1));
        AddMatch(new Match(36, "F4", "F1",-1,-1));
        AddMatch(new Match(37, "G1", "G2",-1,-1));
        AddMatch(new Match(38, "G3", "G4",-1,-1));
        AddMatch(new Match(39, "G1", "G3",-1,-1));
        AddMatch(new Match(40, "G4", "G2",-1,-1));
        AddMatch(new Match(41, "G2", "G3",-1,-1));
        AddMatch(new Match(42, "G4", "G1",-1,-1));
        AddMatch(new Match(43, "H1", "H2",-1,-1));
        AddMatch(new Match(44, "H3", "H4",-1,-1));
        AddMatch(new Match(45, "H1", "H3",-1,-1));
        AddMatch(new Match(46, "H4", "H2",-1,-1));
        AddMatch(new Match(47, "H2", "H3",-1,-1));
        AddMatch(new Match(48, "H4", "H1",-1,-1));
    }
    public Boolean AddMatch(Match match) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MATCH_TEAM_A,match.getHome());
        contentValues.put(MATCH_TEAM_B,match.getAway());
        contentValues.put(MATCH_RESULT_TEAM_A,match.getResultHome());
        contentValues.put(MATCH_RESULT_TEAM_B,match.getResultAway());
        contentValues.put(MATCH_ID,match.getMatchId());
        Match.matchArrayList.add(match);
        long insert = db.insert(TABLE_MATCH_NAME, null, contentValues);
        if(insert == -1) {
            return false;
        }
        return true;
    }
    public ArrayList<Match> GetAllMatches() {
        ArrayList<Match> result = new ArrayList<Match>();
        String querystring = "SELECT * FROM " + TABLE_TEAM_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(querystring,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String home = cursor.getString(1);
                String away = cursor.getString(2);
                int homeResult = cursor.getInt(3);
                int awayResult = cursor.getInt(4);
                Match match = new Match(id,home,away,homeResult,awayResult);
                result.add(match);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
    public void UpdateMatch(Match match) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + TABLE_MATCH_NAME + " SET " + MATCH_RESULT_TEAM_A + " = '"
                + match.getResultHome() + "', " + MATCH_RESULT_TEAM_B + " = '" + match.getResultAway() +
                "' WHERE " + MATCH_TEAM_A + " = " + match.getHome() + ", " + MATCH_TEAM_A + " = " + match.getAway();
        db.execSQL(queryString);

    }
    public boolean DeleteOneMatch(Match match) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_MATCH_NAME + " WHERE " + MATCH_ID + " = " + match.getMatchId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            return true;
        }
        return false;
    }
}