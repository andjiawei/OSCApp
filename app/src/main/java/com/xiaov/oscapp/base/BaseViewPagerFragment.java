package com.xiaov.oscapp.base;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaov.oscapp.R;
import com.xiaov.oscapp.adapter.ViewPageFragmentAdapter;
import com.xiaov.oscapp.adapter.ViewPageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:带有导航条的基类
 * 作者：zhangjiawei
 * 时间：2016/12/27
 */
public abstract class BaseViewPagerFragment extends Fragment {

    public final String TAG=getClass().getName();
    public List<ViewPageInfo> mTabs=new ArrayList<>();

    public View mRoot;
    public TabLayout mTabLayout;
    public ViewPageFragmentAdapter mTabsAdapter;
    public ViewPager mViewpager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRoot == null) {//避免重复加载

            View root = inflater.inflate(R.layout.base_viewpage_fragment, null);
            mTabLayout = (TabLayout) root.findViewById(R.id.base_vp_fragment_tabLayout);
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
            mViewpager = (ViewPager) root.findViewById(R.id.base_vp_fragment_viewpager);
//          mErrorLayout = (EmptyLayout) root.findViewById(R.id.error_layout);

            mTabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(),mTabs,mTabLayout.getContext());
            setScreenPageLimit();
            onSetupTabAdapter(mTabsAdapter);
            mViewpager.setAdapter(mTabsAdapter);
            mTabLayout.setupWithViewPager(mViewpager);
            mRoot = root;
        }
        return mRoot;
    }

    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter mTabsAdapter);

    protected abstract void setScreenPageLimit();


}
