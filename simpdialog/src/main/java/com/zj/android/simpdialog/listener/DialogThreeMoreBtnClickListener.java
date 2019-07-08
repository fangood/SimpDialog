package com.zj.android.simpdialog.listener;

/**
 * 基类单个按钮的点击事件
 */
public interface DialogThreeMoreBtnClickListener {

    /**
     * 弹窗类型 多个弹窗时候使用此tag来区分
     *
     * @param position
     * @param tag
     */
    void onMoreClickListener(int position, int tag);


}
