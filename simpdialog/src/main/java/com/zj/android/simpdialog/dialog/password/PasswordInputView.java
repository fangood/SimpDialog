package com.zj.android.simpdialog.dialog.password;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.zj.android.simpdialog.R;
import com.zj.android.simpdialog.util.ConvertUtils;


/**
 * 密码输入框
 * modify:
 * modify date:
 */
public class PasswordInputView extends View {

    /**
     * 默认6个密码
     */
    private static final int DEFAULT_PASSWORD_COUNT = 6;
    /**
     * 默认密码框颜色
     */
    private static final int DEFAULT_STOCK_COLOR = Color.BLACK;
    /**
     * 填充符号的颜色
     */
    private static final int DEFAULT_SYMBOL_COLOR = Color.BLACK;

    /**
     * 默认密码框圆角幅度
     */
    private static final int DEFAULT_PASSWORD_RADIUS = 6;

    /**
     * 默认密码框画笔大小 dp
     */
    private static final int DEFAULT_PASSWORD_BOX_WIDTH = 2;

    /**
     * 小圆点幅度
     */
    private static final int DEFAULT_PASSWORD_SYMBOL_RADIUS = 6;

    /**
     * 默认高度36dp
     */
    private static final int DEFAULT_PASSWORD_HEIGHT = 36;

    /**
     * 密码数量
     */
    private int passwordCount;

    /**
     * 输入框密码颜色
     */
    private int strokeColor;

    /**
     * 输入的小圆点
     */
    private Paint mSymbolPaint;

    /**
     * 密码输入整体的画笔
     */
    private Paint mPaint;

    /**
     * 符号的颜色 小圆点
     */
    private int symbolColor;

    /**
     * 边框圆角幅度
     */
    private float mRadius;

    /**
     * 小圆点的圆角幅度
     */
    private float mSymbolRadius;

    /**
     * 输入框边框的线条粗细
     */
    private float inputBoxStroke;

    /**
     * 文字（密码）
     */
    private StringBuffer mText;

    /**
     * 默认的像素值高度
     */
    private int defaultHeight;

    private PasswordInputListener passwordInputListener;

    //    第一个构造函数：     当不需要使用xml声明或者不需要使用inflate动态加载时候，实现此构造函数即可
    public PasswordInputView(Context context) {
        super(context);
        setDefault();
        init();
    }

