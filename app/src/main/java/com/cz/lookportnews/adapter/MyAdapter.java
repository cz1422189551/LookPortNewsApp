package com.cz.lookportnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cz.lookportnews.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<String> stuList;
    private LayoutInflater inflater;

    public MyAdapter() {
    }

    public MyAdapter(List<String> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public String getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view = inflater.inflate(R.layout.item1, null);


        //在view视图中查找id为image_photo的控件
        TextView tv_name = (TextView) view.findViewById(R.id.tv_1);

        tv_name.setText(stuList.get(position));
        return view;
    }

}