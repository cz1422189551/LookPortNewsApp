package com.cz.lookportnews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.classic.adapter.CommonRecyclerAdapter;
import com.cz.lookportnews.R;
import com.cz.lookportnews.entity.News;
import com.cz.lookportnews.ui.IAdapterNotificaitionListener;
import com.cz.lookportnews.util.Constant;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by 14221 on 2018/2/12.
 */

public class MultipleLayoutAadapter extends CommonRecyclerAdapter<News> implements IAdapterNotificaitionListener {

    private static final String TAG = "MultipleLayoutAadapter";

    Context mContext;



    public MultipleLayoutAadapter(@NonNull Context context, int layoutResId , List<News> data) {
        super(context, layoutResId , data);
        mContext=context;
    }

    public MultipleLayoutAadapter(@NonNull Context context, int layoutResId) {
        super(context, layoutResId);
        mContext=context;
    }

    @Override
    public void onCreate(RecyclerView.ViewHolder viewHolder, BaseAdapterHelper helper) {
        super.onCreate(viewHolder, helper);

    }

    @Override
    public void onUpdate(BaseAdapterHelper helper, News item, int position) {
        Log.d(TAG, "onUpdate: "+item.getTitle());
        switch (item.getNewsShowType()){
            case News.ONE_PHOTO: //布局样式一
                TextView tvTitle =(TextView) helper.getView(R.id.tv_title);
                 tvTitle.setTextSize(Constant.TextDefalut+ Constant.TEXT_SIZE*3);
                ImageView img = (ImageView)helper.getView(R.id.iv_icon);
                String imgUrl = item.getImgUrl();
                if(!TextUtils.isEmpty(imgUrl)){
                    Picasso.with(mContext).load(imgUrl).into(img);
                }
                if(!TextUtils.isEmpty(item.getOrigin())){
                    helper.setText(R.id.tv_from,item.getOrigin());
                }
                helper.setText(R.id.tv_title, item.getTitle());
                break;
            case News.MORE_PHOTO: //布局样式二
                String[] url = item.getImgUrl().split(";");
                if(url.length>=3){
                    for (int i = 0; i < url.length; i++) {
                        Log.d(TAG, "onUpdate: MMM "+url[i]);
                    }
                    TextView tvTitles =(TextView) helper.getView(R.id.tv_more_title);
                    tvTitles.setTextSize(Constant.TextDefalut+ Constant.TEXT_SIZE*3);

                    ImageView img1 = (ImageView)helper.getView(R.id.iv_more_1);
                    ImageView img2 = (ImageView)helper.getView(R.id.iv_more_2);
                    ImageView img3 = (ImageView)helper.getView(R.id.iv_more_3);
                    Picasso.with(mContext).load(url[0]).into(img1);
                    Picasso.with(mContext).load(url[1]).into(img2);
                    Picasso.with(mContext).load(url[2]).into(img3);
                    helper.setText(R.id.tv_more_title, item.getTitle());
                } else {
                    helper.setText(R.id.tv_title, item.getTitle())
                            .setImageUrl(R.id.iv_more_1,url[0]);
                    Log.d(TAG, "onUpdate:  2 else" );
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

    @Override
    public void notification() {
        this.notifyDataSetChanged();
    }
}
