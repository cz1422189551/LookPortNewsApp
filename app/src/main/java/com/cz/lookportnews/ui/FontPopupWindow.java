package com.cz.lookportnews.ui;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cz.lookportnews.R;
import com.cz.lookportnews.util.Constant;
import com.cz.lookportnews.util.Util;

import java.util.ArrayList;


/**
 * Created by 14221 on 2018/3/2.
 */

public class FontPopupWindow extends PopupWindow implements ResponseOnTouch {


    private static final String TAG = "FontPopupWindow";

    private Context mContext;

    private SharedPreferences sharedPreferences=null;

    CustomSeekbar customSeekBar;

    View view;

    public FontPopupWindow(final Context mContext , int width , int height) {

        this.mContext = mContext;

        this.view = LayoutInflater.from(mContext).inflate(R.layout.pop_font, null);

        customSeekBar = view.findViewById(R.id.myCustomSeekBar);
        initSeekBar();

        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(height);
        this.setWidth(width);
        sharedPreferences=mContext.getSharedPreferences("TextSize",Context.MODE_PRIVATE);

        //如果不设置背景颜色的话，无法是pop dimiss掉。
        this.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.popupwindow_background));

        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//        this.view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                int height = view.findViewById(R.id.pop_layout).getTop();
//                int y = (int) motionEvent.getY();
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) {
//                        dismiss();
//                    }
//                }
//                return false;
//            }
//        });
        this.setAnimationStyle(R.style.MyPopupWindow_anim_style);


    }

    private void initSeekBar(){
         ArrayList<String> volume_sections = new ArrayList<String>();
        volume_sections.add("小");
        volume_sections.add("中");
        volume_sections.add("大");
        volume_sections.add("特大");
        customSeekBar.initData(volume_sections);
        int position=Util.getSharedPreferences(mContext,"TextSize").getInt("fontSize",1);
        customSeekBar.setProgress(position);
        customSeekBar.setResponseOnTouch(this);
    }

    @Override
    public void onTouchResponse(int volume) {
        Constant.TEXT_SIZE = volume;
         SharedPreferences.Editor edit =sharedPreferences.edit();
        edit.putInt("fontSize",volume);
        edit.apply();
        int position=sharedPreferences.getInt("fontSize",1);

        Log.d(TAG, "onTouchResponse: "+position);
        Constant.notification();
    }

    @Override
    public void dismiss() {

        super.dismiss();
    }
}
