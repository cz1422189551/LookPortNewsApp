package com.cz.lookportnews.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.coorchice.library.SuperTextView;
import com.cz.lookportnews.R;
import com.cz.lookportnews.entity.Comment;
import com.cz.lookportnews.ui.CommentDialog;
import com.cz.lookportnews.ui.ListViewNesting;
import com.cz.lookportnews.ui.MyPopupWindow;
import com.cz.lookportnews.util.UIUtils;

import java.util.List;


/**
 * Created by Administrator on 2016-11-30.
 */

public class CommentAdapter extends BaseAdapter {

    private static final String TAG = "CommentAdapter";
    private List<Comment> commentList = null;
    private LayoutInflater mInflater;

    private MyPopupWindow popupWindow = null;

    LinearLayout linearLayout;

    private boolean mCanGetBitmapFromNetWork = true;
    Activity context;
    CommentDialog commentDialog;

    View views;
    ClipboardManager cm = null;

    String copyStr;

    public CommentAdapter(List<Comment> commentList, Activity context,
                          CommentDialog dialog, MyPopupWindow popupWindow, View view, LinearLayout linearLayout) {
        this.commentList = commentList;
        mInflater = LayoutInflater.from(context);
        this.context = context;
        cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        commentDialog = dialog;
        this.linearLayout = linearLayout;
        this.popupWindow = popupWindow;
        this.views = view;
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

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        View view = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.detail_item_comment, null);

             viewHolder = new ViewHolder();
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_detail_contents);

            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_detail_time);
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tv_detail_comment_name);

            viewHolder.tvUserName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: " + view);
                }
            });

            viewHolder.tvReView = (TextView) convertView.findViewById(R.id.tv_detail_huifu);

            viewHolder.tvReView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (commentDialog != null) {
                        commentDialog.show();
                    }

                }
            });

            viewHolder.ivIcon = (SuperTextView) convertView.findViewById(R.id.iv_detail_icon);
            viewHolder.ivIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick:  ivIcon" + view);
                }
            });

            viewHolder.ivBingo = (ImageView) convertView.findViewById(R.id.iv_detail_bingo);
            viewHolder.ivBingo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick:  ivBingo" + view);
                }
            });
            viewHolder.ivMore = (ImageView) convertView.findViewById(R.id.iv_detail_more);
            final ViewHolder finalViewHolder = viewHolder;



            viewHolder.ivMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick:  ivMore" + "更多");

                    UIUtils.showPopWindow(context, popupWindow, views, linearLayout, finalViewHolder.tvContent);
                }
            });
            viewHolder.listViewNesting = convertView.findViewById(R.id.lv_detail);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvUserName.setText(commentList.get(position).getUser().getUsername());
        viewHolder.tvContent.setText(commentList.get(position).getCommentContent());

        viewHolder.tvTime.setText(commentList.get(position).getTime());

        if (commentList.get(position).getSubComment() != null && commentList.size() > 0) {
            ReViewAdapter reViewAdapter = new ReViewAdapter(commentList.get(position).getSubComment(), context);
            viewHolder.listViewNesting.setAdapter(reViewAdapter);
            viewHolder.listViewNesting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.d(TAG, "onItemClick: listViewNestIng ");
                }
            });
        }


        return convertView;
    }



    class ViewHolder {
        TextView tvUserName, tvContent, tvTime, tvReView;
        ImageView ivBingo, ivMore;
        SuperTextView ivIcon;
        ListViewNesting listViewNesting;
    }



}