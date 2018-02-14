package com.cz.lookportnews.entity;

import java.util.List;



/**
 * 新闻类
 * Created by 14221 on 2018/1/12.
 */


public class News extends MultiMedia {

    //评论回复集合
    private List<Comment> comments ;


    //新闻的展示类型
    private int newsShowType ;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getNewsShowType() {
        return newsShowType;
    }

    public void setNewsShowType(int newsShowType) {
        this.newsShowType = newsShowType;
    }
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
