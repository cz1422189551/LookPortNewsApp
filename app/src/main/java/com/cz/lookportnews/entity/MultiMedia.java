package com.cz.lookportnews.entity;

import java.io.Serializable;



/**
 * 多媒体类型, 父类
 * Created by 14221 on 2018/1/12.
 */


public class MultiMedia implements Serializable {

    //唯一标识
    protected  Long id ;

    //简介
    protected  String description;

    //标题
    protected String Title;

    //内容
    protected String Content;

    //报道的来源
    protected String origin;



}
