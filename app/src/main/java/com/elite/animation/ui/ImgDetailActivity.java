package com.elite.animation.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elite.animation.R;

import java.util.List;

/**
 * Elite Group
 * Created by wjc133 on 2015/10/9.
 */
public class ImgDetailActivity extends Activity {
    private ViewPager viewPager;
    private List<View> viewContainer;
    private List<String> titleContainer;
    private LayoutInflater inflater;

    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return titleContainer.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleContainer.get(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewContainer.get(position));
            return viewContainer.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewContainer.get(position));
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_detail);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

    }
}
