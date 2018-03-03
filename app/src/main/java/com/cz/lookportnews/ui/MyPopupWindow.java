package com.cz.lookportnews.ui;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cz.lookportnews.R;

import java.lang.reflect.Method;

/**
 * Created by 14221 on 2018/3/2.
 */

public class MyPopupWindow extends PopupWindow {

    private Context mContext;

    private View view;

    private ImageView shareView ;

    private ImageView copyView;

    private ImageView reportView;

    private int width ;

    private int height;

    LinearLayout linearLayout ;


    private TextView copyString ;

    ClipboardManager cm = null;

    public MyPopupWindow(final Context mContext, int width, int height, LinearLayout linearLayout  ) {

        this.mContext =mContext;
        this.width=width;
        this.height=height;
        this.linearLayout=linearLayout;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.pop_detail, null);
        cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        shareView = view.findViewById(R.id.iv_share);
        copyView = view.findViewById(R.id.iv_copy);
        reportView = view.findViewById(R.id.iv_report);

        copyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyComment(copyString.getText().toString());
            }
        });

        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(height);
        this.setWidth(width);

        //如果不设置背景颜色的话，无法是pop dimiss掉。
        this.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.popupwindow_background));
        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int height = view.findViewById(R.id.pop_layout).getTop();
                int y = (int) motionEvent.getY();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return false;
            }
        });
        this.setAnimationStyle(R.style.MyPopupWindow_anim_style);


    }


    @Override
    public void dismiss() {
        linearLayout.setVisibility(View.VISIBLE);
        super.dismiss();
    }


    public void copyComment(String copyString) {
        // 将文本内容放到系统剪贴板里。
        cm.setText(copyString);
        Toast.makeText(mContext, "已将评论复制到剪切板。", Toast.LENGTH_SHORT).show();

    }

    public void setCopyString(TextView copyString) {
        this.copyString = copyString;
    }
}
