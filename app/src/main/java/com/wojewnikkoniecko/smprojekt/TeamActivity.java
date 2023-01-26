package com.wojewnikkoniecko.smprojekt;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.wojewnikkoniecko.smprojekt.Models.Team;

import java.util.List;

public class TeamActivity extends AppCompatActivity {
    EditText editTeamID;
    EditText editTeamName;
    EditText editGroupID;
    Button backButton;
    Button DeleteButton;
    Button UpdateButton;
    Button FetchButton;
    Button InsertButton;
    Button ConfirmButton;
    TextView textbox;
    Boolean UpdatePassed = false;
    Boolean DeletePassed = false;
    public DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity);
        editTeamID = (EditText) findViewById(R.id.editTextID);
        editTeamName = (EditText) findViewById(R.id.editTextTeamName);
        editGroupID = (EditText) findViewById(R.id.editTextGroupID);
        backButton = (Button) findViewById(R.id.backButton);
        ConfirmButton = (Button) findViewById(R.id.ConfirmButton);
        DeleteButton = (Button) findViewById(R.id.DeleteButton);
        UpdateButton = (Button) findViewById(R.id.UpdateButton);
        FetchButton = (Button) findViewById(R.id.FetchButton);
        InsertButton = (Button) findViewById(R.id.InsertButton);
        textbox = (TextView) findViewById(R.id.text_box);

        textbox.setVisibility(View.GONE);

        editTeamID.setVisibility(View.GONE);
        editTeamName.setVisibility(View.GONE);
        editGroupID.setVisibility(View.GONE);

        backButton.setVisibility(View.GONE);
        ConfirmButton.setVisibility(View.GONE);


        dbManager = new DatabaseManager();
    }

    public void btnBackPressed(View view) {
        editTeamID.setVisibility(View.GONE);
        editTeamName.setVisibility(View.GONE);
        editGroupID.setVisibility(View.GONE);

        backButton.setVisibility(View.GONE);
        ConfirmButton.setVisibility(View.GONE);
        textbox.setVisibility(View.GONE);

        DeleteButton.setVisibility(View.VISIBLE);
        UpdateButton.setVisibility(View.VISIBLE);
        FetchButton.setVisibility(View.VISIBLE);
        InsertButton.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void btnConfirmPressed(View view) {
        if (ConfirmButton.getVisibility() == View.GONE) {
            List<Team> teams = dbManager.fetch();
            StringBuilder sb = new StringBuilder();
            for (Team item : teams) {
                sb.append(item.TeamID + " " + item.TeamName + " " + item.Group);
                sb.append("\n");
            }
            textbox.setText(sb.toString());
        } else if (UpdateButton.getVisibility() == View.VISIBLE) {
            dbManager.update(Integer.parseInt(editTeamID.getText().toString()), editTeamName.getText().toString(), editGroupID.getText().toString());
        } else if (DeleteButton.getVisibility() == View.VISIBLE) {
            dbManager.delete(Integer.parseInt(editTeamID.getText().toString()));
        } else if (InsertButton.getVisibility() == View.VISIBLE) {
            String team = editTeamName.getText().toString();
            String GroupId = editGroupID.getText().toString();
            dbManager.insert(team, GroupId);
        }
    }

    public void btnInsertPressed(View view) {
        editTeamID.setVisibility(View.GONE);
        editTeamName.setVisibility(View.VISIBLE);
        editGroupID.setVisibility(View.VISIBLE);

        backButton.setVisibility(View.VISIBLE);
        ConfirmButton.setVisibility(View.VISIBLE);
        textbox.setVisibility(View.GONE);

        DeleteButton.setVisibility(View.GONE);
        UpdateButton.setVisibility(View.GONE);
        FetchButton.setVisibility(View.GONE);
        InsertButton.setVisibility(View.VISIBLE);
    }

    public void btnUpdatePressed(View view) {
        editTeamID.setVisibility(View.VISIBLE);
        editTeamName.setVisibility(View.VISIBLE);
        editGroupID.setVisibility(View.VISIBLE);
        textbox.setVisibility(View.GONE);

        backButton.setVisibility(View.VISIBLE);
        ConfirmButton.setVisibility(View.VISIBLE);

        DeleteButton.setVisibility(View.GONE);
        UpdateButton.setVisibility(View.VISIBLE);
        FetchButton.setVisibility(View.GONE);
        InsertButton.setVisibility(View.GONE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void btnFetchPressed(View view) {
        editTeamID.setVisibility(View.GONE);
        editTeamName.setVisibility(View.GONE);
        editGroupID.setVisibility(View.GONE);
        textbox.setVisibility(View.VISIBLE);

        backButton.setVisibility(View.VISIBLE);
        ConfirmButton.setVisibility(View.GONE);

        DeleteButton.setVisibility(View.GONE);
        UpdateButton.setVisibility(View.GONE);
        FetchButton.setVisibility(View.GONE);
        InsertButton.setVisibility(View.GONE);
        btnConfirmPressed(view);

    }

    public void btnDeletePressed(View view) {
        editTeamID.setVisibility(View.VISIBLE);
        editTeamName.setVisibility(View.GONE);
        editGroupID.setVisibility(View.GONE);

        backButton.setVisibility(View.VISIBLE);
        ConfirmButton.setVisibility(View.VISIBLE);

        DeleteButton.setVisibility(View.VISIBLE);
        UpdateButton.setVisibility(View.GONE);
        FetchButton.setVisibility(View.GONE);
        InsertButton.setVisibility(View.GONE);
    }
}
