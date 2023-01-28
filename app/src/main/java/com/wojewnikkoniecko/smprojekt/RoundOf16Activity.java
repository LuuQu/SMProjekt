package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RoundOf16Activity extends AppCompatActivity {
    TextView team1,team2;
    Button match1,match2,match3,match4,match5,match6,match7,match8,next,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_of16);
        match1 = findViewById(R.id.idButton1);
        match2 = findViewById(R.id.idButton2);
        match3 = findViewById(R.id.idButton3);
        match4 = findViewById(R.id.idButton4);
        match5 = findViewById(R.id.idButton5);
        match6 = findViewById(R.id.idButton6);
        match7 = findViewById(R.id.idButton7);
        match8 = findViewById(R.id.idButton8);
        next = findViewById(R.id.idButtonNext);
        back = findViewById(R.id.idButtonBack);
        next.setEnabled(false);

        match1.setOnClickListener(view -> {
            //losuje wynik meczu 1
            match1.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }
        });
        match2.setOnClickListener(view -> {
            //losuje wynik meczu 2
            match2.setEnabled(false);
            if(!match1.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match3.setOnClickListener(view -> {
            //losuje wynik meczu 3
            match3.setEnabled(false);
            if(!match2.isEnabled() && !match1.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match4.setOnClickListener(view -> {
            //losuje wynik meczu 4
            match4.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match1.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match5.setOnClickListener(view -> {
            //losuje wynik meczu 5
            match5.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match1.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match6.setOnClickListener(view -> {
            //losuje wynik meczu 6
            match6.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match1.isEnabled() && !match7.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match7.setOnClickListener(view -> {
            //losuje wynik meczu 7
            match7.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match1.isEnabled() && !match8.isEnabled()) {
                next.setEnabled(true);
            }

        });
        match8.setOnClickListener(view -> {
            //losuje wynik meczu 8
            match8.setEnabled(false);
            if(!match2.isEnabled() && !match3.isEnabled() && !match4.isEnabled() && !match5.isEnabled() && !match6.isEnabled() && !match7.isEnabled() && !match1.isEnabled()) {
                next.setEnabled(true);
            }

        });
        next.setOnClickListener(view -> {
            Intent intent = new Intent(RoundOf16Activity.this, RoundOf8Activity.class);
            startActivity(intent);
        });
        back.setOnClickListener(view -> {
            finish();
        });
    }
}