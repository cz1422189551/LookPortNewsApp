package com.cz.lookportnews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hsp on 2016/11/19.
 */

public class MyPagerAdapter extends PagerAdapter {

    private List<View> viewList;
    private List<String> titleList;

    public MyPagerAdapter(List<View> viewList, List<String> titleList){
        this.viewList = viewList;
        this.titleList = titleList;
    }
    public MyPagerAdapter(){

    }


      //返回页卡的数量
    @Override
    public int getCount() {
        return viewList.size();
    }


    //View是否来自于对象
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    //实例化一个页卡
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    //销毁一个页卡
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(viewList.get(position));
    }

    //设置ViewPager的标题


    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
