package com.cz.lookportnews.util;

import android.content.Context;
import android.content.SharedPreferences;

import skin.support.SkinCompatManager;

/**
 * Created by 14221 on 2018/3/3.
 */

public class Util {


    public static String processStr(String temp) {
        return temp.substring(1,temp.length()-1).replace("</p>，", "</p>")
                .replace("</p>,", "</p>");

    }

    public static SharedPreferences sharedPreferences = null;

    public static SharedPreferences.Editor editor = null;

    public static SharedPreferences.Editor getSharedPreEditor(Context mContext, String shareName) {
        if (sharedPreferences != null) {
            if (editor != null) {
                return editor;
            } else {
                editor = sharedPreferences.edit();
            }

        } else {
            sharedPreferences = mContext.getSharedPreferences(shareName, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return editor;
    }

    public static SharedPreferences getSharedPreferences(Context mContext, String shareName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(shareName, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static boolean isNightTheme() {
        if ("night.skin".equals(SkinCompatManager.getInstance().getCurSkinName())) {
            return true;
        } else {
            return false;
        }

    }


}
