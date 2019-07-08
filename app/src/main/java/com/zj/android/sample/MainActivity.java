package com.zj.android.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.zj.android.simpdialog.dialog.DoubleBtnDialog;
import com.zj.android.simpdialog.dialog.InputDoubleBtnDialog;
import com.zj.android.simpdialog.dialog.SingleBtnDialog;
import com.zj.android.simpdialog.dialog.ThreeMoreBtnDialog;
import com.zj.android.simpdialog.listener.DialogDoubleBtnClickListener;
import com.zj.android.simpdialog.listener.DialogSingleBtnClickListener;
import com.zj.android.simpdialog.listener.DialogThreeMoreBtnClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                showSingleBtnDialog();
                break;
            case R.id.btn2:
                showTwoBtnDialog();
                break;
            case R.id.btn3:
                showMoreBtnDialog();
                break;
            case R.id.btn4:
                showMoreStyleBtnDialog();
                break;
            case R.id.btn5:
                showInputStyleBtnDialog();
                break;
        }

    }

    private void showSingleBtnDialog() {
        new SingleBtnDialog.Builder(MainActivity.this, getLayoutInflater())
                .setDialogSingleBtnClickListener(new DialogSingleBtnClickListener() {
                    @Override
                    public void onSingleClickListener(int tag) {

                    }

                }).setBtnText("确定")
                .setTitle("提示")
                .setContent("是否关闭弹框")
                .create().show();

    }

    private void showTwoBtnDialog() {
        new DoubleBtnDialog.Builder(MainActivity.this, getLayoutInflater())
                .setLeftBtnString("取消")
                .setRightBtnString("确定").setBaseDialogDoubleBtnClickListener(new DialogDoubleBtnClickListener() {

            @Override
            public void baseOnLeftClickListener(int tag) {

            }

            @Override
            public void baseOnRightClickListener(int tag) {

            }
        }).setContent("消息内容").create().show();

    }

    private void showMoreBtnDialog() {
        ArrayList<String> list = new ArrayList<>();
        list.add("选择照片");
        list.add("拍照");
        list.add("取消");
        new ThreeMoreBtnDialog
                .Builder(this, getLayoutInflater())
                .setBtnText(list)
                .setDialogThreeMoreBtnClickListener(new DialogThreeMoreBtnClickListener() {

                    @Override
                    public void onMoreClickListener(int position, int tag) {

                    }
                })
                .setContent("请选择")
                .setCloseVisible(View.VISIBLE)//显示或隐藏关闭图标，默认关闭
                .create().show();
    }
    private void showMoreStyleBtnDialog() {
        new SingleBtnDialog.Builder(MainActivity.this, getLayoutInflater())
                .setDialogSingleBtnClickListener(new DialogSingleBtnClickListener() {
                    @Override
                    public void onSingleClickListener(int tag) {

                    }

                }).setBtnText("确定")
                .setTitle("提示")
                .setContent("是否关闭弹框")
                .setContentTextSize(R.dimen.font_large)
                .setBtnTextSize(R.dimen.font_large_plus)
                .setTitleTextColorRes(R.color.bg_color_green)
                .setContentTextColorRes(R.color.bg_color_blue)
                .create().show();

    }
    private void showInputStyleBtnDialog() {
        new InputDoubleBtnDialog
                .Builder(MainActivity.this, getLayoutInflater())
                .setBaseDialogDoubleBtnClickListener(new DialogDoubleBtnClickListener() {


                    @Override
                    public void baseOnLeftClickListener(int tag) {

                    }

                    @Override
                    public void baseOnRightClickListener(int tag) {

                    }
                })
                .setTitle("提示")
                .setTextHint("请输入内容")
                .create()
                .show();

    }
}
