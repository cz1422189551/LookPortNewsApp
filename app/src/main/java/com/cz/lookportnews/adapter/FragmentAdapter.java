package com.cz.lookportnews.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;

import com.cz.lookportnews.R;

import java.util.List;

/**
 * Created by hsp on 2016/11/27.
 */

public class FragmentAdapter extends FragmentPagerAdapter {


    List<Fragment> mlist;

    List<String> titles;

    Context context;



    public FragmentAdapter(Context context,List<Fragment> mlist,List<String> titles, FragmentManager fm) {
        super(fm);
        this.mlist =mlist;
        this.titles=titles;
        this.context=context;
    }


    public FragmentAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position);


    }
}
