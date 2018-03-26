package com.cz.lookportnews.fragment;


import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.cz.lookportnews.R;
import com.cz.lookportnews.activity.BasActivity;
import com.cz.lookportnews.activity.ChannelActivity;
import com.cz.lookportnews.activity.HomeActivity;
import com.cz.lookportnews.adapter.FragmentAdapter;
import com.cz.lookportnews.entity.Channel;
import com.cz.lookportnews.entity.News;
import com.cz.lookportnews.entity.Result;
import com.cz.lookportnews.ui.PagerSlidingTabStrip;
import com.cz.lookportnews.ui.TabPageChangeListenner;
import com.cz.lookportnews.util.Constant;
import com.cz.lookportnews.util.JsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 14221 on 2018/2/7.
 */


public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.view_pager)
    ViewPager tabViewPager;

    @BindView(R.id.pager_sliding_tab_strip)
    PagerSlidingTabStrip pagerSlidingTabStrip;

    //频道标题
    List<String> titles = new ArrayList<>();

    //频道标题
    List<Channel> channelList = new ArrayList<>();

    FragmentAdapter tabFragmentAdapter;

    private TabPageChangeListenner tabPageChangeListenner;

    private StandFragment oneFragment;

    private TwoFragment twoFragment;

    private ThreeFragment threeFragment;

//    private Handler mhandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 2:
//                    tabFragmentAdapter.notifyDataSetChanged();
//                    tabViewPager.setAdapter(tabFragmentAdapter);
//                    break;
//            }
//        }
//    };

    @BindView(R.id.ib_more)
    ImageButton imageButton;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view) {
        HomeActivity.showDialog();
        loadHomeData();
    }

    private void loadHomeData() {
        String urlStr = Constant.BASEURL + "newsApp/home";
        OkHttpUtils.get()
                .url(urlStr)
                .tag(this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        HomeActivity.showErrorDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        HomeActivity.closeDialog();
                        if (!TextUtils.isEmpty(response)) {
                            Result<Channel> result = null;
                            result = (Result) JsonUtil.fromJsonObject(response, Channel.class);
                            channelList = result.getData();
                            if(channelList!=null&&channelList.size()>0){
                                initContent(channelList);
                            }
                        }
                    }
                });
    }

    //初始化 首页内容
    private void initContent(List<Channel> list) {

//        twoFragment = new TwoFragment();
//        threeFragment = new ThreeFragment();
//        ThreeFragment threeFragment2 = new ThreeFragment();
//        ThreeFragment threeFragment4 = new ThreeFragment();
//        ThreeFragment threeFragment5 = new ThreeFragment();
//        ThreeFragment threeFragment6 = new ThreeFragment();
//
//        List<Fragment> fragmentList = new ArrayList<>();
//        fragmentList.add(oneFragment);
//        fragmentList.add(twoFragment);
//        fragmentList.add(threeFragment);
//        fragmentList.add(threeFragment2);
//        fragmentList.add(threeFragment4);


        //FragmentAdapter的初始化
        tabFragmentAdapter = new FragmentAdapter(getActivity(),list , getActivity().getSupportFragmentManager());
        //TabViewPager注入适配器
        tabViewPager.setAdapter(tabFragmentAdapter);
        //Tab控件的属性的初始化
        pagerSlidingTabStrip.setViewPager(tabViewPager);
        pagerSlidingTabStrip.setShouldExpand(false);
        //    pagerSlidingTabStrip.setDividerColorResource(R.color.colorPrimary);
        pagerSlidingTabStrip.setIndicatorHeight(5);
        pagerSlidingTabStrip.setUnderlineHeight(0);
        pagerSlidingTabStrip.setIndicatorColorResource(R.color.ColorHeadColor);
        //TabViewPager滑动监听器实现初始化,被选中的Tab字体将样式被改变.
        tabPageChangeListenner = new TabPageChangeListenner(getActivity(), pagerSlidingTabStrip);
        tabViewPager.addOnPageChangeListener(tabPageChangeListenner);
        tabPageChangeListenner.updateTextStyle(tabViewPager.getCurrentItem());
    }

    @OnClick(R.id.ib_more)
    public void onClick() {
        BasActivity.startToActivity(getActivity(), ChannelActivity.class);
    }
}
