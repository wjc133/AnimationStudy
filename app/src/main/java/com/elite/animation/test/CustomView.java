package com.elite.animation.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.elite.animation.helper.UiUtils;

/**
 * Elite Group
 * Created by wjc133 on 2015/10/10.
 */
public class CustomView extends View {
    // 淡白色
    private static final int WHITE_COLOR = 0xfffde399;

    //Default Angle
    public static final int DEFAULT_START_ANGLE = 90;
    public static final int DEFAULT_SWEEP_ANGLE = 180;
    private RectF mArcRectF;
    private Paint mWhitePaint;
    private int mTotalWidth, mTotalHeight;

    // 用于控制绘制的进度条距离左／上／下的距离
    private static final int LEFT_MARGIN = 9;
    // 用于控制绘制的进度条距离右的距离
    private static final int RIGHT_MARGIN = 25;
    private int mLeftMargin, mRightMargin;
    //弧形区域的半径
    private int mArcRadius;
    private float mStartAngle, mSweepAngle;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mLeftMargin = UiUtils.dipToPx(context, LEFT_MARGIN);
        mRightMargin = UiUtils.dipToPx(context, RIGHT_MARGIN);

        initAngle();
        initPaint();
    }

    private void initAngle() {
        mStartAngle = DEFAULT_START_ANGLE;
        mSweepAngle = DEFAULT_SWEEP_ANGLE;
    }

    public void setStartAngle(int startAngle) {
        mStartAngle = startAngle;
    }

    public void setSweepAngle(int sweepAngle) {
        mSweepAngle = sweepAngle;
    }

    private void initPaint() {
        mWhitePaint = new Paint();
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setColor(WHITE_COLOR);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawArc(mArcRectF, mStartAngle, mSweepAngle, false, mWhitePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mArcRadius = (mTotalHeight - 2 * mLeftMargin) / 2;   //这样做，保证了mArcRectF是一个正方形
        mArcRectF = new RectF(mLeftMargin, mLeftMargin, mLeftMargin + 2 * mArcRadius,
                mTotalHeight - mLeftMargin);
    }
}
