package com.cz.lookportnews.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.cz.lookportnews.entity.Channel;
import com.cz.lookportnews.fragment.StandFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsp on 2016/11/27.
 */

public class FragmentAdapter extends FragmentPagerAdapter {


    List<Fragment> fragmentList = new ArrayList<>();

    List<String> titles = new ArrayList<>();

    List<Channel> channelList;


    Context context;


    public FragmentAdapter(Context context, List<Fragment> fragmentList, List<String> titles, FragmentManager fm) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
        this.context = context;
    }

    public FragmentAdapter(Context context, List<Channel> list, FragmentManager fm) {
        super(fm);
        this.context = context;
        channelList = list;
        initFragment();
    }


    public FragmentAdapter(Context context, List<Fragment> fragmentList, boolean activity, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;

    }

    private void initFragment() {
        for (Channel channel : channelList) {
            titles.add(channel.getName());
            StandFragment standFragment = new StandFragment(channel.getNewsList());

            fragmentList.add(standFragment);
        }
    }


    public FragmentAdapter(FragmentManager fm) {
        super(fm);

    }

    /**
     * 是为了在viewpager中，因为下一界面有类似于新闻类的标签功能，
     * 根据选择的标签，返回本页以后，动态替换viewpager中的fragment，
     * 解决notifyDataSetChanged（）不刷新的问题
     *
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (titles == null || titles.size() < 1) {
            return " ";
        }
        return titles.get(position);

    }
}
