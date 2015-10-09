package com.elite.animation.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.elite.animation.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Elite Group
 * Created by wjc133 on 2015/9/17.
 */
public class CustomHeaderView extends FrameLayout implements PtrUIHandler {

    private TextView mRefreshText;
    private ImageView mRefreshImageView;
    private Animation mRotateAnimation, mRevertRotateAnimation;
    private VerticalLineFlipView mProcessBar;


    public CustomHeaderView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.list_header, this);

        mRefreshText = (TextView) findViewById(R.id.pull_to_refresh_text);
        mRefreshImageView = (ImageView) findViewById(R.id.custom_arrow);
        mProcessBar = (VerticalLineFlipView) findViewById(R.id.pull_loading_image);

        mRotateAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setDuration(150);
        mRotateAnimation.setFillAfter(true);

        mRevertRotateAnimation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mRevertRotateAnimation.setInterpolator(new LinearInterpolator());
        mRevertRotateAnimation.setDuration(150);
        mRevertRotateAnimation.setFillAfter(true);
    }

    public CustomHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        mRefreshImageView.clearAnimation();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        mRefreshText.setText("下拉刷新");
        mProcessBar.setVisibility(INVISIBLE);
        mRefreshImageView.setVisibility(VISIBLE);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        mRefreshImageView.clearAnimation();
        mRefreshImageView.setVisibility(INVISIBLE);
        mProcessBar.startFilp();
        mProcessBar.setVisibility(VISIBLE);
        mRefreshText.setText("正在获取数据");
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        mRefreshText.setText("刷新完成");
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int mOffsetToRefresh = ptrFrameLayout.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
//                crossRotateLineFromBottomUnderTouch(ptrFrameLayout);
                if (mRefreshImageView != null) {
                    mRefreshImageView.clearAnimation();
                    mRefreshImageView.startAnimation(mRevertRotateAnimation);
                    mRefreshText.setText("下拉刷新");
                }
            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
//                crossRotateLineFromTopUnderTouch(ptrFrameLayout);
                if (mRefreshImageView != null) {
                    mRefreshImageView.clearAnimation();
                    mRefreshImageView.startAnimation(mRotateAnimation);
                    mRefreshText.setText("松开刷新");
                }
            }
        }
    }
}
