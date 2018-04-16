package com.example.galv.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import java.util.Random;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userName;
    private int numRow;
    private int numCol;
    private int level;
    private int numberOfElements; //*level
    private static final int EASY=2, NORMAL=4, HARD=6;
    // public static final int BOARD_EASY =EASY*EASY, BOARD_NORMAL = NORMAL*NORMAL ,BOARD_HARD = HARD*HARD ;

    private MemoryButton[] buttons;

    private int[] buttonsGraphicsLocations; // 2-1 2-2 -2-3 HOLD THE ACTUAL RESPURCE VALUES THE INTEGER VALUES
    private int [] buttonGraphics;

    private MemoryButton selectButton1;
    private MemoryButton selectButton2;
    private boolean isBusy = false;

    GridLayout grid_layout;

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
            startGame();

//            switch (level) {
//
//                case NORMAL: {
//
//                   startGame();
//                        //}
//
////                    for (int r = 0; r < numRow; r++) {
////                        for (int c = 0; c < numCol; c++) {
////                            int location = r * numCol + c;
////                            //tempImageId = ((r * (numRow - fixIndexNumber)) + c) / 2;
////                            //buttons[(r * (numRow - fixIndexNumber)) + c]
////                            int place = buttonsGraphicsLocations[location];
////                            MemoryButton tempButton = new MemoryButton(this, r, c, buttonGraphics[place]);
////                            tempButton.setId(View.generateViewId());
////                            tempButton.setOnClickListener(this);
////                            buttons[location] = tempButton;
////                            grid_layout.addView(tempButton);
////
////                //  }
//                    break;
//                }
//
//
//                case EASY: {
//                    startGame();
//                    break;
//                }
//                case HARD: {
//                    startGame();
//                    break;
//                }
//            }
        }
    }

    private void startGame() {
        detailsUser();
        buildBorad();
        inputPicToButton();
        shuffleButtonsGraphics();
        createMemoryButtons();
    }

    private void detailsUser() {
       String StringUserName = getIntent().getExtras().getString("userName");
        userName=(TextView)findViewById(R.id.userName);
        userName.setText(StringUserName);



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
        grid_layout = (GridLayout)findViewById(R.id.grid_layout);
        numRow = level;
        numCol = level;
        numberOfElements = numCol * numRow;

        buttons=new MemoryButton[numberOfElements];
        grid_layout.setColumnCount(numCol);
        grid_layout.setRowCount(numRow);
        buttonGraphics = new int[numberOfElements / 2];

        buttonsGraphicsLocations = new int[numberOfElements];
    }

    private void inputPicToButton() {
        buttonGraphics[0] = R.drawable.blue2;
        buttonGraphics[1] = R.drawable.blue;
        if (level==NORMAL || level== HARD) {
            buttonGraphics[2] = R.drawable.red;
            buttonGraphics[3] = R.drawable.green2;
            buttonGraphics[4] = R.drawable.green;
            buttonGraphics[5] = R.drawable.yellow;
            buttonGraphics[6] = R.drawable.puruple;
            buttonGraphics[7] = R.drawable.greencircle;
        }
        if (level== HARD) {
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
           // buttonGraphics[18] = R.drawable.pinkyellow;
        }
    }


    private void shuffleButtonsGraphics() {
        Random rand=new Random();
        for (int i=0; i<numberOfElements ; i++)
        {
            buttonsGraphicsLocations[i]=i% (numberOfElements/2);
        }
        for(int i=0; i<numberOfElements ; i++) {
            int temp =buttonsGraphicsLocations[i];
            int swapIndex=rand.nextInt(4);

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
