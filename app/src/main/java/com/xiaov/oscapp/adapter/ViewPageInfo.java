package com.xiaov.oscapp.adapter;

import android.os.Bundle;

/**
 * viewpager中每一个item的fragment 和 title 及bundle参数 和tag
 */
public final class ViewPageInfo {

	public final String tag;
    public final Class<?> clss;
    public final Bundle args;
    public final String title;

    public ViewPageInfo(String _title, String _tag, Class<?> _class, Bundle _args) {
    	title = _title;
        tag = _tag;
        clss = _class;
        args = _args;
    }
}