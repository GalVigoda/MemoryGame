package com.example.galv.myapplication;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.GridLayout;

@SuppressLint("AppCompatCustomView")
public class MemoryButton extends Button {
////
    protected int row;
    protected int col;
    protected int frontImageDrawableId;

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

        back = context.getResources().getDrawable(R.drawable.question, null);
        front = AppCompatDrawableManager.get().getDrawable(context, frontImageDrawableId);

         this.setBackground(back);
         sizeCards(level);
    }

    private void sizeCards(int level) {
        switch (level) {

            case EASY: {
                GridLayout.LayoutParams tempParams =new GridLayout.LayoutParams(GridLayout.spec(row),GridLayout.spec(col));
                tempParams.width=(int) getResources().getDisplayMetrics().density*160;
                tempParams.height=(int) getResources().getDisplayMetrics().density*160;
                setLayoutParams(tempParams);

                break;
            }

            case NORMAL: {
                GridLayout.LayoutParams tempParams =new GridLayout.LayoutParams(GridLayout.spec(row),GridLayout.spec(col));
                tempParams.width=(int) getResources().getDisplayMetrics().density*85;
                tempParams.height=(int) getResources().getDisplayMetrics().density*85;
                setLayoutParams(tempParams);

                break;
            }
            case HARD: {
                GridLayout.LayoutParams tempParams =new GridLayout.LayoutParams(GridLayout.spec(row),GridLayout.spec(col));
                tempParams.width=(int) getResources().getDisplayMetrics().density*58;
                tempParams.height=(int) getResources().getDisplayMetrics().density*58;
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

        if (isMatched())

            return;

        if(isFlipped) {
            setBackground(back);
            setFlipped(false);
        }
        else
        {
            setBackground(front);
            isFlipped=true;
        }
    }
}
