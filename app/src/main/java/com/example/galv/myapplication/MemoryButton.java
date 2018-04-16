package com.example.galv.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;


import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.ButtonBarLayout;
import android.widget.Button;

import android.widget.GridLayout;
import android.widget.ImageButton;
//import android.os.Build;
//import android.support.v7.widget.ButtonBarLayout;

@SuppressLint("AppCompatCustomView")
public class MemoryButton extends Button {
///
    protected int row;
    protected int col;
    protected int frontImageDrawableId;
    private int level;

    protected boolean isFlipped= false;
    protected boolean isMatched= false;
    private static final int EASY=2, NORMAL=4, HARD=6;

    protected Drawable front;
    protected Drawable back;

    @SuppressLint("RestrictedApi")
    public MemoryButton(Context context, int r, int c, int frontImageDrawableId, int level) {
        super(context);
        row = r;
        col = c;
        this.frontImageDrawableId = frontImageDrawableId;
           // front= context.getDrawable(frontImageDrawableId);
           // back= context.getDrawable(R.drawable.smiba123);



        back = context.getResources().getDrawable(R.drawable.question, null);
        front = AppCompatDrawableManager.get().getDrawable(context, frontImageDrawableId);
       // front = context.getResources().getDrawable(this.frontImageDrawableId,null);
      //   front = AppCompatDrawableManager.get().getDrawable(context, frontImageDrawableId);   //Crash here =\ fix it
       //  back = AppCompatDrawableManager.get().getDrawable(context, R.drawable.question);    //Crash here =\ fix it

      //  front.setIm
        //  front.setImageResource(R.drawable.background_grey);
        // back = context.getDrawable(R.drawable.pic2857001201062919813);
        //front = context.getDrawable(frontImageDrawableId);

       // ContextCompat.getDrawable(getActivity(), R.drawable.name);
       // back=AppCompatDrawableManager.get().getDrawable(context,frontImageDrawableId);
        // back= context.getResources().getDrawable(R.drawable.star, null);
        this.setBackground(back);
       //s back.getDrawable(context, R.drawable.smiba123);

     //   back=context.getResources().getDrawable(R.drawable.smiba123, null);
        sizeCards(level);

    }

    private void sizeCards(int level) {
        switch (level) {

            case EASY: {
                GridLayout.LayoutParams tempParams =new GridLayout.LayoutParams(GridLayout.spec(row),GridLayout.spec(col));
                tempParams.width=(int) getResources().getDisplayMetrics().density*200;
                tempParams.height=(int) getResources().getDisplayMetrics().density*200;

                setLayoutParams(tempParams);
                break;
            }


            case NORMAL: {
                GridLayout.LayoutParams tempParams =new GridLayout.LayoutParams(GridLayout.spec(row),GridLayout.spec(col));
                tempParams.width=(int) getResources().getDisplayMetrics().density*90;
                tempParams.height=(int) getResources().getDisplayMetrics().density*90;
                setLayoutParams(tempParams);

                break;
            }
            case HARD: {
                GridLayout.LayoutParams tempParams =new GridLayout.LayoutParams(GridLayout.spec(row),GridLayout.spec(col));
                tempParams.width=(int) getResources().getDisplayMetrics().density*55;
                tempParams.height=(int) getResources().getDisplayMetrics().density*55;
                setLayoutParams(tempParams);

                break;
            }
        }
    }


    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }


    public int getFrontImageDrawableId() {
        return frontImageDrawableId;
    }

    public void flip(){

        if (isMatched)
            return;

        if(isFlipped) {
            setBackground(back);
            isFlipped = false;
        }
        else
        {
            setBackground(front);
            isFlipped=true;
        }




    }
}
