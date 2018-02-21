package com.cz.lookportnews.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.classic.adapter.CommonRecyclerAdapter;
import com.cz.lookportnews.R;
import com.cz.lookportnews.activity.BasActivity;
import com.cz.lookportnews.activity.DetailActivity;
import com.cz.lookportnews.adapter.MultipleLayoutAadapter;
import com.cz.lookportnews.entity.News;
import com.cz.lookportnews.util.UIUtils;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Created by 14221 on 2018/2/7.
 */

public class OneFragment extends LazyFragment {

    private static final String TAG = "OneFragment";

    @BindView(R.id.store_house_ptr_frame)
    PtrClassicFrameLayout storeHousePtrFrame;
    @BindView(R.id.rv_sports)
    RecyclerView recycleView;
    MultipleLayoutAadapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sports;
    }

    @Override
    protected void initViews(View view) {
        loadData();
    }

    private void loadData(){

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
        List<News>  newsList = new ArrayList<>();
        News news = new News();
        news.setTitle("欧文半场无解半场萎靡 现在或不再想念他");
        news.setImgUrl("abc");
        news.setNewsShowType(News.ONE_PHOTO);

        News news2 = new News();
        news2.setTitle("詹姆斯开心疯了！新引援惊艳全场！欧文全场懵逼，皮尔斯全程捂脸");
        news2.setImgUrl("app;app;app;");
        news2.setNewsShowType(News.MORE_PHOTO);


        newsList.add(news );
        newsList.add(news2);
        Log.d(TAG, "loadData: "+recycleView);
        adapter = new MultipleLayoutAadapter(getActivity(),R.layout.base_item,newsList);
        adapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
                Log.d(TAG, "onItemClick: " +position);
                BasActivity.startToActivity(getActivity(), DetailActivity.class);
            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setHasFixedSize(true);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(adapter);
    }



}
