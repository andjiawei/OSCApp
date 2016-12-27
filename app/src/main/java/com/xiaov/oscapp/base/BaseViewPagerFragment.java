package com.xiaov.oscapp.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaov.oscapp.R;

/**
 * 描述:带有导航条的基类
 * 作者：zhangjiawei
 * 时间：2016/12/27
 */
public class BaseViewPagerFragment extends Fragment {

    public final String TAG=getClass().getName();

    private View mRoot;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "++++-------------++++++onAttach: " );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "++++-------------++++++onCreate: " );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "++++-------------++++++onCreateView: mRoot:"+mRoot );
        if (mRoot == null) {//避免重复加载
            View root = inflater.inflate(R.layout.base_viewpage_fragment, null);
            TextView textView= (TextView) root.findViewById(R.id.test);
            textView.setText(getClass().getName());
            mRoot = root;
        }
        return mRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "++++-------------++++++onViewCreated: " );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "++++-------------++++++onActivityCreated: " );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "++++-------------++++++onDestroy: " );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "++++-------------++++++onDestroyView: " );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "++++-------------++++++onDetach: " );

    }
}
