package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameIsActive = true;
    //0 = yellow , 1 = red
    int active_player = 0;
    // -1 means the state is unplayed
    int[] gameStates = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 6}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tapped_Counter = Integer.parseInt(counter.getTag().toString());

        if(gameStates[tapped_Counter] == -1 && gameIsActive) {

            gameStates[tapped_Counter] = active_player;
            counter.setTranslationY(-1000f);
            if (active_player == 0) {
                counter.setImageResource(R.drawable.yellow);
                active_player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                active_player = 0;

            }

            counter.animate().translationYBy(1000f).setDuration(300);
            /*for(int[] winningPositions: winningPositions){
                if(gameStates[winningPositions[0]] == gameStates[winningPositions[1]] &&
                        gameStates[winningPositions[1]] == gameStates[winningPositions[2]] &&
                        gameStates[winningPositions[0]] != -1) {
                    System.out.println("Winner is " + gameStates[winningPositions[0]]);
                }
            }*/

            for(int i = 0; i < winningPositions.length; i++){
                if(gameStates[winningPositions[i][0]] == gameStates[winningPositions[i][1]] &&
                        gameStates[winningPositions[i][1]] == gameStates[winningPositions[i][2]] &&
                        gameStates[winningPositions[i][0]] != -1){
                    String winner = "Red";
                    if(gameStates[winningPositions[i][0]] == 0){
                        winner = "Yellow";
                    };

                    gameIsActive = false;
                    // someone has won!
                    TextView winnermessage = (TextView) findViewById(R.id.winnerMessage);
                    winnermessage.setText(winner + " has won!");


                    LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);
                    playAgain.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameOver = true;
                    for(int state: gameStates){
                        if(state == -1){
                            gameOver = false;
                        }
                    }
                    if(gameOver){
                        TextView winnermessage = (TextView) findViewById(R.id.winnerMessage);
                        winnermessage.setText("it's a draw!");


                        LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);
                        playAgain.setVisibility(View.VISIBLE);

                    }

                }
            }



        }
    }

    public void playAgain(View view){
        gameIsActive = true;
        LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);
        playAgain.setVisibility(View.INVISIBLE);
        active_player = 0;
        // -1 means the state is unplayed
        for(int i = 0; i < gameStates.length; i++){
            gameStates[i] = -1;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
