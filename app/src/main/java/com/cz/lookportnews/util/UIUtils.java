package com.cz.lookportnews.util;

import android.text.TextPaint;
import android.widget.TextView;

import org.w3c.dom.Text;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by 14221 on 2018/2/11.
 */

public class UIUtils {

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

    /**
     * 初始化
     * @param storeHousePtrFrame
     */
    public static void initPrtClassLayout (PtrClassicFrameLayout storeHousePtrFrame ) {
        storeHousePtrFrame.setResistance(1.7f);
        storeHousePtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        storeHousePtrFrame.setDurationToClose(200);
        storeHousePtrFrame.setDurationToCloseHeader(2000);
        storeHousePtrFrame.setDurationToCloseFooter(2000);
        storeHousePtrFrame.setPullToRefresh(false);
        storeHousePtrFrame.setKeepHeaderWhenRefresh(true);
    }
}
