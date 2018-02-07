package com.cz.lookportnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cz.lookportnews.R;

/**
 * Created by 14221 on 2018/2/7.
 */

public class OneFragment extends LazyFragment {

    private TextView textView;

    @Override
    protected int getLayout() {
        return R.layout.fragment1;
    }

    @Override
    protected void initViews(View view) {
        textView = view.findViewById(R.id.tv_1);
    }
}
