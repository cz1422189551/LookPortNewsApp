package com.cz.lookportnews.activity;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.app.SkinCompatActivity;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Created by 14221 on 2018/3/2.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinCompatManager.withoutActivity(this)

                .addInflater(new SkinMaterialViewInflater())    // material design

                .addInflater(new SkinConstraintViewInflater())  // ConstraintLayout

                .addInflater(new SkinCardViewInflater())// CardView v7
               .loadSkin();

//                .setSkinStatusBarColorEnable(false)             // 关闭状态栏换肤

//                .setSkinWindowBackgroundEnable(false)           // 关闭windowBackground换肤

//               .setSkinAllActivityEnable(true)                // true: 默认所有的Activity都换肤; false: 只有实现SkinCompatSupportable接口的Activity换肤

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
