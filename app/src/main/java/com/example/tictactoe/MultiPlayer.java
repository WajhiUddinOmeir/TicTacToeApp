package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MultiPlayer extends AppCompatActivity {

    public EditText playerOneName,playerTwoName;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);

        playerOneName=findViewById(R.id.playerOneName);
        playerTwoName=findViewById(R.id.playerTwoName);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(MultiPlayer.this, Game.class);

                send.putExtra("playerOneName",playerOneName.getText().toString());
                send.putExtra("playerTwoName",playerTwoName.getText().toString());

                startActivity(send);

            }
        });
    }
}