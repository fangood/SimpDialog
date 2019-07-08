package com.zj.android.simpdialog.listener;

/**
 * 基类左右两个按钮的点击事件
 */
public interface DialogInputDoubleBtnOnClickListener {

    /**
     * 弹窗类型 多个弹窗时候使用此tag来区分
     *
     * @param tag
     * @param text 输入的文字
     */
    void onLeftClickListener(int tag, String text);

    /**
     * 弹窗类型 多个弹窗时候使用此type来区分
     *
     * @param tag
     * @param text 输入的文字
     */
    void onRightClickListener(int tag, String text);


}
