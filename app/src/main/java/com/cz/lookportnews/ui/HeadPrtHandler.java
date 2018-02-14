//package com.cz.lookportnews.ui;
//
//import android.content.Context;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.support.v4.app.Fragment;
//import android.support.v4.content.ContextCompat;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.cz.lookportnews.R;
//
//import in.srain.cube.views.ptr.PtrFrameLayout;
//import in.srain.cube.views.ptr.PtrHandler;
//import in.srain.cube.views.ptr.PtrUIHandler;
//import in.srain.cube.views.ptr.indicator.PtrIndicator;
//
//
//
///**
// * Created by 14221 on 2018/2/11.
// */
//
//public class HeadPrtHandler extends FrameLayout implements PtrUIHandler {
//
//    private static final String TAG = "HeadPrtHandler";
//    private ImageView imageView ;
//
//    private TextView textView ;
//
//    PtrFrameLayout mPtrFrameLayout;
//
//    private View view;
//
//    private HeadPushView headPushView;
//
//
//
//
//    public HeadPrtHandler(Context context) {
//        this(context, null, 0);
//    }
//
//    public HeadPrtHandler(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public HeadPrtHandler(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initialize();;
//    }
//
//    /**
//     * 初始化加载布局
//     */
//    private void initialize() {
////        // 加载刚才的
//        view = LayoutInflater.from(getContext()).inflate(R.layout.head_push, this, false);
//        addView(view);
//        imageView = (ImageView) view.findViewById(R.id.iv_square);
//        textView = view.findViewById(R.id.tv_push);
//        headPushView=view.findViewById(R.id.head_push_view);
//
//    }
//
//
//    /**
//     *  重置头动画，一般停止刷新动画
//     * @param frame
//     */
//    @Override
//    public void onUIReset(PtrFrameLayout frame) {
//
//    }
//
//    /**
//     * 准备刷新动画
//     * @param frame
//     */
//    @Override
//    public void onUIRefreshPrepare(PtrFrameLayout frame) {
//        headPushView.setVisibility(View.VISIBLE);
//        textView.setVisibility(View.GONE);
//    }
//
//    /**
//     *  开始刷新动画
//     * @param frame
//     */
//    @Override
//    public void onUIRefreshBegin(PtrFrameLayout frame) {
//
//    }
//
//    /**
//     *  刷新完成 ,停止动画
//     * @param frame
//     */
//    @Override
//    public void onUIRefreshComplete(PtrFrameLayout frame) {
//        textView.setVisibility(View.VISIBLE);
//        headPushView.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//        // 手指下拉的时候的状态，我们的下拉动画的控制就是通过这个方法：
//
//        final int mOffsetToRefresh = frame.getOffsetToRefresh();
//        Log.d(TAG, "onUIPositionChange: " +mOffsetToRefresh);
//        final int currentPos = ptrIndicator.getCurrentPosY();
//        final int lastPos = ptrIndicator.getLastPosY();
//
//
//        if (currentPos < mOffsetToRefresh) {
//            //未到达刷新线
//            if (status == PtrFrameLayout.PTR_STATUS_PREPARE) {
//                Log.d(TAG, "onUIPositionChange: ");
//                if(currentPos>=66) {
//                    headPushView.setPos((currentPos-66));
//                    headPushView.invalidate();
//                }
//
//
//            }
//        } else if (currentPos > mOffsetToRefresh) {
//            //到达或超过刷新线
//            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
////                tvLoading.setText("释放刷新");
//            }
//        }
//    }
//    //
//    public void setHeadColor(int color){
//        view.setBackgroundColor(color);
//    }
//
//    // frame是刷新的root layout。
//        // isUnderTouch是手指是否按下，因为还有自动刷新，手指肯定是松开状态。
//        // status是现在的加载状态，准备、加载中、完成：PREPARE、LOADING、COMPLETE。
//        // ptrIndicator是一些下拉偏移量的参数封装。
//
//    }
//
