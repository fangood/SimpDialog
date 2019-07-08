package com.zj.android.simpdialog.listener;

/**
 * 基类左右两个按钮的点击事件
 */
public interface DialogDoubleBtnClickListener {

    /**
     * 弹窗类型 多个弹窗时候使用此tag来区分
     *
     * @param tag
     */
    void baseOnLeftClickListener(int tag);

    /**
     * 弹窗类型 多个弹窗时候使用此tag来区分
     *
     * @param tag
     */
    void baseOnRightClickListener(int tag);


}
