package com.cz.lookportnews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.classic.adapter.CommonRecyclerAdapter;
import com.cz.lookportnews.R;
import com.cz.lookportnews.entity.News;

import java.util.List;

/**
 * Created by 14221 on 2018/2/12.
 */

public class MultipleLayoutAadapter extends CommonRecyclerAdapter<News> {

    private static final String TAG = "MultipleLayoutAadapter";

    public MultipleLayoutAadapter(@NonNull Context context, int layoutResId , List<News> data) {
        super(context, layoutResId , data);
    }

    public MultipleLayoutAadapter(@NonNull Context context, int layoutResId) {
        super(context, layoutResId);
    }

    @Override
    public void onCreate(RecyclerView.ViewHolder viewHolder, BaseAdapterHelper helper) {
        super.onCreate(viewHolder, helper);
    }

    @Override
    public void onUpdate(BaseAdapterHelper helper, News item, int position) {
        switch (item.getNewsShowType()){
            case News.ONE_PHOTO: //布局样式一
                helper.setText(R.id.tv_title, item.getTitle())
                          .setImageUrl(R.id.iv_icon,item.getImgUrl());
                break;
            case News.MORE_PHOTO: //布局样式二
                Log.d(TAG, "onUpdate: "+item.getTitle());
                String[] url = item.getImgUrl().split(";");
                if(url.length>=3){
                    helper.setText(R.id.tv_more_title, item.getTitle())
                            .setImageUrl(R.id.iv_more_1,url[0])
                            .setImageUrl(R.id.iv_more_2,url[1])
                            .setImageUrl(R.id.iv_more_3,url[2]);
                } else {
                    helper.setText(R.id.tv_title, item.getTitle())
                            .setImageUrl(R.id.iv_more_1,url[0]);
                }
                break;
//            case News.TYPE_MULTIPLE_PICTURE: //布局样式三
//                helper.setText(R.id.xxx, item.getTitle())
//                        .setImageUrl(R.id.xxx,item.getCoverUrl());
//                break;


        }

    }



    //多种布局重写此方法即可
    @Override
    public int getLayoutResId(News item, int position) {
        int layoutResId = -1;
        switch (item.getNewsShowType()){
            case News.ONE_PHOTO: //布局样式一
                layoutResId = R.layout.base_item;
                break;
            case News.MORE_PHOTO: //布局样式二
               layoutResId = R.layout.more_item;
                break;
            case News.PHOTO: //布局样式三
//                layoutResId = R.layout.item_multiple_picture;
                break;
        }
        return layoutResId;
    }

}
