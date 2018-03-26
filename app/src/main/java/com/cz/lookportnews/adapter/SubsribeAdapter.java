package com.cz.lookportnews.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coorchice.library.SuperTextView;
import com.cz.lookportnews.R;
import com.cz.lookportnews.entity.Comment;
import com.cz.lookportnews.entity.SubsribeSource;

import java.util.List;


/**
 * Created by Administrator on 2016-11-30.
 */

public class SubsribeAdapter extends BaseAdapter   {

    private static final String TAG = "SubsribeAdapter";
    private List<SubsribeSource> list = null;
    private LayoutInflater mInflater;


    Context context;

    public SubsribeAdapter(List<SubsribeSource> list, Context context) {
        this.list=list;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.subsribe_item, null);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvDescribe = convertView.findViewById(R.id.tv_descibe);
            viewHolder.superTextView = convertView.findViewById(R.id.iv_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Log.d(TAG, "getView: "+list.get(position).getDescribe());
        viewHolder.tvDescribe.setText(list.get(position).getDescribe());
        viewHolder.tvTitle.setText(list.get(position).getName());
        if(position>3){
            viewHolder.superTextView.setDrawable(R.mipmap.ic_launcher);
        }else if(position>2){
            viewHolder.superTextView.setDrawable(R.drawable.zbh3);
        }else if(position<1){
            viewHolder.superTextView.setDrawable(R.drawable.zbh1);
        }else{
            viewHolder.superTextView.setDrawable(R.drawable.zbh5);
        }

        return convertView;
    }


    class ViewHolder {
        SuperTextView superTextView ;
        TextView tvTitle;
        TextView tvDescribe;

    }
}