package com.cz.lookportnews.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cz.lookportnews.ui.FontPopupWindow;
import com.cz.lookportnews.ui.MyPopupWindow;

import org.w3c.dom.Text;

import java.lang.reflect.Method;



/**
 * Created by 14221 on 2018/2/11.
 */

public class UIUtils {

    private static int stheme;

    public static final int THEME_DEFAULT=0;

    public static final int THEME_BLACK=1;

    public static void changeToTheme(Activity activity ,int theme){
        stheme  = theme ;
        activity.finish();
        activity.startActivity(new Intent(activity,activity.getClass()));
    }

    /**
     *  为TextView 中文字体加粗
     * @param textViews
     */
    public static void setChineseTextViewBold(TextView... textViews){
        for (TextView tx  : textViews){
            TextPaint tp = tx.getPaint();
            tp.setFakeBoldText(true);
        }
    }

//    /**
//     * 初始化
//     * @param storeHousePtrFrame
//     */
//    public static void initPrtClassLayout (PtrClassicFrameLayout storeHousePtrFrame ) {
//        storeHousePtrFrame.setResistance(1.7f);
//        storeHousePtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
//        storeHousePtrFrame.setDurationToClose(200);
//        storeHousePtrFrame.setDurationToCloseHeader(2000);
//        storeHousePtrFrame.setDurationToCloseFooter(2000);
//        storeHousePtrFrame.setPullToRefresh(false);
//        storeHousePtrFrame.setKeepHeaderWhenRefresh(true);
//    }
//
//    //        /**
////         * 经典 风格的头部实现
////         */
//    public static void setPrtClassLayoutHeadView(PtrClassicFrameLayout storeHousePtrFrame ,  Context context){
//        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(context);
//        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);
//        storeHousePtrFrame.setHeaderView(header);
//        storeHousePtrFrame.addPtrUIHandler(header);
//        storeHousePtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
//    }

    public static void showPopWindow(Context context , MyPopupWindow popupWindow , View view , LinearLayout linearLayout , TextView copyString){

        if (checkDeviceHasNavigationBar2(context )) {
            int heigth_tobottom = getNavigationBarHeight(context)-100;
            linearLayout.setVisibility(View.GONE);
            popupWindow.setCopyString(copyString);
            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, heigth_tobottom);
        } else {
            popupWindow.setCopyString(copyString);
            linearLayout.setVisibility(View.GONE);
            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 100);
        }
    }

    public static void showPopWindow(Context context , PopupWindow popupWindow , View view ){

        if (checkDeviceHasNavigationBar2(context )) {
            int heigth_tobottom = getNavigationBarHeight(context)-118;
            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, heigth_tobottom);
        } else {

            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 100);
        }
    }


    /**
     * 判断设备是否有虚拟按键（navifationbar）。第一种方法
     */
    public static boolean checkDeviceHasNavigationBar(Context activity) {
        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar

        boolean hasMenuKey = ViewConfiguration.get(activity).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;

    }

    /**
     * /获取是否存在虚拟按键 NavigationBar：如果是有就返回true,如果是没有就是返回的false。第二种方法
     */
    private static boolean checkDeviceHasNavigationBar2(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;


    }

    private static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier(
                "navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;


    }


}
