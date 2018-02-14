package com.cz.lookportnews.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cz.lookportnews.R;

/**
 * Created by 14221 on 2018/2/11.
 */

public class HeadPushView extends View {

    private static final String TAG = "HeadPushView";
    private Paint paint;


    private static float parentWidth = 0;

    private static float parentHeight = 0;

    private int pos;

    private float bilie = 6;
    private float rotaNumber = 0.983f;

    //初始化旋转角度
    private float rotaValue = 45;

    private float rotaBilie =35.5f;

    private float baseValue = 22;


    public HeadPushView(Context context) {
        super(context);
        parentHeight = getMeasuredHeight();
        parentWidth = getMeasuredWidth();
    }

    public HeadPushView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        parentHeight = getMeasuredHeight();
        parentWidth = getMeasuredWidth();
    }

    public HeadPushView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parentHeight = getMeasuredHeight();
        parentWidth = getMeasuredWidth();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HeadPushView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        parentHeight = getMeasuredHeight();
        parentWidth = getMeasuredWidth();

    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (paint == null) {
            paint = new Paint();
            paint.setColor(R.color.base_color);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(1.0f);
        } else {
            float degrees = rotaValue +(pos/rotaNumber);
            if(degrees>=180)
                degrees=180;
            canvas.rotate(degrees,22f,22f);
            if(degrees>=135){

                float i = degrees-135;
                float temp =(i/rotaBilie);
                Log.d(TAG, "onDraw:  temp" +temp);
                canvas.drawRect((baseValue - (pos / bilie)+temp), (baseValue - (pos / bilie)+temp),
                        (baseValue + (pos / bilie)-temp), (baseValue + (pos / bilie)-temp), paint);
            } else {
                canvas.drawRect((baseValue - (pos / bilie)), (baseValue - (pos / bilie)), (baseValue + (pos / bilie)), (baseValue + (pos / bilie)), paint);
            }

        }

        pos = 0;
    }


    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
