package com.cz.lookportnews.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cz.lookportnews.R;

import butterknife.BindView;
import skin.support.SkinCompatManager;
import skin.support.annotation.Skinable;

/**
 * Created by 14221 on 2018/2/7.
 */

public class MineFragment extends LazyFragment {

    private static final String TAG = "MineFragment";

    private static String skinName= "";

    private TextView textView;

    @BindView(R.id.iv_moon1)
    ImageView themeView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews(View view) {
        themeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                skinName=SkinCompatManager.getInstance().getCurSkinName();

                if("night.skin".equals(skinName)){
                    themeView.setImageResource(R.drawable.sun);
                    SkinCompatManager.getInstance().restoreDefaultTheme();
                }
                else {

                    themeView.setImageResource(R.drawable.moon1);
                    SkinCompatManager.getInstance()
                            .loadSkin("night.skin", null, SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
                }



            }
        });
    }
}
