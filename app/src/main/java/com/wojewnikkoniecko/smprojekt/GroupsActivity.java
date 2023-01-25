package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupsActivity extends AppCompatActivity {

    //List<String> teams = new ArrayList<>();
    HashMap<Integer, ArrayList<String>> teams = new HashMap<Integer, ArrayList<String>>();
    //String[] groups = {"a","b","c","d","e","f","g","h"}; //potem do odczytywania, czy jest kolejna grupa
    int[] groups = {1,2,3};
    int activeGroup = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        ArrayList<String> list = new ArrayList<>();
        list.add("Polska");
        list.add("Argentyna");
        list.add("Arabia Saudyjska");
        list.add("Meksyk");
        teams.put(1,list);
        list = new ArrayList<>();
        list.add("Francja");
        list.add("Dania");
        list.add("Australia");
        list.add("Tunezja");
        teams.put(2,list);
        list = new ArrayList<>();
        list.add("Anglia");
        list.add("USA");
        list.add("Walia");
        list.add("Iran");
        teams.put(3,list);
        SetTeams(1);
    }
    public void loadChoosingTeam(View view) {
        finish();
    }
    public void SetTeams(int group) {
        ArrayList<String> list = teams.get(group);
        TextView team1 = (TextView) findViewById(R.id.team1);
        TextView team2 = (TextView) findViewById(R.id.team2);
        TextView team3 = (TextView) findViewById(R.id.team3);
        TextView team4 = (TextView) findViewById(R.id.team4);
        team1.setText(list.get(0));
        team2.setText(list.get(1));
        team3.setText(list.get(2));
        team4.setText(list.get(3));
    }
    public void prevGroup(View view) {
        if(activeGroup == 1) {
            activeGroup = groups[groups.length-1];
        }
        else {
            activeGroup--;
        }
        SetTeams(activeGroup);
    }
    public void nextGroup(View view) {
        if(activeGroup == groups[groups.length-1]) {
            activeGroup = groups[0];
        }
        else {
            activeGroup++;
        }
        SetTeams(activeGroup);
    }
}