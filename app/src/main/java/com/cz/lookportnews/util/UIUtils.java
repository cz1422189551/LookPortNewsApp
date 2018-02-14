package com.cz.lookportnews.util;

import android.text.TextPaint;
import android.widget.TextView;

import org.w3c.dom.Text;

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
}
