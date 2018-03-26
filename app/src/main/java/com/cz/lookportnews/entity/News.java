package com.cz.lookportnews.entity;

import java.io.Serializable;
import java.util.List;



/**
 * 新闻类
 * Created by 14221 on 2018/1/12.
 */


public class News extends MultiMedia implements Serializable {

    private static final long serialVersionUID = 31544L;

    //评论回复集合
    private List<Comment> comments ;


    private String pageSource;

    public String getPageSource() {
        return pageSource;
    }

    public void setPageSource(String pageSource) {
        this.pageSource = pageSource;
    }

    private String editor;

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }


    @Override
    public String toString() {
        return "News{" +
                "editor='" + editor + '\'' +
                ", id=" + id +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", newsShowType=" + newsShowType +
                ", content='" + content + '\'' +
                ", origin='" + origin + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

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
