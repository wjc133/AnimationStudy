package com.elite.animation.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.elite.animation.R;
import com.elite.animation.application.MyApplication;
import com.elite.animation.helper.BitmapCache;

/**
 * Elite Group
 * Created by wjc133 on 2015/10/9.
 */
public class SharedDetailActivity extends Activity {

    private TextView mTitleView;
    private TextView mDescriptionView;
    private TextView mLearnerView;
    private ImageView mPicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_detail);

        Bundle bundle = getIntent().getBundleExtra("news");
        String name = bundle.getString("name");
        String desc = bundle.getString("desc");
        String listener = bundle.getString("listener");
        int position = bundle.getInt("position");
        String picUrl = bundle.getString("picUrl");

        mTitleView = (TextView) findViewById(R.id.detail_title);
        mDescriptionView = (TextView) findViewById(R.id.detail_description);
        mPicView = (ImageView) findViewById(R.id.detail_img);
        mLearnerView = (TextView) findViewById(R.id.detail_listener);

        mPicView.setTransitionName("robot" + position);
        mTitleView.setText(name);
        mDescriptionView.setText(desc);
        mLearnerView.setText("当前听众:" + listener);

        //加载网络图片
        ImageLoader imageLoader = new ImageLoader(MyApplication.getRequestQueue(), new BitmapCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(mPicView, R.mipmap.ic_launcher, R.drawable.thumb2);
        imageLoader.get(picUrl, imageListener);
        mPicView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}
