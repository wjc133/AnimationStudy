package com.elite.animation.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.elite.animation.R;

/**
 * Created by wjc13 on 2015/10/10.
 */
public class ParallaxPageActivity extends Activity {

    private ViewPager mViewPager;
    private ImageView dummyImageView;
    private ParallaxPageTransformer mTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        dummyImageView = (ImageView) findViewById(R.id.weatherImg);
    }

    public class ParallaxPageTransformer implements ViewPager.PageTransformer {
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(1);
            } else if (position <= 1) { // [-1,1]
                dummyImageView.setTranslationX(-position * (pageWidth / 2)); //Half the normal speed
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(1);
            }
        }
    }
}
