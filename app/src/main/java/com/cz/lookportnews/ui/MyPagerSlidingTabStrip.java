//package com.cz.lookportnews.ui;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.util.AttributeSet;
//
//import com.cz.lookportnews.R;
//
//import skin.support.widget.SkinCompatImageHelper;
//import skin.support.widget.SkinCompatSupportable;
//
//import static skin.support.widget.SkinCompatHelper.INVALID_ID;
//
///**
// * Created by 14221 on 2018/3/3.
// */
//
//public class MyPagerSlidingTabStrip extends  PagerSlidingTabStrip implements SkinCompatSupportable {
//
//
//    private int mFillColorResId = INVALID_ID;
//
//    private int mBorderColorResId = INVALID_ID;
//
//    private SkinCompatImageHelper mImageHelper;
//
//
//    public MyPagerSlidingTabStrip(Context context) {
//        super(context);
//    }
//
//    public MyPagerSlidingTabStrip(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public MyPagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//
//
//
//        mImageHelper = new SkinCompatImageHelper(this);
//
//        mImageHelper.loadFromAttributes(attrs, defStyle);
//
//
//
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);
//
//        mBorderColorResId = a.getResourceId(R.styleable.CircleImageView_civ_border_color, INVALID_ID);
//
//        mFillColorResId = a.getResourceId(R.styleable.CircleImageView_civ_fill_color, INVALID_ID);
//
//        a.recycle();
//
//        applyBorderColorResource();
//
//        applyFillColorResource();
//
//    }
//
//
//
//
//
//    @Override
//    public void applySkin() {
//
//    }
//}
