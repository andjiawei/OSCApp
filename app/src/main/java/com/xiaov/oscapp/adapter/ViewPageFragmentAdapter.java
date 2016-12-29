package com.xiaov.oscapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述: GeneralViewPagerFragment的adapter
 * 作者：zhangjiawei
 * 时间：2016/12/29
 */
public class ViewPageFragmentAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    public List<ViewPageInfo> mTabs = new ArrayList<>();
    private Map<String, Fragment> mFragments = new ArrayMap<>();

    public ViewPageFragmentAdapter(FragmentManager fm, List<ViewPageInfo> tabs, Context context) {
        super(fm);
        mContext=context;
        mTabs=tabs;
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override//调用notify时强制刷新
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        ViewPageInfo info = mTabs.get(position);

        Fragment fragment = mFragments.get(info.tag);
        if (fragment == null) {
            fragment = Fragment.instantiate(mContext, info.clss.getName(), info.args);
            // 避免重复创建而进行缓存
            mFragments.put(info.tag, fragment);
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {//自定义布局就不用这个了 优先级高于自定义布局
        return mTabs.get(position).title;
    }
}
