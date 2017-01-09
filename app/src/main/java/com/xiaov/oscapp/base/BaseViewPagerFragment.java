package com.xiaov.oscapp.base;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xiaov.oscapp.R;
import com.xiaov.oscapp.adapter.ViewPageFragmentAdapter;
import com.xiaov.oscapp.adapter.ViewPageInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.support.design.widget.TabLayout.GRAVITY_CENTER;

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
            mTabLayout.setTabGravity(GRAVITY_CENTER);
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
            mViewpager = (ViewPager) root.findViewById(R.id.base_vp_fragment_viewpager);
//          mErrorLayout = (EmptyLayout) root.findViewById(R.id.error_layout);

            mTabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(),mTabs,mTabLayout.getContext());
            setScreenPageLimit();
            onSetupTabAdapter(mTabsAdapter);
            mViewpager.setAdapter(mTabsAdapter);
            mTabLayout.setupWithViewPager(mViewpager);
            setUpIndicatorWidth();
            mRoot = root;
        }
        return mRoot;
    }

    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter mTabsAdapter);

    protected abstract void setScreenPageLimit();

    /**
     * 通过反射修改TabLayout Indicator的宽度（仅在Android 4.2及以上生效）
     */
    private void setUpIndicatorWidth() {
        Class<?> tabLayoutClass = mTabLayout.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayoutClass.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        LinearLayout layout = null;
        try {
            if (tabStrip != null) {
                layout = (LinearLayout) tabStrip.get(mTabLayout);
            }
            for (int i = 0; i < layout.getChildCount(); i++) {
                View child = layout.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    params.setMarginStart(50);
                    params.setMarginEnd(50);
                }
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
