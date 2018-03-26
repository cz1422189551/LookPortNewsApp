package com.cz.lookportnews.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  树状结构的频道栏目列表
 */


public class Channel implements Serializable{


    private int totalPage ;

    private int currentPage ;

    private int pageSize ;


    private Long id ;

    private String name;

    private String url ;

    private Integer accordion;

    private String fragmentName;


    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    private List<News>  newsList;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAccordion() {
        return accordion;
    }

    public void setAccordion(Integer accordion) {
        this.accordion = accordion;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }
}
