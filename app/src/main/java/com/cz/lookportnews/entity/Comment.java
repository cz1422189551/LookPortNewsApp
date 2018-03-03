package com.cz.lookportnews.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * 评论
 * Created by 14221 on 2018/1/12.
 */


public class Comment implements Serializable {

    private Long id ;

    private MultiMedia multiMedia;

    private User user ;

    //评论内容
    private String commentContent ;

    //改评论的回复
    private List<Comment>  subComment ;

    //评论时间
    private String time ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultiMedia getMultiMedia() {
        return multiMedia;
    }

    public void setMultiMedia(MultiMedia multiMedia) {
        this.multiMedia = multiMedia;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public List<Comment> getSubComment() {
        return subComment;
    }

    public void setSubComment(List<Comment> subComment) {
        this.subComment = subComment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
