package com.example.android.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectBoardActivity extends AppCompatActivity {
    private Button singlePlayerBtn;
    private Button twoPlayerBtn;
    private String boardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singlePlayerBtn = (Button) findViewById(R.id.btn_single_player);
        twoPlayerBtn = (Button) findViewById(R.id.btn_two_player);

        Intent myIntent = getIntent();


        boardType = myIntent.getStringExtra("BOARDTYPE");

        singlePlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boardType.equalsIgnoreCase("STANDARD")) {
                    Intent myIntent = new Intent(SelectBoardActivity.this, SinglePlayerActivity.class);
                    startActivity(myIntent);
                    finish();
                    return;
                } else if (boardType.equalsIgnoreCase("MEGA")) {
                    Intent myIntent = new Intent(SelectBoardActivity.this, MegaSinglePlayerActivity.class);
                    startActivity(myIntent);
                    finish();
                    return;
                }

            }
        });
        twoPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boardType.equalsIgnoreCase("STANDARD")) {
                    Intent myIntent = new Intent(SelectBoardActivity.this, TwoPlayerActivity.class);
                    startActivity(myIntent);
                    finish();
                    return;
                } else if (boardType.equalsIgnoreCase("MEGA")) {
                    Intent myIntent = new Intent(SelectBoardActivity.this, MegaTwoPlayerActivity.class);
                    startActivity(myIntent);
                    finish();
                    return;
                }
            }
        });
    }
}