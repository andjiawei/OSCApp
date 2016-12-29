package com.xiaov.oscapp.ui;


import com.xiaov.oscapp.R;
import com.xiaov.oscapp.viewpagerfragment.ExploreFragment;
import com.xiaov.oscapp.viewpagerfragment.GeneralViewPagerFragment;
import com.xiaov.oscapp.viewpagerfragment.MyInformationFragment;
import com.xiaov.oscapp.viewpagerfragment.TweetsViewPagerFragment;

/**
 * 更换tab图片文字在此类
 */
public enum MainTab {

	/*
	NEWS(0, R.string.main_tab_name_news, R.drawable.tab_icon_new,
			NewsViewPagerFragment.class),
			*/

    NEWS(0, R.string.main_tab_name_news, R.drawable.tab_icon_new,
            GeneralViewPagerFragment.class),

    TWEET(1, R.string.main_tab_name_tweet, R.drawable.tab_icon_tweet,
            TweetsViewPagerFragment.class),

    QUICK(2, R.string.main_tab_name_quick, R.drawable.tab_icon_new,//这里不需要图标 因此随意写的
            null),

    EXPLORE(3, R.string.main_tab_name_explore, R.drawable.tab_icon_explore,
            ExploreFragment.class),

    ME(4, R.string.main_tab_name_my, R.drawable.tab_icon_me,
            MyInformationFragment.class);

    private int idx;//第几个
    private int resName;//text内容 tag
    private int resIcon;//图标
    private Class<?> clz;//对应fragment

    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
