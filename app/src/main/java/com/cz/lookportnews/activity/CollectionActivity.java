package com.cz.lookportnews.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cz.lookportnews.R;
import com.cz.lookportnews.adapter.FragmentAdapter;
import com.cz.lookportnews.fragment.HomeFragment;
import com.cz.lookportnews.fragment.MineFragment;

import com.cz.lookportnews.fragment.SubScribeFragment;
import com.cz.lookportnews.fragment.ThreeFragment;
import com.cz.lookportnews.fragment.TwoFragment;
import com.cz.lookportnews.ui.FontPopupWindow;
import com.cz.lookportnews.ui.MyWebView;
import com.cz.lookportnews.ui.NoScrollViewPager;
import com.cz.lookportnews.ui.PagerSlidingTabStrip;
import com.cz.lookportnews.ui.TabPageChangeListenner;
import com.cz.lookportnews.util.Constant;
import com.cz.lookportnews.util.UIUtils;
import com.cz.lookportnews.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import skin.support.annotation.Skinable;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by 14221 on 2018/2/7.
 */


public class CollectionActivity extends BasActivity {

    private static final String TAG = "CollectionActivity";




    @Override
    public int getLayout() {
        return R.layout.parent_viewpager;
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {

    }



}
