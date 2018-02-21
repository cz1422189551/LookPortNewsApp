package com.cz.lookportnews.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.coorchice.library.SuperTextView;
import com.cz.lookportnews.R;
import com.cz.lookportnews.activity.SearchActivity;
import com.cz.lookportnews.util.UIUtils;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Created by 14221 on 2018/2/7.
 */

public class SubScribeFragment extends LazyFragment {

    private static final String TAG = "SubScribeFragment";

    @BindView(R.id.stv_search)
    SuperTextView superTextView;
    @BindView(R.id.tx_head)
    TextView headTextView;
    @BindView(R.id.store_house_ptr_frame)
    PtrClassicFrameLayout storeHousePtrFrame;

    @Override
    protected int getLayout() {
        return R.layout.fragment_subscribe;
    }

    @Override
    protected void initViews(View view) {

        headTextView.setText("推荐订阅");

        superTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
            }
        });
        UIUtils.initPrtClassLayout(storeHousePtrFrame);

//        /**
//         * 经典 风格的头部实现
//         */
        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getActivity());
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

        storeHousePtrFrame.setHeaderView(header);
        storeHousePtrFrame.addPtrUIHandler(header);

        storeHousePtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
        storeHousePtrFrame.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(final PtrFrameLayout frame) {
                storeHousePtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Log.d(TAG, "onLoadMoreBegin : 加载更多");
                        frame.refreshComplete();
                    }
                }, 1000);

            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                storeHousePtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Log.d(TAG, "onRefreshBegin : 加载更多");
                        frame.refreshComplete();
                    }
                }, 1000);
            }

            /**
             * 检查是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时。
             */
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                System.out.println("checkCanDoRefresh");
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                // return true;
            }

        });
    }
}
