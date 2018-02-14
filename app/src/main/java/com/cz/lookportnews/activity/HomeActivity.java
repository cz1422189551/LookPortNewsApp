package com.cz.lookportnews.activity;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cz.lookportnews.R;
import com.cz.lookportnews.adapter.FragmentAdapter;
import com.cz.lookportnews.fragment.HomeFragment;
import com.cz.lookportnews.fragment.MineFragment;
import com.cz.lookportnews.fragment.OneFragment;
import com.cz.lookportnews.fragment.SubScribeFragment;
import com.cz.lookportnews.fragment.ThreeFragment;
import com.cz.lookportnews.fragment.TwoFragment;
import com.cz.lookportnews.ui.NoScrollViewPager;
import com.cz.lookportnews.ui.PagerSlidingTabStrip;
import com.cz.lookportnews.ui.TabPageChangeListenner;
import com.cz.lookportnews.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 14221 on 2018/2/7.
 */

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

     FragmentAdapter fragmentAdapter;

     SubScribeFragment subScribeFragment;

     MineFragment mineFragment;


     HomeFragment homeFragment;

    @BindView(R.id.ll_news)
    LinearLayout newsLinearLayout;
    @BindView(R.id.ll_subscribe)
    LinearLayout subScribeLinearLayout;
    @BindView(R.id.ll_my)
    LinearLayout mineLinearLayout;


    @BindView(R.id.no_scroll_viewpager)
    NoScrollViewPager noScrollViewPager ;

     @BindView(R.id.tv_news)
     TextView newsTextView;
    @BindView(R.id.tv_subscribe)
     TextView subScribeTextView;
    @BindView(R.id.tv_mine)
     TextView mineTextView;

    @BindView(R.id.iv_news)
     ImageView newsImageView;
    @BindView(R.id.iv_subscribe)
     ImageView subScribeImageView;
    @BindView(R.id.iv_my)
     ImageView mineImageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_viewpager);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        //设置底部Tab字体中文加粗
        UIUtils.setChineseTextViewBold(newsTextView,subScribeTextView,mineTextView);

        //初始化切换Bottom的Tab的点击事件
        LinearLayoutListener linearLayoutListener = new LinearLayoutListener();
        newsLinearLayout.setOnClickListener(linearLayoutListener);
        subScribeLinearLayout.setOnClickListener(linearLayoutListener);
        mineLinearLayout.setOnClickListener(linearLayoutListener);


        //bottomViewPager
        homeFragment = new HomeFragment();
        subScribeFragment  = new SubScribeFragment();
        mineFragment = new MineFragment();

        List<Fragment> bottomFragment = new ArrayList<>();
        bottomFragment.add(homeFragment);
        bottomFragment.add(subScribeFragment);
        bottomFragment.add(mineFragment);

        fragmentAdapter = new FragmentAdapter(this,bottomFragment,getSupportFragmentManager());
        noScrollViewPager.setAdapter(fragmentAdapter);
        noScrollViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                resetView();
                resetPic();
                switch (position) {
                    case 0:
                        newsTextView.setTextColor(Color.parseColor("#337FFF"));
                        newsTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        resetPic();
                        newsImageView.setBackgroundResource(R.drawable.news_1);
                        break;
                    case 1:
                        subScribeTextView.setTextColor(Color.parseColor("#337FFF"));
                        subScribeTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        resetPic();
                        subScribeImageView.setBackgroundResource(R.drawable.subscribe_1);
                        break;
                    case 2:
                        mineTextView.setTextColor(Color.parseColor("#337FFF"));
                        mineTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        resetPic();
                        mineImageView.setBackgroundResource(R.drawable.mine_1);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int position, float offset, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }

    private class LinearLayoutListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_news:
                   noScrollViewPager.setCurrentItem(0);
                    break;
                case R.id.ll_subscribe:
                    noScrollViewPager.setCurrentItem(1);
                    break;
                case R.id.ll_my:
                    noScrollViewPager.setCurrentItem(2);
                    break;
            }
        }

    }

    private void resetPic() {
        newsImageView.setBackgroundResource(R.drawable.news_grey);
        subScribeImageView.setBackgroundResource(R.drawable.subscribe);
        mineImageView.setBackgroundResource(R.drawable.mine);
    }

    protected void resetView() {
        newsTextView.setTextColor(Color.parseColor("#757575"));
        subScribeTextView.setTextColor(Color.parseColor("#757575"));
        mineTextView.setTextColor(Color.parseColor("#757575"));
    }


}
