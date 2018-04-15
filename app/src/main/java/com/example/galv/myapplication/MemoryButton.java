package com.example.galv.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;


import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.ButtonBarLayout;
import android.widget.Button;

import android.widget.GridLayout;
import android.widget.ImageButton;
//import android.os.Build;
//import android.support.v7.widget.ButtonBarLayout;

@SuppressLint("AppCompatCustomView")
public class MemoryButton extends ImageButton {

    protected int row;
    protected int col;
    protected int frontImageDrawableId;

    protected boolean isFlipped= false;
    protected boolean isMatched= false;

    protected Drawable front;
    protected Drawable back;


    @SuppressLint("RestrictedApi")
    public MemoryButton(Context context, int r, int c, int frontImageDrawableId) {
        super(context);

        row = r;
        col = c;
        this.frontImageDrawableId = frontImageDrawableId;


        front = AppCompatDrawableManager.get().getDrawable(context, frontImageDrawableId);   //Crash here =\ fix it
        back = AppCompatDrawableManager.get().getDrawable(context, R.drawable.smiba123);    //Crash here =\ fix it

      //  front.setIm
        //  front.setImageResource(R.drawable.background_grey);
        // back = context.getDrawable(R.drawable.pic2857001201062919813);
        //front = context.getDrawable(frontImageDrawableId);

       // ContextCompat.getDrawable(getActivity(), R.drawable.name);
       // back=AppCompatDrawableManager.get().getDrawable(context,frontImageDrawableId);
        // back= context.getResources().getDrawable(R.drawable.star, null);

        setBackground(back);


       //s back.getDrawable(context, R.drawable.smiba123);

     //   back=context.getResources().getDrawable(R.drawable.smiba123, null);



        GridLayout.LayoutParams tempParams =new GridLayout.LayoutParams(GridLayout.spec(r),GridLayout.spec(c));

        tempParams.width=(int) getResources().getDisplayMetrics().density*50;
        tempParams.height=(int) getResources().getDisplayMetrics().density*50;

        setLayoutParams(tempParams);
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
