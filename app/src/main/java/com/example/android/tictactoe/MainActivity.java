package com.example.android.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button standardbtn;
    private Button megaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_board);
        standardbtn = (Button) findViewById(R.id.btn_standard_board);
        megaBtn = (Button) findViewById(R.id.btn_mega_board);

        standardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SelectBoardActivity.class);
                myIntent.putExtra("BOARDTYPE", "STANDARD");
                startActivity(myIntent);
                finish();
                return;
            }
        });
        megaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SelectBoardActivity.class);
                myIntent.putExtra("BOARDTYPE", "MEGA");
                startActivity(myIntent);
                finish();
                return;
            }
        });
    }
}
