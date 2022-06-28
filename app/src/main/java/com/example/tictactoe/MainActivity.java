package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public ImageView imageView1,imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(MainActivity.this,MultiPlayer.class);
                startActivity(send);
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent send = new Intent(MainActivity.this,GameTwo.class);
                startActivity(send);
//                Toast.makeText(MainActivity.this, "VS Computer Under Development", Toast.LENGTH_SHORT).show();
            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent send = new Intent(MainActivity.this, Game.class);
//
//                send.putExtra("playerOneName",playerOneName.getText().toString());
//                send.putExtra("playerTwoName",playerTwoName.getText().toString());
//
//                startActivity(send);
//
//            }
//        });


    }

}