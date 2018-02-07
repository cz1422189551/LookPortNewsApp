package com.cz.lookportnews.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.cz.lookportnews.R;
import com.cz.lookportnews.adapter.FragmentAdapter;
import com.cz.lookportnews.adapter.MyPagerAdapter;
import com.cz.lookportnews.fragment.OneFragment;
import com.cz.lookportnews.fragment.ThreeFragment;
import com.cz.lookportnews.fragment.TwoFragment;
import com.cz.lookportnews.ui.PagerSlidingTabStrip;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by 14221 on 2018/2/7.
 */

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private ViewPager viewPager ;

    private FragmentAdapter fragmentAdapter;

    private PagerSlidingTabStrip pagerSlidingTabStrip;

    private OneFragment oneFragment;

    private TwoFragment twoFragment;

    private ThreeFragment threeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        pagerSlidingTabStrip = (PagerSlidingTabStrip)findViewById(R.id.pager_sliding_tab_strip);
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        ThreeFragment threeFragment2 = new ThreeFragment();

        ThreeFragment threeFragment4 = new ThreeFragment();
        ThreeFragment  threeFragment5 = new ThreeFragment();
        ThreeFragment threeFragment6 = new ThreeFragment();
        List<Fragment>  fragmentList = new ArrayList<>();
        fragmentList.add(oneFragment);
        fragmentList.add(twoFragment);
        fragmentList.add(threeFragment);
        fragmentList.add(threeFragment2);
        fragmentList.add(threeFragment4);
        fragmentList.add(threeFragment5);
        fragmentList.add(threeFragment6);



       List<String> titles = new ArrayList<>();
       titles.add("体育");
        titles.add("国内");
       titles.add("推荐");
       titles.add("社会");
       titles.add("娱乐");
       titles.add("汽车");
       titles.add("科技");
        fragmentAdapter = new FragmentAdapter(this,fragmentList,titles,getSupportFragmentManager());

        viewPager.setAdapter(fragmentAdapter);

        Log.d(TAG, "initView: "+pagerSlidingTabStrip.toString());

        pagerSlidingTabStrip.setViewPager(viewPager);
        pagerSlidingTabStrip.setShouldExpand(true);
        pagerSlidingTabStrip.setDividerColor(Color.WHITE);
        pagerSlidingTabStrip.setIndicatorHeight(5);
        pagerSlidingTabStrip.setUnderlineHeight(0);
        pagerSlidingTabStrip.setIndicatorColorResource(R.color.ColorPrimaryColor);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateTextStyle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        updateTextStyle(viewPager.getCurrentItem());
    }

    /**
     * 设置顶部选中时候的字体样式
     * @param position
     */
    private void updateTextStyle(int position) {
        LinearLayout tabsContainer = (LinearLayout) pagerSlidingTabStrip.getChildAt(0);
        for(int i=0; i< tabsContainer.getChildCount(); i++) {
            TextView textView = (TextView) tabsContainer.getChildAt(i);
            if(position == i) {
                textView.setTextSize(17);
                textView.setTextColor(getResources().getColor(R.color.ColorPrimaryText));
            } else {
                textView.setTextSize(16);
                textView.setTextColor(getResources().getColor(R.color.ColorSecondaryText));
            }
        }
    }



}
