package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditTeamActivity extends AppCompatActivity {
    DatabaseManager databaseManager = new DatabaseManager(this);
    TextView name,group;
    Button delete,update,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int teamId = getIntent().getIntExtra("id",0);
        String teamName = getIntent().getStringExtra("name");
        String teamGroup = getIntent().getStringExtra("group");
        Team team = new Team(teamId,teamName,teamGroup);
        setContentView(R.layout.activity_edit_team);
        name = findViewById(R.id.nameId);
        group = findViewById(R.id.groupId);
        name.setText(teamName);
        group.setText(teamGroup);
        delete = findViewById(R.id.deleteButtonId);
        update = findViewById(R.id.updateButtonId);
        back = findViewById(R.id.backButtonId);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseManager.DeleteOneTeam(team);
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                team.setName(name.getText().toString());
                team.setGroup(group.getText().toString());
                databaseManager.UpdateTeam(team);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}