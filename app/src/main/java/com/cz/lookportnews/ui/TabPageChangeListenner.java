package com.cz.lookportnews.ui;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cz.lookportnews.R;

import skin.support.SkinCompatManager;
import skin.support.widget.SkinCompatSupportable;

/**
 * Created by Cz on 2018/2/7.
 *  TabViewPage滑动的时候 选中时候 , 字体 , 颜色 改变.
 */

public class TabPageChangeListenner implements ViewPager.OnPageChangeListener ,SkinCompatSupportable {

    private Context context;

    private PagerSlidingTabStrip pagerSlidingTabStrip;

    public TabPageChangeListenner(Context context , PagerSlidingTabStrip pagerSlidingTabStrip){
        this.context=context;
        this.pagerSlidingTabStrip=pagerSlidingTabStrip;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        updateTextStyle(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 设置顶部选中时候的字体样式
     * @param position
     */
    public void updateTextStyle(int position) {
        LinearLayout tabsContainer = (LinearLayout) pagerSlidingTabStrip.getChildAt(0);
        for(int i=0; i< tabsContainer.getChildCount(); i++) {
            TextView textView = (TextView) tabsContainer.getChildAt(i);
            if(position == i) {
                textView.setTextSize(17);
                if("night.skin".equals(SkinCompatManager.getInstance().getCurSkinName())){

                }else {
                    textView.setTextColor(context.getResources().getColor(R.color.colorPrimaryText));
                }

            } else {
                textView.setTextSize(16);

                textView.setTextColor(context.getResources().getColor(R.color.colorSecondText));
            }
        }
    }

    /**
     *  Tab为图片时的 改变图片颜色
     * @param position
     */
    private void updateIconStyle(int position) {
        LinearLayout tabsContainer = (LinearLayout) pagerSlidingTabStrip.getChildAt(0);
        for (int i = 0; i < tabsContainer.getChildCount(); i++) {
            ImageButton imageButton = (ImageButton) tabsContainer.getChildAt(i);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageButton.setImageTintMode(PorterDuff.Mode.SRC_IN);

                if (i == position) {

                    //选中颜色
                    imageButton.setImageTintList(context.getResources().getColorStateList(R.color.ColorHeadColor));

                } else {

                    //没选中颜色
                    imageButton.setImageTintList(context.getResources().getColorStateList(R.color.colorPrimaryText));

                }
            }
        }
    }

    @Override
    public void applySkin() {

    }
}
