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


public class HomeActivity extends BasActivity {

    private static final String TAG = "HomeActivity";

   static  SweetAlertDialog sweetAlertDialog;

    public static WebView myWebView;

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
    NoScrollViewPager noScrollViewPager;

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

    SharedPreferences sharedPreferences = null;

    public static FontPopupWindow fontPopupWindow;

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

        sharedPreferences = this.getSharedPreferences("TextSize", Context.MODE_PRIVATE);
        sweetAlertDialog=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.setTitle("正在加载");
        sweetAlertDialog.setCancelable(false);
        View view = LayoutInflater.from(this).inflate(R.layout.detail_head, null);
        myWebView = (WebView) view.findViewById(R.id.wv_detail);
        Log.d(TAG, "initViews: " + Util.getSharedPreferences(this, "textSize").getInt("fontSize", 1));
        Constant.TEXT_SIZE = sharedPreferences.getInt("fontSize", 1);
        fontPopupWindow = new FontPopupWindow(this, getScreenWidth(this), getScreenHeight(this) * 2 / 10);
        //设置底部Tab字体中文加粗
        UIUtils.setChineseTextViewBold(newsTextView, subScribeTextView, mineTextView);

        //初始化切换Bottom的Tab的点击事件
        LinearLayoutListener linearLayoutListener = new LinearLayoutListener();
        newsLinearLayout.setOnClickListener(linearLayoutListener);
        subScribeLinearLayout.setOnClickListener(linearLayoutListener);
        mineLinearLayout.setOnClickListener(linearLayoutListener);


        //bottomViewPager
        homeFragment = new HomeFragment();
        subScribeFragment = new SubScribeFragment();
        mineFragment = new MineFragment();
        //初始化首页三个Fragment
        List<Fragment> bottomFragment = new ArrayList<>();
        bottomFragment.add(homeFragment);
        bottomFragment.add(subScribeFragment);
        bottomFragment.add(mineFragment);
        //创建Fragment适配器
        fragmentAdapter = new FragmentAdapter(this, bottomFragment,true,getSupportFragmentManager());
        //将三个Fragment加载至Activity并且可点击下方Tab按钮左右切换。
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

    @Override
    public void loadData() {

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

    public static void showDialog(){
        sweetAlertDialog.show();
    }

    public static void showErrorDialog(){
        sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog.setTitle("加载错误");
        sweetAlertDialog.show();;
    }

    public static void closeDialog(){
        if(sweetAlertDialog!=null&&sweetAlertDialog.isShowing()){
            sweetAlertDialog.dismiss();
        }
    }
}
