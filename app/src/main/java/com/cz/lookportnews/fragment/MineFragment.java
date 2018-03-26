package com.cz.lookportnews.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cz.lookportnews.R;
import com.cz.lookportnews.activity.HomeActivity;
import com.cz.lookportnews.ui.FontPopupWindow;
import com.cz.lookportnews.ui.ResponseOnTouch;
import com.cz.lookportnews.util.Constant;
import com.cz.lookportnews.util.UIUtils;
import com.cz.lookportnews.util.Util;

import butterknife.BindView;
import skin.support.SkinCompatManager;
import skin.support.annotation.Skinable;

/**
 * Created by 14221 on 2018/2/7.
 */

public class MineFragment extends LazyFragment {

    private static final String TAG = "MineFragment";

    private static String skinName = "";

    @BindView(R.id.tv_collection)
    private TextView collection;

    @BindView(R.id.iv_moon1)
    ImageView themeView;
    @BindView(R.id.iv_font)
    ImageView fontView;


    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews(View view) {

        //我的收藏
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fontView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.showPopWindow(getActivity(), HomeActivity.fontPopupWindow, getView());
            }
        });

        themeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skinName = SkinCompatManager.getInstance().getCurSkinName();
                if ("night.skin".equals(skinName)) {
                    themeView.setImageResource(R.drawable.sun);

                    SkinCompatManager.getInstance().restoreDefaultTheme();
                } else {
                    themeView.setImageResource(R.drawable.moon1);
                    SkinCompatManager.getInstance()
                            .loadSkin("night.skin", new SkinCompatManager.SkinLoaderListener() {
                                @Override
                                public void onStart() {

                                }

                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onFailed(String errMsg) {

                                }
                            }, SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
                }


            }
        });
    }


}
