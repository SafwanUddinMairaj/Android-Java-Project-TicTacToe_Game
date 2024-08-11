package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

public class GAME extends AppCompatActivity {
    private void insertGameHistory(String winner) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GameDatabaseHelper.COLUMN_WINNER, winner);
        databaseHelper = new GameDatabaseHelper(this);

        db.insert(GameDatabaseHelper.TABLE_GAME_HISTORY, null, values);
        db.close();
    }


    //0 ->yellow player ......... 1->red player
    int activePlayer= 0;
    boolean isGameDrawn = false;

    //2 means image view empty
    int[] gameState={2,2,2,2,2,2,2,2,2};

            //2D array
            int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

            String winner = " ";
            boolean IsgameActive=true;
    public void DisplayImage(View view)
    {

        ImageView counter=(ImageView) view;
        
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState [tappedCounter] == 2 && IsgameActive)
        {
            gameState[tappedCounter]=activePlayer;

            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yellow);
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }


            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    if (activePlayer == 1)
                        winner = "player yellow";
                    else
                        winner = "player red";

                    // Insert the game history into the database
                    insertGameHistory(winner);

                    // Display the winner message and end the game
                    //Toast.makeText(this, winner + " won!", Toast.LENGTH_LONG).show();
                    IsgameActive = false;


                    //Toast.makeText(this, winner + " won!", Toast.LENGTH_LONG).show();
                    IsgameActive=false;

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+" won!!");

                    Button btnPlayAgain=(Button) findViewById(R.id.btnPlayAgain);
                    btnPlayAgain.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    String winner = " ";
                    if (activePlayer == 1)
                        winner = "player yellow";
                    else
                        winner = "player red";


                    //Toast.makeText(this, winner + " won!", Toast.LENGTH_LONG).show();
                    IsgameActive=false;

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+" won!!");

                    Button btnPlayAgain=(Button) findViewById(R.id.btnPlayAgain);
                    btnPlayAgain.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }

        //drawn feature
        isGameDrawn = true;
        for (int position : gameState) {
            if (position == 2) {
                isGameDrawn = false;
                break;
            }
        }
        if (isGameDrawn) {
            // Insert the game history into the database
            insertGameHistory("Game Drawn");

            // Display the draw message and end the game
            //Toast.makeText(this, "Game drawn!", Toast.LENGTH_LONG).show();
            IsgameActive = false;

            TextView winnerTextView = findViewById(R.id.winnerTextView);
            winnerTextView.setText("Game drawn!");
            winnerTextView.setVisibility(View.VISIBLE);

            Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
            btnPlayAgain.setVisibility(View.VISIBLE);
        }

    }

    public void playAgain(View view)
    {
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        Button btnPlayAgain=(Button) findViewById(R.id.btnPlayAgain);
        btnPlayAgain.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        activePlayer=0;
        IsgameActive=true;

        for(int i=0;i< gameState.length;i++)
            gameState[i]=2;

        int[] allImages={R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,
                R.id.imageView8,R.id.imageView9, R.id.imageView10,R.id.imageView11,R.id.imageView12};

        for(int i=0; i<allImages.length;i++)
        {
            ImageView counter=(ImageView) findViewById(allImages[i]);
            counter.setImageDrawable(null);
        }

    }
    private GameDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        databaseHelper = new GameDatabaseHelper(this);

        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

}