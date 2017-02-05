package com.xiaov.oscapp.viewpagerfragment;


import android.os.Bundle;

import com.xiaov.oscapp.R;
import com.xiaov.oscapp.adapter.ViewPageFragmentAdapter;
import com.xiaov.oscapp.adapter.ViewPageInfo;
import com.xiaov.oscapp.base.BaseListFragment;
import com.xiaov.oscapp.base.BaseViewPagerFragment;
import com.xiaov.oscapp.bean.BlogList;
import com.xiaov.oscapp.bean.NewsList;
import com.xiaov.oscapp.fragment.BlogFragment;
import com.xiaov.oscapp.fragment.EventFragment;
import com.xiaov.oscapp.fragment.QuestionFragment;
import com.xiaov.oscapp.fragment.general.NewsFragment;

/**
 * 描述: 综合 news
 * 作者：zhangjiawei
 * 时间：2016/12/27
 */
public class GeneralViewPagerFragment extends BaseViewPagerFragment {


    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
        String[] title = getResources().getStringArray(R.array.general_viewpage_arrays);
        mTabs.add(new ViewPageInfo(title[0], "news", NewsFragment.class, getBundle(NewsList.CATALOG_ALL)));
        mTabs.add(new ViewPageInfo(title[1], "latest_blog", BlogFragment.class, getBundle(NewsList.CATALOG_WEEK)));
        mTabs.add(new ViewPageInfo(title[2], "question", QuestionFragment.class, getBundle(BlogList.CATALOG_LATEST)));
        mTabs.add(new ViewPageInfo(title[3], "activity", EventFragment.class, getBundle(BlogList.CATALOG_RECOMMEND)));
    }

    @Override
    protected void setScreenPageLimit() {
        mViewpager.setOffscreenPageLimit(3);
    }

    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, newType);
        return bundle;
    }

    private Bundle getBundle(String catalog) {
        Bundle bundle = new Bundle();
        bundle.putString(BlogFragment.BUNDLE_BLOG_TYPE, catalog);
        return bundle;
    }

}
