package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RoundOf8Activity extends AppCompatActivity {
    TextView team1,team2;
    Button match1,match2,match3,match4,next,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_of8);
        match1 = findViewById(R.id.idButton1);
        match2 = findViewById(R.id.idButton2);
        match3 = findViewById(R.id.idButton3);
        match4 = findViewById(R.id.idButton4);
        next = findViewById(R.id.idButtonNext);
        back = findViewById(R.id.idButtonBack);
        next.setEnabled(false);

        match1.setOnClickListener(view -> {
            //losuje wynik meczu 1
            match1.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled()) {
                next.setEnabled(true);
            }
        });
        match2.setOnClickListener(view -> {
            //losuje wynik meczu 2
            match2.setEnabled(false);
            if(!match1.isEnabled() && !match3.isEnabled() && !match4.isEnabled()) {
                next.setEnabled(true);
            }
        });
        match3.setOnClickListener(view -> {
            //losuje wynik meczu 3
            match3.setEnabled(false);
            if(!match2.isEnabled() && !match1.isEnabled() && !match4.isEnabled()) {
                next.setEnabled(true);
            }
        });
        match4.setOnClickListener(view -> {
            //losuje wynik meczu 4
            match4.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match1.isEnabled()) {
                next.setEnabled(true);
            }
        });
        next.setOnClickListener(view -> {
            Intent intent = new Intent(RoundOf8Activity.this, RoundOf4Activity.class);
            startActivity(intent);
        });
        back.setOnClickListener(view -> {
            finish();
        });
    }
}