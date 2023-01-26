package com.wojewnikkoniecko.smprojekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ChoosingTeamActivity extends AppCompatActivity {
    //teams do wczytania z bazy danych
    List<String> teams = new ArrayList<>();
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teams.add("Polska");
        teams.add("Argentyna");
        teams.add("Arabia Saudyjska");
        teams.add("Meksyk");
        setContentView(R.layout.activity_choosing_team);
        autoCompleteTextView = findViewById(R.id.teams);
        adapterItems = new ArrayAdapter<>(this,R.layout.list_item,teams);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(ChoosingTeamActivity.this,"Wybrałeś drużynę: " + item,Toast.LENGTH_SHORT).show();
            Button button = (Button)findViewById(R.id.nextButton);
            button.setEnabled(true);
        });
    }
    public void loadGroups(View view) {
        Intent i = new Intent(this, GroupsActivity.class);
        startActivity(i);
    }
    public void loadMainMenu(View view) {
        finish();
    }
}