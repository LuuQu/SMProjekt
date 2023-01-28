package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wojewnikkoniecko.smprojekt.Models.SaveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadPreviousSaveActivity extends AppCompatActivity {
    HashMap<String,String> saves = new HashMap<>();
    DatabaseManager databaseManager = new DatabaseManager(this);
    List<String> saveList = new ArrayList<>();
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView autoCompleteTextView;
    String chosenSave;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_previous_save);
        saves = databaseManager.GetAllSaves();
        for(Map.Entry<String,String> save : saves.entrySet()){
            String json = save.getKey();
            SaveData saveData = gson.fromJson(json, SaveData.class);
            saveList.add(saveData.getChampion() + " " + save.getValue());
        }
        setContentView(R.layout.activity_choosing_team);
        autoCompleteTextView = findViewById(R.id.teams);
        adapterItems = new ArrayAdapter<>(this,R.layout.list_item,saveList);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            chosenSave = item;
            Toast.makeText(LoadPreviousSaveActivity.this,"Wybrałeś ten zapis: " + item,Toast.LENGTH_SHORT).show();
            Button button = (Button)findViewById(R.id.nextButton);
            button.setEnabled(true);
        });
    }
    public void loadGroups(View view) {
        Intent i = new Intent(this, GroupsActivity.class);
        String[] tmp = chosenSave.split(" ");
        String jsonToSend = "";
        for(Map.Entry<String,String> save : saves.entrySet()){
            String json = save.getKey();
            SaveData saveData = gson.fromJson(json, SaveData.class);
            if(saveData.getChampion().equals(tmp[0])){
                jsonToSend = json;
            }
        }
        i.putExtra("save", jsonToSend);
        startActivity(i);
    }
    public void loadMainMenu(View view) {
        finish();
    }
}