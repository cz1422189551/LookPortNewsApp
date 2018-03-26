package com.cz.lookportnews.entity;

import java.io.Serializable;



/**
 * 多媒体类型, 父类
 * Created by 14221 on 2018/1/12.
 */


public class MultiMedia implements Serializable {

    public static final int PHOTO = 3 ;

    public static final int VIDEO = 2 ;

    public static final int ONE_PHOTO  = 1 ;

    public static final int  MORE_PHOTO = 4 ;


    //唯一标识
    protected  Long id ;

    //简介
    protected  String description;

    protected  String time ;

    //标题
    protected String title;

    //内容
    protected String content;

    //报道的来源
    protected String origin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
