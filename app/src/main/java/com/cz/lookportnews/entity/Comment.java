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
    private Date time ;
}
