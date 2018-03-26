package com.cz.lookportnews.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.coorchice.library.SuperTextView;
import com.cz.lookportnews.R;
import com.cz.lookportnews.activity.SearchActivity;
import com.cz.lookportnews.adapter.SubsribeAdapter;
import com.cz.lookportnews.entity.SubsribeSource;
import com.cz.lookportnews.util.UIUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;


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
    @BindView(R.id.lv_subsribe)
    ListView listView ;

    SubsribeAdapter adapter ;

    @Override
    protected int getLayout() {
        return R.layout.fragment_subscribe;
    }

    @Override
    protected void initViews(View view) {

        headTextView.setText("推荐订阅");
        List<SubsribeSource>  list  = new ArrayList<>();
        SubsribeSource source = new SubsribeSource();
        source.setName("快科技");
        source.setDescribe("11存iPad Pro意外杀出：屏幕......");

        SubsribeSource source2 = new SubsribeSource();
        source2.setName("柳叶刀");
        source2.setDescribe("李嘉诚的财富与普通人的财富有.....");

        SubsribeSource source3 = new SubsribeSource();
        source3.setName("金满楼");
        source3.setDescribe("李鸿章出使美国时所住的豪宅，在中国...");

        SubsribeSource source4 = new SubsribeSource();
        source4.setName("皮海洲");
        source4.setDescribe("“最严IPO新规”可以更严的...");

        SubsribeSource source5 = new SubsribeSource();
        source5.setName("寻找中国创客");
        source5.setDescribe("美国打车北京站上线；...");

        SubsribeSource source6 = new SubsribeSource();
        source6.setName("节目一线");
        source6.setDescribe("古装剧“网”中求生变独显...");

        SubsribeSource source7 = new SubsribeSource();
        source7.setName("1905电影网");
        source7.setDescribe("《爱情公寓2019》正式立项 电影版关谷神奇...");

        SubsribeSource source8 = new SubsribeSource();
        source8.setName("体坛一只眼");
        source8.setDescribe("过分！恒大训练遭突然关灯+球员抹黑离场...");

        SubsribeSource source9 = new SubsribeSource();
        source9.setName("军武次位面");
        source9.setDescribe("《军武次位面》是知名的网络军事类视频节目...");


        list.add(source);
        list.add(source2);
        list.add(source3);
        list.add(source4);
        list.add(source5);
        list.add(source6);
        list.add(source7);
        list.add(source8);
        list.add(source9);


        adapter = new SubsribeAdapter(list,getActivity());
        listView.setAdapter(adapter);
        superTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
            }
        });
//        UIUtils.initPrtClassLayout(storeHousePtrFrame);
//
////        /**
////         * 经典 风格的头部实现
////         */
//        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getActivity());
//        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);
//
//        storeHousePtrFrame.setHeaderView(header);
//        storeHousePtrFrame.addPtrUIHandler(header);
//
//        storeHousePtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
//        storeHousePtrFrame.setPtrHandler(new PtrDefaultHandler2() {
//            @Override
//            public void onLoadMoreBegin(final PtrFrameLayout frame) {
//                storeHousePtrFrame.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Log.d(TAG, "onLoadMoreBegin : 加载更多");
//                        frame.refreshComplete();
//                    }
//                }, 1000);
//
//            }
//
//            @Override
//            public void onRefreshBegin(final PtrFrameLayout frame) {
//                storeHousePtrFrame.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Log.d(TAG, "onRefreshBegin : 加载更多");
//                        frame.refreshComplete();
//                    }
//                }, 1000);
//            }
//
//            /**
//             * 检查是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时。
//             */
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                System.out.println("checkCanDoRefresh");
//                // 默认实现，根据实际情况做改动
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//                // return true;
//            }
//
//        });
    }
}
