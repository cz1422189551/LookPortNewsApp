package com.cz.lookportnews.ui;

import android.content.Context;
import android.util.Log;

import com.cz.lookportnews.adapter.CommentAdapter;
import com.cz.lookportnews.entity.Comment;
import com.cz.lookportnews.entity.User;

import java.util.List;

/**
 * Created by 14221 on 2018/2/23.
 */

public class CommentOnSendListener implements CommentDialog.OnSendListener {

    private static final String TAG = "CommentOnSendListener";


    private User user ;

    private Context context ;

    private List<Comment> comments;

    CommentAdapter adapter;

    public CommentOnSendListener(User user, Context context, List<Comment> comments , CommentAdapter adapter) {
        this.user = user;
        this.context = context;
        this.comments = comments;
        this.adapter =adapter;
    }

    @Override
    public void sendComment(String content) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setCommentContent(content);
        comment.setTime("刚刚");
        comments.add(comment);
        Log.d(TAG, "sendComment: ");

        adapter.notifyDataSetChanged();
    }
}
