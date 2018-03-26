package com.cz.lookportnews.fragment;

import android.content.Context;
import android.view.View;

import com.cz.lookportnews.activity.MyApplication;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by 14221 on 2018/2/11.
 */

public  class BaseFragment extends  LazyFragment {




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }



    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initViews(View view) {

    }


}
