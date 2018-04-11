package com.example.android.tictactoe;

import android.os.Handler;
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


public class SinglePlayerActivity extends AppCompatActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_single_player);

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
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
//                View mView = getLayoutInflater().inflate(R.layout.dialog, null);
//                final EditText mEmail = (EditText)mView.findViewById(R.id.et_email);
//                final EditText mPassword = (EditText)mView.findViewById(R.id.et_password);
//                Button mButton = (Button)mView.findViewById(R.id.login_btn);
//                mBuilder.setView(mView);
//                final AlertDialog dialog = mBuilder.create();
//
//                mButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if(!mEmail.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()){
//                            Toast.makeText(MainActivity.this, sucessful, Toast.LENGTH_LONG).show();
//                            dialog.dismiss();
//                        }else{
//                            Toast.makeText(MainActivity.this, "fill empty fields", Toast.LENGTH_LONG).show();
//
//                        }
//                    }
//                });
//
//                dialog.show();

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
//        }else{
//            Log.e("Main", "the player 2 turn");
//            //((Button)view).setText("O");
//            player2play();
//        }

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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        player2play();
                    }
                }, 5000);

            }

        }
    }

    private void player2play() {
        int position = -1;
        int x = 0;
        int y = 0;


        do {
            position = RANDOM.nextInt(9);
            x = position / 3;
            y = position % 3;
            Log.e("MainActivity", "the position is: " + position + " the X: " + x + " the Y: " + y);

        } while (!(buttons[x][y].getText().toString().equals("")));

        buttons[x][y].setText("O");
        ((Button) buttons[x][y]).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
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

        }

    }

    private void draw() {
        Toast.makeText(SinglePlayerActivity.this, "Draw!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(SinglePlayerActivity.this, "Hurray! Player 1 wins!", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }


    private void player2Wins() {
        player2Points++;
        Toast.makeText(SinglePlayerActivity.this, " Yaay! Player 2 takes it!", Toast.LENGTH_LONG).show();
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
