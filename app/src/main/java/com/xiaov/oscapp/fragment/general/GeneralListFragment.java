package com.xiaov.oscapp.fragment.general;


import com.xiaov.oscapp.fragment.base.BaseListFragment;
import com.xiaov.oscapp.interf.OnTabReselectListener;

/**
 */

public abstract class GeneralListFragment<T> extends BaseListFragment<T> implements OnTabReselectListener {
    @Override
    public void onTabReselect() {
        mListView.setSelection(0);
        mRefreshLayout.setRefreshing(true);
        onRefreshing();
    }
}
