package com.example.galv.myapplication;
import tyrantgit.explosionfield.ExplosionField;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.widget.GridLayout;
import android.os.CountDownTimer;
import android.widget.TextView;
import java.util.Random;

import android.widget.Toast;

@SuppressWarnings("ALL")
public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userName;
    private int numOfCorrectCrd, numRow, numCol, level, numberOfElements, timeForTimer;
    private static final int NORMAL = 4, HARD = 6;
    private String StringUserName;
    DatabaseHelper scoreDB;

    private int[] buttonsGraphicsLocations;
    private int[] buttonGraphics;

    private MemoryButton[] buttons;
    private MemoryButton selectButton1;
    private MemoryButton selectButton2;
    private boolean isBusy = false, gameFinishWin = false, gameFinishLose = false;

    GridLayout grid_layout;

    //timer
    private TextView tvCountDownText;
    private CountDownTimer CountDownTimer;
    private long timeLeftInMilliSecendes;
    ; // 30 seconds== 30 000 milliseconds

    long animationDurtion = 1000; // miliSecends
    ExplosionField explosionField;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        createNewGame();


        //ExplosionField
        //      explosionField = ExplosionFie
        //  updateTimer();
    }

    private void createNewGame() {

        Intent intent = getIntent();
        if (intent != null) {
            level = getIntent().getExtras().getInt("level");
            startGame();
        }
    }

    private void startGame() {
        detailsUser();
        buildBorad();
        inputPicToButton();
        shuffleButtonsGraphics();
        createMemoryButtons();
        startTheTimer();
    }

    private void startTheTimer() {
        findTimerViewById();
        checkTheTimesForTheGame();
        StartTimer();
    }

    private void checkTheTimesForTheGame() {
        timeForTimer = getIntent().getExtras().getInt("timeForTimer");
        timeLeftInMilliSecendes = timeForTimer;
    }

    private void findTimerViewById() {

        tvCountDownText = findViewById(R.id.textTimer);

    }

    private void StartTimer() {

        CountDownTimer = new CountDownTimer(timeLeftInMilliSecendes, 1000) {
            @Override
            public void onTick(long long1) {
                timeLeftInMilliSecendes = long1;
                updateTimer();
            }

            @Override
            public void onFinish() {
                gameFinishLose = true;
                FinishGame();
            }
        }.start();

        // timeRuning=true;
    }

    public void updateTimer() {
        int minutes = 0;
        int seconds = (int) (timeLeftInMilliSecendes / 1000) % 60;
        String timeLeftText;
        timeLeftText = String.format("%02d:%02d", minutes, seconds);
        tvCountDownText.setText(timeLeftText);
    }

    private void detailsUser() {
        StringUserName = getIntent().getExtras().getString("userName");
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(StringUserName);
        scoreDB=new DatabaseHelper(this);
    }

    private void createMemoryButtons() {
        int row, column, location;
        for (row = 0; row < numRow; row++) {
            for (column = 0; column < numCol; column++) {
                location = row * numCol + column;
                MemoryButton tempButton = new MemoryButton(this, row, column, buttonGraphics[buttonsGraphicsLocations[location]], level);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons[location] = tempButton;
                grid_layout.addView(tempButton);
            }
        }
    }

    private void buildBorad() {
        grid_layout = (GridLayout) findViewById(R.id.grid_layout);
        numRow = level;
        numCol = level;
        numberOfElements = numCol * numRow;

        buttons = new MemoryButton[numberOfElements];
        grid_layout.setColumnCount(numCol);
        grid_layout.setRowCount(numRow);
        buttonGraphics = new int[numberOfElements / 2];

        buttonsGraphicsLocations = new int[numberOfElements];
    }

    private void inputPicToButton() {
        buttonGraphics[0] = R.drawable.blue2;  //for sure (easy level)
        buttonGraphics[1] = R.drawable.blue;    //for sure (easy level)
        if (level == NORMAL || level == HARD) {
            buttonGraphics[2] = R.drawable.red;
            buttonGraphics[3] = R.drawable.green2;
            buttonGraphics[4] = R.drawable.green;
            buttonGraphics[5] = R.drawable.yellow;
            buttonGraphics[6] = R.drawable.puruple;
            buttonGraphics[7] = R.drawable.greencircle;
        }
        if (level == HARD) {
            buttonGraphics[8] = R.drawable.brown;
            buttonGraphics[9] = R.drawable.orange;
            buttonGraphics[10] = R.drawable.hetz;
            buttonGraphics[11] = R.drawable.star;
            buttonGraphics[12] = R.drawable.redbrown;
            buttonGraphics[13] = R.drawable.redoragne;
            buttonGraphics[14] = R.drawable.yellowbrown;
            buttonGraphics[15] = R.drawable.white;
            buttonGraphics[16] = R.drawable.yellwlines;
            buttonGraphics[17] = R.drawable.bluered;
        }
    }


    private void shuffleButtonsGraphics() {
        Random rand = new Random();
        for (int i = 0; i < numberOfElements; i++) {
            buttonsGraphicsLocations[i] = i % (numberOfElements / 2);
        }
        for (int i = 0; i < numberOfElements; i++) {
            int temp = buttonsGraphicsLocations[i];
            int swapIndex = rand.nextInt(4);

            buttonsGraphicsLocations[i] = buttonsGraphicsLocations[swapIndex];
            buttonsGraphicsLocations[swapIndex] = temp;
        }
    }

    @Override
    public void onClick(View view) {
        //doAnimationWin();
        if (isBusy)
            return;

        MemoryButton button = (MemoryButton) view;

        if (button.isMatched)
            return;

        if (selectButton1 == null) {
            selectButton1 = button;
            selectButton1.flip();

            return;
        }

        if (selectButton1.getId() == button.getId()) {
            return;
        }

        if (selectButton1.getFrontImageDrawableId() == button.getFrontImageDrawableId()) {

            button.flip();

            button.setMatched(true);
            selectButton1.setMatched(true);

            selectButton1.setEnabled(false);
            button.setEnabled(false);

            selectButton1 = null;
            checkIfWin();
            return;
        } else {

            selectButton2 = button;
            selectButton2.flip();
            isBusy = true;

            handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                selectButton2.flip();
                selectButton1.flip();

                selectButton1 = null;
                selectButton2 = null;
                isBusy = false;
            }
        }, 400);
    }
    }

    private void checkIfWin() {

        numOfCorrectCrd++;
        if (numOfCorrectCrd == numberOfElements / 2) {
            gameFinishWin = true;
            // closeTimer();
            FinishGame();
          //  backToSecendActivity();
            // thread.start();
            //  finish=true;
        }
    }

    private void FinishGame() {
        Context context = getApplicationContext();
        if (gameFinishWin == true) { // win
            String Win="You Win ! ! ! :) " ;
            Toast.makeText(context, Win, Toast.LENGTH_SHORT).show();
               doAnimationWin();
               gameFinishWin = false;
        }
        if (gameFinishLose == true) { // Lose
                doAnimationLose();
                gameFinishLose = false;
                String lose="Game Over You Lose :( " ;
                Toast.makeText(context, lose, Toast.LENGTH_SHORT).show();

        }
    }



    protected void doAnimationWin() {

        //add animation win!!!
        CountDownTimer.cancel();
        handler.postDelayed(goToListScoreActivity, 3000);
        handler.postDelayed(addData, 3000);
       }

    private Runnable addData=new Runnable() {
        @Override
        public void run() {
            boolean insertData= scoreDB.addData(StringUserName,timeLeftInMilliSecendes);
            Context context = getApplicationContext();
            if (insertData==true) {
                String save = "this data save ";
                Toast.makeText(context, save, Toast.LENGTH_SHORT).show();
            }
            else{
                String notSave = "this data wasn't save ";
                Toast.makeText(context, notSave, Toast.LENGTH_SHORT).show();
            }
        }
    };



    protected void doAnimationLose() {

        explosionField = ExplosionField.attach2Window(this);
        explosionField.explode(grid_layout);




        handler.postDelayed(goToListScoreActivity, 3000);

    }

    private Runnable goToListScoreActivity=new Runnable() {
        @Override
        public void run() {
            Intent intent=new Intent(ThirdActivity.this, ListScore.class);
            startActivity(intent);
        }
    };


// settings
//        ObjectAnimator animatorX=ObjectAnimator.ofFloat( buttonGraphics[0],"x",420f);
//        //bjectAnimator animatorY=ObjectAnimator.ofFloat( buttonGraphics[1],"x",200f);
//        animatorX.setDuration(animationDurtion);  // time show
//        //for start the animations:
//        AnimatorSet animatorSet=new AnimatorSet();
//        animatorSet.playTogether(animatorX);//,animatorY);
//        animatorSet.start();


//
//    private void doAnimationLose() {

//        explosionField.explode(grid_layout);
//
//        for (int  i=0; i <100000000 ; i++ )             //// delete it!!
//        {}
//
//    }






        //I fixed from mMtala num 1
//       CountDownTimer.cancel();
//
//        Handler tempHandler = new Handler();
//        tempHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent backMenu = new Intent(ThirdActivity.this, SecendActivity.class);
//                startActivity(backMenu);
//            }
//        }, 2000);

    }




