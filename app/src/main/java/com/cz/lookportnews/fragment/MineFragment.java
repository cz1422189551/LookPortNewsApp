package com.cz.lookportnews.fragment;

import android.view.View;
import android.widget.TextView;

import com.cz.lookportnews.R;

/**
 * Created by 14221 on 2018/2/7.
 */

public class MineFragment extends LazyFragment {

    private TextView textView;

    @Override
    protected int getLayout() {
        return R.layout.frg_my;
    }

    @Override
    protected void initViews(View view) {
        textView = view.findViewById(R.id.tv_1);
    }
}