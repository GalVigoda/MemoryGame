package com.example.galv.myapplication;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TableLayout;

import java.util.Random;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    private int level;
    private int numberOfElements; //*level
    private static final int EASY=2, NORMAL=4, HARD=6;
    // public static final int BOARD_EASY =EASY*EASY, BOARD_NORMAL = NORMAL*NORMAL ,BOARD_HARD = HARD*HARD ;

    private MemoryButton[] buttons;

    private int[] buttonsGraphicsLocations; // 2-1 2-2 -2-3 HOLD THE ACTUAL RESPURCE VALUES THE INTEGER VALUES
    private Integer buttonGraphics[];

    private MemoryButton selectButton1;
    private MemoryButton selectButton2;
    private boolean isBusy = false;

    //check

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        createNewGame();
    }

    private void createNewGame() {

        // Bundle ex;
        Intent intent = getIntent();
        if (intent != null) {
            //   ex = intent.getExtras();
            level = getIntent().getExtras().getInt("level");


            switch (level) {

                case NORMAL: {
                    GridLayout grid_layout = (GridLayout)findViewById(R.id.grid_layout);
                    int numRow = level;
                    int numCol = level;
                    numberOfElements = numCol * numRow;
                    int fixIndexNumber = (numRow == numCol ? 0 : 1);

                    buttons=new MemoryButton[numberOfElements];
                    grid_layout.setColumnCount(numCol);
                    grid_layout.setRowCount(numRow);

                    buttonGraphics = new Integer[numberOfElements / 2];

                    buttonGraphics[0] = R.drawable.plutodog;
                    buttonGraphics[1] = R.drawable.dov687877833843232;
                    buttonGraphics[2] = R.drawable.smiba123;
                    buttonGraphics[3] = R.drawable.flyd4276204ef7f4fe7c7d98913435ba808;
                    buttonGraphics[4] = R.drawable.mini16334223;
                    buttonGraphics[5] = R.drawable.mixze524318ea1beb2_mask;
                    buttonGraphics[6] = R.drawable.twogirls;
                    buttonGraphics[7] = R.drawable.yellowgirlpowerpoh;

                    buttonsGraphicsLocations = new int[numberOfElements];



                    for (int r = 0; r < numRow; r++) {
                        for (int c = 0; c < numCol; c++) {
                            int location = r * numCol + c;
                            //tempImageId = ((r * (numRow - fixIndexNumber)) + c) / 2;
                            //buttons[(r * (numRow - fixIndexNumber)) + c]
                            int place= buttonGraphics[buttonsGraphicsLocations[location]];
                            MemoryButton tempButton;
                           tempButton = new MemoryButton(this, r, c,  place);
                            tempButton.setId(View.generateViewId());
                            tempButton.setOnClickListener(this);
                            buttons[location] = tempButton;
                            grid_layout.addView(tempButton);
                        }
                    }

                    shuffleButtonsGraphics();
                    //  InitBoard(BOARD_EASY);
                    break;
                }


                case EASY: {

                    break;
                }
                case HARD: {

                    break;
                }
            }
        }
    }

    private void shuffleButtonsGraphics() {
        Random rand=new Random();
        for (int i=0; i<numberOfElements ; i++)
        {
            buttonsGraphicsLocations[1]=i% (numberOfElements/2);
        }
        for(int i=0; i<numberOfElements ; i++) {
            int temp =buttonsGraphicsLocations[i];
            int swapIndex=rand.nextInt(16);

            buttonsGraphicsLocations[i]=buttonsGraphicsLocations[swapIndex];
            buttonsGraphicsLocations[swapIndex]=temp;
        }
    }

    @Override
    public void onClick(View view) {

        if(isBusy)
            return;

        MemoryButton button=(MemoryButton)view;


        if (button.isMatched)
            return;

        if(selectButton1==null) {
            selectButton1 = button;
            selectButton1.flip();
            return;
        }

        if(selectButton1.getId()==button.getId()) {
            return;
        }

        if (selectButton1.getFrontImageDrawableId()==button.getFrontImageDrawableId()){

            button.flip();

            button.setMatched(true);
            selectButton1.setMatched(true);

            selectButton1.setEnabled(false);
            button.setEnabled(false);

            selectButton1=null;
            return;
        }
        else{

            selectButton2=button;
            selectButton2.flip();
            isBusy=true;
            final Handler handler= new Handler();
            handler.postDelayed(new Runnable(){

                @Override
                public void run() {
                    selectButton2.flip();
                    selectButton1.flip();

                    selectButton1=null;
                    selectButton2=null;
                    isBusy=false;
                }
            },500);
        }
    }
}
