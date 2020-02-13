package com.example.animationinandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player{
        one,two,no
    }

    Player currnetplayer = Player.one;

    Player[] playerTurns = new Player[9];
    int [][] array = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    private boolean gameover = false;
    public int f = 0,p=0;

    private Button btn;
    private GridLayout gridView;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<playerTurns.length;i++) {
            playerTurns[i] = Player.no;
        }
//        playerTurns[0] = Player.no;
//        playerTurns[1] = Player.no;
//        playerTurns[2] = Player.no;
//        playerTurns[3] = Player.no;
//        playerTurns[4] = Player.no;
//        playerTurns[5] = Player.no;
//        playerTurns[6] = Player.no;
//        playerTurns[7] = Player.no;
//        playerTurns[8] = Player.no;

        btn = findViewById((R.id.button));
        gridView = findViewById(R.id.grid);
        t = findViewById(R.id.textView3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetgame();
            }
        });
    }

    private void resetgame() {
        for(int index = 0 ; index< gridView.getChildCount();index++){
            ImageView imageView = (ImageView) gridView.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.6f);
        }
        currnetplayer = Player.one;

        for(int i=0;i<playerTurns.length;i++) {
            playerTurns[i] = Player.no;
        }


        gameover = false;
        btn.setVisibility(View.INVISIBLE);
       // t.setVisibility(View.INVISIBLE);
    }

    public void imgviewtapped(View imgView) {

        ImageView tappedImgView = (ImageView) imgView; //ImageView is subclass of View
        int tiTag = Integer.parseInt(tappedImgView.getTag().toString());//to refer to tag

        if (playerTurns[tiTag] == Player.no && gameover == false) {
            tappedImgView.setTranslationX(-2000);
            playerTurns[tiTag] = currnetplayer;//when taps imageView assign valye current player to it
            if (currnetplayer == Player.one) {
                tappedImgView.setImageResource(R.drawable.tiger);
                currnetplayer = Player.two;
            } else if (currnetplayer == Player.two) {
                tappedImgView.setImageResource(R.drawable.lion);
                currnetplayer = Player.one;
            }

            tappedImgView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);

//        Toast.makeText(this,tappedImgView.getTag().toString(),Toast.LENGTH_SHORT).show();
            for (int[] arrayColumns : array) {
                if (playerTurns[arrayColumns[0]] == playerTurns[arrayColumns[1]] &&
                        playerTurns[arrayColumns[1]] == playerTurns[arrayColumns[2]] &&
                        playerTurns[arrayColumns[0]] != Player.no) {

//                    btn.setVisibility(View.VISIBLE);
                    gameover = true;
                    String winnerOfGame = "";

                    if (currnetplayer == Player.one) {
                        //Toast.makeText(this,"PLAYER ONE IS THE WINNER",Toast.LENGTH_SHORT).show();
                        winnerOfGame = "Player TWO/LION";
                        f=f+1;

                    } else if (currnetplayer == Player.two) {
                        winnerOfGame = "Player ONE/TIGER";
                        p=p+1;
                    }
                    Toast.makeText(this, winnerOfGame + " is the winner", Toast.LENGTH_LONG).show();
                }
                btn.setVisibility(View.VISIBLE);
               // t.setVisibility(View.VISIBLE);
                t.setText("Final Score:- \nPlayer One: " + p + " Player Two: " + f);

            }
        }
    }
}
