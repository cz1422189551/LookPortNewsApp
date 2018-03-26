package com.cz.lookportnews.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.classic.adapter.CommonRecyclerAdapter;
import com.cz.lookportnews.R;
import com.cz.lookportnews.activity.BasActivity;
import com.cz.lookportnews.activity.DetailActivity;
import com.cz.lookportnews.adapter.MultipleLayoutAadapter;
import com.cz.lookportnews.entity.Channel;
import com.cz.lookportnews.entity.News;
import com.cz.lookportnews.util.Constant;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.finalteam.loadingviewfinal.HeaderAndFooterRecyclerViewAdapter;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;


/**
 * Created by 14221 on 2018/2/7.
 */

@SuppressLint("ValidFragment")
public class StandFragment extends LazyFragment {

    private static final String TAG = "OneFragment";

    @BindView(R.id.store_house_ptr_frame)
    PtrClassicFrameLayout mPtrLayout;
    @BindView(R.id.rv_sports)
    RecyclerViewFinal recycleView;
    MultipleLayoutAadapter adapter;

    List<News> newsList;

    @SuppressLint("ValidFragment")
    public StandFragment(List<News> newsList) {
        this.newsList = newsList ;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_sports;
    }

    @Override
    protected void initViews(View view) {
        loadData();
    }

    private void loadData() {

        mPtrLayout.disableWhenHorizontalMove(true);
        mPtrLayout.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //发起下拉刷新请求
                //   requestData(1);
                newsList.removeAll(newsList);
                recycleView.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "onRefreshBegin: 刷新");
                        Log.d(TAG, "onRefreshBegin: " + newsList.size());

                        adapter.notifyDataSetChanged();
                        mPtrLayout.onRefreshComplete();
                    }
                });
            }
        });

        recycleView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                //发起加载更多请求
                //requestData(mPage);
                Log.d(TAG, "loadMore: 加载更多");
            }
        });
        recycleView.setHasLoadMore(true);

        for (News news : newsList) {
            Log.d(TAG, "toString: "+news.toString());
            news.setNewsShowType(News.ONE_PHOTO);
        }

        adapter = new MultipleLayoutAadapter(getActivity(), R.layout.base_item, newsList);
        adapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
                Log.d(TAG, "onItemClick: " + position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("news",   adapter.getItem(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });

        Constant.addAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setHasFixedSize(true);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(adapter);
    }

    private void testData(){
        //        News news4 = new News();
//        news4.setTitle("巴黎银行赛张帅1-2无缘16强 遇普娃已遭七连败");
//        news4.setImgUrl("http://cms-bucket.nosdn.127.net/catchpic/8/8c/8cdbfca032d7d2436f0712676c581170.jpg?imageView&thumbnail=550x0");
//        news4.setNewsShowType(News.ONE_PHOTO);
//
//        News news5 = new News();
//        news5.setTitle("郎平:家庭事业难平衡 李盈莹来国家队进步空间大");
//        news5.setImgUrl("http://pic-bucket.nosdn.127.net/photo/0005/2017-10-27/D1PHEEVI0AP80005NOS.jpg?imageView&thumbnail=550x0");
//        news5.setNewsShowType(News.ONE_PHOTO);
//
//        News news7 = new News();
//        news7.setTitle("杜兰特喷网友:别来教育我怎么打球!你们都是垃圾");
//        news7.setImgUrl("http://cms-bucket.nosdn.127.net/d0479bcd9330475ca45fab2c1e572ae020180313080505.png?imageView&thumbnail=550x0");
//        news7.setNewsShowType(News.ONE_PHOTO);
//
//        News news8 = new News();
//        news8.setTitle("父亲荷兰名宿,母亲为成人杂志拍性感写真,他凭什么撑起荷兰黄金一代?");
//        news8.setImgUrl("http://cms-bucket.nosdn.127.net/506453baca804bd0b464c884622411eb20180310104021.jpeg?imageView&thumbnail=550x0;" +
//                "http://cms-bucket.nosdn.127.net/21ebb79cc9d3411ab0310c3aae5c3c3f20180310072706.jpeg?imageView&thumbnail=550x0;" +
//                "http://cms-bucket.nosdn.127.net/catchpic/0/06/065af44c47f752ddb79e8fc5373568c1.jpg?imageView&thumbnail=550x0;");
//        news8.setNewsShowType(News.MORE_PHOTO);
//
//        News news12 = new News();
//        news12.setTitle("父亲荷兰名宿,母亲为成人杂志拍性感写真,他凭什么撑起荷兰黄金一代?");
//        news12.setImgUrl("http://cms-bucket.nosdn.127.net/506453baca804bd0b464c884622411eb20180310104021.jpeg?imageView&thumbnail=550x0;" +
//                "http://cms-bucket.nosdn.127.net/21ebb79cc9d3411ab0310c3aae5c3c3f20180310072706.jpeg?imageView&thumbnail=550x0;" +
//                "http://cms-bucket.nosdn.127.net/catchpic/0/06/065af44c47f752ddb79e8fc5373568c1.jpg?imageView&thumbnail=550x0;");
//        news12.setNewsShowType(News.MORE_PHOTO);
//
//        News news9 = new News();
//        news9.setTitle("哈登:波波对我们无可奈何 接下来每场都是季后赛");
//        news9.setImgUrl("http://pic-bucket.nosdn.127.net/photo/0005/2018-03-13/DCP4C1BK4TM10005NOS.jpg?imageView&thumbnail=550x0");
//        news9.setNewsShowType(News.ONE_PHOTO);
//
//
//        News news10 = new News();
//        news10.setTitle("首节领先22分翻车能算强队? 骑士防守只能撑一节");
//        news10.setImgUrl("http://pic-bucket.nosdn.127.net/photo/0005/2018-01-13/D81589QM4TM10005NOS.jpg?imageView&thumbnail=550x0");
//        news10.setNewsShowType(News.ONE_PHOTO);
//
//        News news11 = new News();
//        news11.setTitle("哈登14罚全中诠释造犯规之王 三节打卡取28+6+6");
//        news11.setImgUrl("http://pic-bucket.nosdn.127.net/photo/0005/2018-03-13/DCP4C1BQ4TM10005NOS.jpg?imageView&thumbnail=550x0");
//        news11.setNewsShowType(News.ONE_PHOTO);
//        newsList.add(news4);
//        newsList.add(news5);
//        newsList.add(news9);
//        newsList.add(news12);
//        newsList.add(news7);
//        newsList.add(news10);
//        newsList.add(news11);
//        newsList.add(news8);
    }


}
