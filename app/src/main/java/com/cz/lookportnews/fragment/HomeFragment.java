package com.cz.lookportnews.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.cz.lookportnews.R;
import com.cz.lookportnews.activity.BasActivity;
import com.cz.lookportnews.activity.ChannelActivity;
import com.cz.lookportnews.adapter.FragmentAdapter;
import com.cz.lookportnews.ui.PagerSlidingTabStrip;
import com.cz.lookportnews.ui.TabPageChangeListenner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 14221 on 2018/2/7.
 */


public class HomeFragment extends LazyFragment {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.view_pager)
     ViewPager tabViewPager ;

    @BindView(R.id.pager_sliding_tab_strip)
     PagerSlidingTabStrip pagerSlidingTabStrip;

     FragmentAdapter tabFragmentAdapter;


    private TabPageChangeListenner tabPageChangeListenner;

    private OneFragment oneFragment;

    private TwoFragment twoFragment;

    private ThreeFragment threeFragment;

    @BindView(R.id.ib_more)
     ImageButton imageButton ;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view) {

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

        //FragmentAdapter的初始化
        tabFragmentAdapter = new FragmentAdapter(getActivity(),fragmentList,titles,getActivity().getSupportFragmentManager());

        //TabViewPager注入适配器
        tabViewPager.setAdapter(tabFragmentAdapter);

        //Tab控件的属性的初始化
        pagerSlidingTabStrip.setViewPager(tabViewPager);
        pagerSlidingTabStrip.setShouldExpand(false);
        pagerSlidingTabStrip.setDividerColor(Color.WHITE);
        pagerSlidingTabStrip.setIndicatorHeight(5);
        pagerSlidingTabStrip.setUnderlineHeight(0);
        pagerSlidingTabStrip.setIndicatorColorResource(R.color.ColorPrimaryColor);

        //TabViewPager滑动监听器实现初始化,被选中的Tab字体将样式被改变.
        tabPageChangeListenner  = new TabPageChangeListenner(getActivity(),pagerSlidingTabStrip);
        tabViewPager.addOnPageChangeListener(tabPageChangeListenner);
        tabPageChangeListenner.updateTextStyle(tabViewPager.getCurrentItem());
    }

    @OnClick(R.id.ib_more)
    public void onClick (){
        BasActivity.startToActivity(getActivity(), ChannelActivity.class);
    }
}
