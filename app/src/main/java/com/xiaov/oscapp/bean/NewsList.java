package com.xiaov.oscapp.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 作者：zhangjiawei
 * 时间：2016/12/29
 */
@SuppressWarnings("serial")
@XStreamAlias("oschina")
public class NewsList extends Entity implements ListEntity<News> {

    public final static String PREF_READED_NEWS_LIST = "readed_news_list.pref";

    public final static int CATALOG_ALL = 1;
    public final static int CATALOG_INTEGRATION = 2;
    public final static int CATALOG_SOFTWARE = 3;

    public final static int CATALOG_WEEK = 4;
    public final static int CATALOG_MONTH = 5;

    @XStreamAlias("catalog")
    private int catalog;

    @XStreamAlias("pagesize")
    private int pageSize;

    @XStreamAlias("newscount")
    private int newsCount;

    @XStreamAlias("newslist")
    private List<News> list = new ArrayList<News>();

    public int getCatalog() {
        return catalog;
    }

    public void setCatalog(int catalog) {
        this.catalog = catalog;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(int newsCount) {
        this.newsCount = newsCount;
    }

    public List<News> getList() {
        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "NewsList{" +
                "catalog=" + catalog +
                ", pageSize=" + pageSize +
                ", newsCount=" + newsCount +
                ", list=" + list +
                '}';
    }
}
