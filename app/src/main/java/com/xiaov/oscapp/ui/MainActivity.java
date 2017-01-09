package com.xiaov.oscapp.ui;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.xiaov.oscapp.R;
import com.xiaov.oscapp.widget.BadgeView;

import static com.xiaov.oscapp.R.id.realtabcontent;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, TabHost.OnTabChangeListener {

    private FragmentTabHost mTabHost;
    private BadgeView mBvNotice;
    private TextView mToolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setTitle("");
        mToolBarTitle = (TextView) findViewById(R.id.toolbar_title);

        findViewById(R.id.quick_option_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuickOptionDialogFragment quickOptionDialogFragment=new QuickOptionDialogFragment();
                quickOptionDialogFragment.show(getSupportFragmentManager(),"QuickOptionDialogFragment");
            }
        });

        findViewById(R.id.toolbar_title);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), realtabcontent);
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_BEGINNING);//不显示分隔符

        mTabHost.setOnTabChangedListener(this);
        initTabs();
    }

    private void initTabs() {
        MainTab[] tabs = MainTab.values();
        int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));//参数就是切换回调的tabId 必须不一样时 fragment才能切换成功
            View indicator = View.inflate(this, R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            ImageView icon = (ImageView) indicator.findViewById(R.id.iv_icon);

            Drawable drawable = ContextCompat.getDrawable(this, mainTab.getResIcon());
            icon.setImageDrawable(drawable);
            if (i == 2) {
                indicator.setVisibility(View.INVISIBLE);
//              mTabHost.setNoTabChangedTag(getString(mainTab.getResName()));
            }
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);//给每个tab设置contentView
//            tab.setContent(new TabHost.TabContentFactory() {
//
//                @Override
//                public View createTabContent(String tag) {
//                    return new View(MainActivity.this);
//                }
//            });
            mTabHost.addTab(tab, mainTab.getClz(), null);//绑定tag和fragment

            if (mainTab.equals(MainTab.ME)) {
                View cn = indicator.findViewById(R.id.tab_mes);
                mBvNotice = new BadgeView(MainActivity.this, cn);
                mBvNotice.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
                mBvNotice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                mBvNotice.setBackgroundResource(R.drawable.notification_bg);
                mBvNotice.setGravity(Gravity.CENTER);
            }
            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);//给每个tab设置监听
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onTabChanged(String tabId) {
        //tabId 是 newTabSpec（）设置的参数
        mToolBarTitle.setText(tabId);//toolbar上文字
    }
}
