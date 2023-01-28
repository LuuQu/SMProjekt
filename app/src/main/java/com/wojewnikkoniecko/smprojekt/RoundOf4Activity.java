package com.wojewnikkoniecko.smprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RoundOf4Activity extends AppCompatActivity {
    TextView team1,team2;
    Button finalmatch, thirdplace, semi1, semi2,next,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_of4);
        finalmatch = findViewById(R.id.buttonFinal);
        thirdplace = findViewById(R.id.button3rdPlace);
        semi1 = findViewById(R.id.buttonSemifinal1);
        semi2 = findViewById(R.id.buttonSemifinal2);
        next = findViewById(R.id.idButtonNext);
        back = findViewById(R.id.idButtonBack);
        next.setEnabled(false);
        thirdplace.setEnabled(false);
        finalmatch.setEnabled(false);

        finalmatch.setOnClickListener(view -> {
            //losuje wynik meczu 1
            finalmatch.setEnabled(false);
            if(!thirdplace.isEnabled()) {
                next.setEnabled(true);
            }
        });
        thirdplace.setOnClickListener(view -> {
            //losuje wynik meczu 2
            thirdplace.setEnabled(false);
            if(!finalmatch.isEnabled()) {
                next.setEnabled(true);
            }

        });
        semi1.setOnClickListener(view -> {
            //losuje wynik meczu półfinałowego 1
            semi1.setEnabled(false);
            if(!semi2.isEnabled()) {
                finalmatch.setEnabled(true);
                thirdplace.setEnabled(true);
            }
        });
        semi2.setOnClickListener(view -> {
            //losuje wynik meczu półfinałowego 2
            semi2.setEnabled(false);
            if(!semi1.isEnabled()) {
                finalmatch.setEnabled(true);
                thirdplace.setEnabled(true);
            }
        });
        next.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
        back.setOnClickListener(view -> {
            finish();
        });
    }
}