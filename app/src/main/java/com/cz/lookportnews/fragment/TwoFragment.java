package com.cz.lookportnews.fragment;

import android.view.View;
import android.widget.TextView;

import com.coorchice.library.SuperTextView;
import com.cz.lookportnews.R;

/**
 * Created by 14221 on 2018/2/7.
 */

public class TwoFragment extends LazyFragment {

    private SuperTextView superTextView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_subscribe;
    }

    @Override
    protected void initViews(View view) {
//        superTextView = view.findViewById(R.id.tv_1);
    }
}
