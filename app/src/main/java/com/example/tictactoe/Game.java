package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mp;

    private TextView playerOneScore,playerTwoScore,playerStatus,playerOne,playerTwo;
    private Button[] buttons=new Button[9];
    private Button resetGame;

    private int playerOneScoreCount,playerTwoScoreCount,rountCount;
    boolean activePlayer;
    //p1=0,p2=1,empty=2
    int[] gameState={2,2,2,2,2,2,2,2,2};

    int[][] winningPositions={
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent=getIntent();

        playerOne=(TextView)findViewById(R.id.playerOne);
        playerTwo=(TextView)findViewById(R.id.playerTwo);

        playerOneScore=(TextView)findViewById(R.id.playerOneScore);
        playerTwoScore=(TextView)findViewById(R.id.playerTwoScore);
        playerStatus=(TextView)findViewById(R.id.playerStatus);

        resetGame=(Button) findViewById(R.id.resetGame);

//        Toast.makeText(this,send.getStringExtra("playerOneName"), Toast.LENGTH_SHORT).show();

        playerOne.setText(intent.getStringExtra("playerOneName"));
        playerTwo.setText(intent.getStringExtra("playerTwoName"));

        for(int i=0;i<9;i++)
        {
            String buttonID="btn_"+i;
            int resourceID=getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i]=findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        rountCount=0;
        playerOneScoreCount=0;
        playerTwoScoreCount=0;
        activePlayer=true;

    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals(""))
        {
            return;
        }
        String buttonID=v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer=Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));

        if(activePlayer)
        {
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFC34A"));
//            mp.stop();
//            mp=MediaPlayer.create(this,R.raw.audio1);
//            mp.start();
//            wait(2,10000);
//            mp.wait(2);
            gameState[gameStatePointer]=0;
        }
        else
        {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#70FFEA"));

//            mp.stop();
//            mp=MediaPlayer.create(this,R.raw.audio2);
//            mp.start();
            gameState[gameStatePointer]=1;
        }
        rountCount++;

        if(checkWinner())
        {
            if(activePlayer)
            {
                playerOneScoreCount++;
                updatePlayerScore();;
                Toast.makeText(this, playerOne.getText().toString()+" Won!", Toast.LENGTH_SHORT).show();
//                mp.stop();
                mp=MediaPlayer.create(this,R.raw.win);
                mp.start();
//                winmp.start();
                playAgain();
            }
            else
            {
                playerTwoScoreCount++;
                updatePlayerScore();;
                Toast.makeText(this, playerTwo.getText().toString()+" Won!", Toast.LENGTH_SHORT).show();
//                mp.stop();
                mp=MediaPlayer.create(this,R.raw.win);
                mp.start();
//                winmp.start();
                playAgain();
            }
        }
        else if(rountCount==9)
        {
            playAgain();
            Toast.makeText(this, "Draw!!", Toast.LENGTH_SHORT).show();
//            mp.stop();
            mp=MediaPlayer.create(this,R.raw.draw);
            mp.start();
//            drawmp.start();
        }
        else
        {
            activePlayer=!activePlayer;
        }

        if(playerOneScoreCount>playerTwoScoreCount)
        {
            playerStatus.setText(playerOne.getText().toString()+" Is Winning!");
        }
        else if(playerTwoScoreCount>playerOneScoreCount)
        {
            playerStatus.setText(playerTwo.getText().toString()+" Is Winning!");
        }
        else
        {
            playerStatus.setText("");
        }

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount=0;
                playerTwoScoreCount=0;
                playerStatus.setText("");
                updatePlayerScore();
//                mp.stop();
                mp=MediaPlayer.create(Game.this,R.raw.reset);
                mp.start();
//                resetmp.start();
            }
        });

    }

    public boolean checkWinner()
    {
        boolean winnerResult=false;

        for(int [] winnerPosion : winningPositions)
        {
            if(gameState[winnerPosion[0]]==gameState[winnerPosion[1]]&&
                    gameState[winnerPosion[1]]==gameState[winnerPosion[2]]&&
                    gameState[winnerPosion[0]]!=2)
            {
                winnerResult=true;
            }
        }
        return winnerResult;

    }

    public void updatePlayerScore()
    {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain()
    {
        rountCount=0;
        activePlayer=true;
        for(int i=0;i<9;i++)
        {
            gameState[i]=2;
            buttons[i].setText("");
        }
    }

}