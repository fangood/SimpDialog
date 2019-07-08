package com.zj.android.simpdialog.listener;

/**
 * 基类单个按钮的点击事件
 */
public interface DialogSingleBtnClickListener {

    /**
     * 弹窗类型 多个弹窗时候使用此tag来区分
     *
     * @param tag
     */
    void onSingleClickListener(int tag);

}