    // 第二个构造函数:     当需要在xml中声明此控件，则需要实现此构造函数。并且在构造函数中把自定义的属性与控件的数据成员连接起来。
    public PasswordInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
        init();
    }


    // 第三个构造函数：     接受一个style资源
    public PasswordInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PasswordInputView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        getAttrs(context, attrs);
        init();
    }

    /**
     * 获得属性
     *
     * @param context
     * @param attrs
     */
    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PasswordInputView);
        passwordCount = typedArray.getInteger(R.styleable.PasswordInputView_passwordCount, DEFAULT_PASSWORD_COUNT);
        strokeColor = typedArray.getColor(R.styleable.PasswordInputView_stokeColor, DEFAULT_STOCK_COLOR);
        symbolColor = typedArray.getColor(R.styleable.PasswordInputView_symbolColor, DEFAULT_SYMBOL_COLOR);
        mRadius = typedArray.getDimension(R.styleable.PasswordInputView_radius, DEFAULT_PASSWORD_RADIUS);
        mSymbolRadius = typedArray.getDimension(R.styleable.PasswordInputView_symbolRadius, DEFAULT_PASSWORD_SYMBOL_RADIUS);
        inputBoxStroke = typedArray.getDimension(R.styleable.PasswordInputView_inputBoxStroke, DEFAULT_PASSWORD_BOX_WIDTH);
        typedArray.recycle();
    }

    /**
     * 设置默认的
     */
    private void setDefault() {
        passwordCount = DEFAULT_PASSWORD_COUNT;
        strokeColor = DEFAULT_STOCK_COLOR;
        symbolColor = DEFAULT_SYMBOL_COLOR;
        mRadius = DEFAULT_PASSWORD_RADIUS;
        mSymbolRadius = DEFAULT_PASSWORD_SYMBOL_RADIUS;
        inputBoxStroke = DEFAULT_PASSWORD_BOX_WIDTH;
    }

    /**
     * 实例化组件
     */
    private void init() {
        //设置输入框(整个view的大小)
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.WHITE);
        gradientDrawable.setStroke((int) inputBoxStroke, strokeColor);
        gradientDrawable.setCornerRadius(mRadius);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(gradientDrawable);
        } else {
            setBackgroundDrawable(gradientDrawable);
        }
        //输入框边框的画笔
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setColor(strokeColor);
            mPaint.setStrokeWidth(inputBoxStroke);
        }
        //输入框内部符号的设置
        if (mSymbolPaint == null) {
            mSymbolPaint = new Paint();
            mSymbolPaint.setColor(symbolColor);
            mSymbolPaint.setStyle(Paint.Style.FILL);
            mSymbolPaint.setAntiAlias(true);
        }
        defaultHeight = ConvertUtils.dip2px(getContext(), DEFAULT_PASSWORD_HEIGHT);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int speWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int speWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int speHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int speHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (speHeightMode == MeasureSpec.AT_MOST) { //wrap_content
            speHeightSize = defaultHeight;
        }
        if (speWidthMode == MeasureSpec.EXACTLY) { //match_parent 或者是具体的dp
            if (speWidthSize > speHeightSize * passwordCount) {
                speWidthSize = speHeightSize * passwordCount;
            }
        } else if (speWidthMode == MeasureSpec.AT_MOST) { //wrap_content
            speWidthSize = speHeightSize * passwordCount;
        }//UNSPECIFIED可以不予考虑
        setMeasuredDimension(speWidthSize, speHeightSize);  //这里面是原始的大小，需要重新计算可以修改本行
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int singleWidth = getWidth() / passwordCount;
        int height = getHeight();
        if (singleWidth > height) {
            singleWidth = height;
        }
        //绘制每个"●"之间的分割线
        for (int i = 1; i < passwordCount; i++) {
            canvas.drawLine(singleWidth * i, 0, singleWidth * i, height, mPaint);
        }

        if (mText != null) {
            //绘制"●"
            int textSize = mText.length() > passwordCount ? passwordCount : mText.length();
            for (int i = 1; i <= textSize; i++) {
                canvas.drawCircle(singleWidth * i - singleWidth / 2, height / 2, mRadius, mSymbolPaint);
            }
        }
    }

    /**
     * 得到密码数量
     *
     * @return
     */
    public int getPasswordCount() {
        return passwordCount;
    }

    /**
     * 设置密码数量
     *
     * @param passwordCount
     */
    public void setPasswordCount(int passwordCount) {
        this.passwordCount = passwordCount;
    }

    public CharSequence getPassword() {
        if (mText != null) {
            return mText;
        }
        return mText;
    }

    /**
     * 设置密码
     */
    public void setPassword(CharSequence password) {
        if (mText == null) {
            mText = new StringBuffer();
        }
        if (mText.length() < passwordCount - 1) {
            mText.append(password);
            invalidate();
        } else if (mText.length() == passwordCount - 1) {
            mText.append(password);
            invalidate();
            if (passwordInputListener != null) {
                passwordInputListener.onPasswordInputComplete(mText);
            }
        }
    }

    public void clearPassword() {
        if (mText != null) {
            mText.delete(0, mText.length());
            invalidate();
        }
    }

    public void deletePassword() {
        if (mText != null) {
            if (mText.length() > 1) {
                mText.delete(mText.length() - 1, mText.length());
            } else {
                mText.delete(0, 1);
            }
            invalidate();
        }
    }

    public PasswordInputListener getPasswordInputListener() {
        return passwordInputListener;
    }

    public void setPasswordInputListener(PasswordInputListener passwordInputListener) {
        this.passwordInputListener = passwordInputListener;
    }

    public interface PasswordInputListener {
        void onPasswordInputComplete(CharSequence text);
    }

}
