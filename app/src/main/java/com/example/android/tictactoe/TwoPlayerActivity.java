package com.example.android.tictactoe;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class TwoPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;
    private int player1Points;
    private int player2Points;

    private TextView tvPlayer1;
    private TextView tvPlayer2;
    public static final Random RANDOM = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

        tvPlayer1 = (TextView) findViewById(R.id.tv_player1);
        tvPlayer2 = (TextView) findViewById(R.id.tv_player2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {//grid22_btn
                String buttonID = "grid" + i + j + "_btn";
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = (Button) findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = (Button) findViewById(R.id.reset_btn);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();


            }
        });


    }


    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equalsIgnoreCase("")) {
            return;
        }

        if (player1Turn) {
            Log.e("Main", "the player 1 turn");
            ((Button) view).setText("X");
            ((Button) view).setTextColor(getResources().getColor(R.color.colorAccent));
        } else {
            Log.e("Main", "the player 2 turn");
            ((Button) view).setText("O");
            ((Button) view).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            // player2play();
        }

        roundCount++;

        if (checkForWinner()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
            //player2play();
        }

    }


    private void draw() {
        Toast.makeText(TwoPlayerActivity.this, "Draw!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(TwoPlayerActivity.this, "Hurray! Player 1 wins!", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }


    private void player2Wins() {
        player2Points++;
        Toast.makeText(TwoPlayerActivity.this, "Yaay! Player 2 takes it!", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }

    private void updatePointsText() {
        tvPlayer1.setText("Player 1: " + player1Points);
        tvPlayer2.setText("Player 2: " + player2Points);

    }

    private boolean checkForWinner() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][1].equals(field[i][1])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}
