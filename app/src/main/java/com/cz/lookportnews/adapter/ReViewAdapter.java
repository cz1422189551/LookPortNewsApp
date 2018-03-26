package com.cz.lookportnews.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.coorchice.library.SuperTextView;
import com.cz.lookportnews.R;
import com.cz.lookportnews.entity.Comment;

import java.util.List;


/**
 * Created by Administrator on 2016-11-30.
 */

public class ReViewAdapter extends BaseAdapter {

    private static final String TAG = "ReViewAdapter";
    private List<Comment> commentList = null;
    private LayoutInflater mInflater;


    Context context;

    public ReViewAdapter(List<Comment> commentList, Context context) {
        this.commentList = commentList;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
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
            convertView = mInflater.inflate(R.layout.review_item, null);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_item_reviewer);
            viewHolder.linearLayout = convertView.findViewById(R.id.ll_review_more);
            viewHolder.checkMore = convertView.findViewById(R.id.tv_item_checkmore);
            viewHolder.checkMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: " + view);
                }
            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvContent.setText(commentList.get(position).getUser().getUsername() + " : "
                + commentList.get(position).getCommentContent());
        //如果回复评论数，超过4将整栏折叠。
        if (position >= 4) {
            viewHolder.linearLayout.setVisibility(View.VISIBLE);
        } else {
            viewHolder.linearLayout.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder {
        TextView tvContent;
        TextView checkMore;
        LinearLayout linearLayout;
    }
}