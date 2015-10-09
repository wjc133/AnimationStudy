package com.elite.animation.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.elite.animation.R;


public class VerticalLineFlipView extends LinearLayout {

    private int mDuration;
    private int mLineColor;
    private View line1;
    private View line2;
    private View line3;
    private View line4;

    private ObjectAnimator animator1;
    private ObjectAnimator animator2;
    private ObjectAnimator animator3;
    private ObjectAnimator animator4;

    private Handler mHanlder;

    public VerticalLineFlipView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public VerticalLineFlipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public VerticalLineFlipView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VerticalLineFlipView, defStyle, 0);
        mDuration = a.getInt(R.styleable.VerticalLineFlipView_duration, 1200);
        mLineColor = a.getColor(R.styleable.VerticalLineFlipView_lineColor, Color.WHITE);

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        LayoutInflater.from(context).inflate(R.layout.layout_verticalline_filp_view, this, true);

        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        line1.setBackgroundColor(mLineColor);
        line2.setBackgroundColor(mLineColor);
        line3.setBackgroundColor(mLineColor);
        line4.setBackgroundColor(mLineColor);
        mHanlder = new Handler();

        a.recycle();

        setStopState();
    }

    public void startFilp() {
        if (null == animator1) {
            animator1 = filpX(line1, 0);
        }
        if (null == animator2) {
            animator2 = filpX(line2, 200);
        }
        if (null == animator3) {
            animator3 = filpX(line3, 400);
        }
        if (null == animator4) {
            animator4 = filpX(line4, 600);
        }
    }

    public void endFilp() {

        if (null != animator1) {
            animator1.end();
            animator1 = null;
        }

        if (null != animator2) {
            animator2.end();
            animator2 = null;
        }

        if (null != animator3) {
            animator3.end();
            animator3 = null;
        }

        if (null != animator4) {
            animator4.end();
            animator4 = null;
        }

        setStopState();
    }

    private void setStopState() {
        line1.setRotationX(60);
        line2.setRotationX(0);
        line3.setRotationX(40);
        line4.setRotationX(70);
    }

    private ObjectAnimator filpX(final View view, int startDelay) {
        final ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationX", 0, 180);
        animator.setDuration(mDuration);
        animator.setStartDelay(startDelay);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
        return animator;
    }

}
