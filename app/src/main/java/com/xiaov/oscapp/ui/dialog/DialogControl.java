package com.xiaov.oscapp.ui.dialog;

import android.app.ProgressDialog;

/**
 * 描述:
 * 作者：zhangjiawei
 * 时间：2017/1/20
 */
public interface DialogControl {
    public abstract void hideWaitDialog();

    public abstract ProgressDialog showWaitDialog();

    public abstract ProgressDialog showWaitDialog(int resid);

    public abstract ProgressDialog showWaitDialog(String text);
}
