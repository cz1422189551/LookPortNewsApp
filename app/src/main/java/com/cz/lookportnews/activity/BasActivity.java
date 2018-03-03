package com.cz.lookportnews.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cz.lookportnews.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import skin.support.annotation.Skinable;
import skin.support.app.SkinActivityLifecycle;
import skin.support.app.SkinCompatActivity;

//package com.cz.lookportnews.activity;
//
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//
//import com.bigkoo.svprogresshud.SVProgressHUD;
//
//import medusa.theone.waterdroplistview.view.WaterDropListView;
//import model.Response;
//import model.ResultBean;
//
///**
// * Created by cz on 2018/2/20.
// */
//
@Skinable
public abstract  class BasActivity extends AppCompatActivity{


    public static  int states = 1 ;

    private Unbinder unbinder ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (1 == states){
//            setTheme(R.style.Default_TextSize_Small);
//        } else if ( 2== states) {
//            setTheme(R.style.Default_TextSize_Medium);
//        } else if( 3 ==states) {
//            setTheme(R.style.Default_TextSize_Large);
//        }

        loadActivity();

    }

    private  void loadActivity(){
        setContentView(getLayout());
        ButterKnife.bind(getActivity());
        initViews();
    }
    public abstract int getLayout();

    public abstract AppCompatActivity getActivity();

    public abstract void initViews();

    public abstract void loadData();

    public static void  startToActivity(Activity activity, Class intentActivity){
        Intent intent = new Intent();
        intent.setClass(activity,intentActivity);
        activity.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if(unbinder!=null) {
            Log.d(this.getActivity().getClass().getSimpleName(), "onDestroy: 解绑");
            unbinder.unbind();
        }
        super.onDestroy();
    }


    /**
     *携带数据的页面跳转
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

}


