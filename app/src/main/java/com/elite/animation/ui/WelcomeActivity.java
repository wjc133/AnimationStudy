package com.elite.animation.ui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.elite.animation.R;
import com.elite.animation.adapter.SelectorAdapter;
import com.elite.animation.bean.Selector;

import java.util.ArrayList;
import java.util.List;

/**
 * Elite Group
 * Created by wjc133 on 2015/10/9.
 */
public class WelcomeActivity extends Activity {
    private String[] titles = {"View Animation", "画廊图片点击放大效果", "ViewPropertyAnimator", "Fragment切换动画",
            "Transition Framework", "Shared Elements Transition", "叶子进度条动画效果","小球落地弹回效果", "More..."};
    private String[] descriptions = {"Android 2.x使用的动画机制，此时主要展示其弊端", "利用Property Animation实现，Property Animation是Android 3.0推出的全新的动画系统",
            "针对View进行优化了的属性动画", "Fragment切换动画示例，Fragment的切换动画主要通过xml定义，其本质为Property Animation",
            "Transition Framework的使用示例，指定视图层下所有子View都会产生动画", "Android 5.0的Activity之间可以使用共享元素切换",
            "利用View的动画实现","API Demo中提供的触摸小球下落的例子，该例说明了属性动画可以应用于任意对象，不一定非得是View", "coming soon……"};

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mListView = (ListView) findViewById(R.id.wel_list);
//        List<Selector> selectors=getDataList();
        SelectorAdapter adapter = new SelectorAdapter(this, titles, descriptions);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(WelcomeActivity.this, OldAnimationActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(WelcomeActivity.this, AlbumActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(WelcomeActivity.this, AlbumActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(WelcomeActivity.this, FragmentTransitionActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(WelcomeActivity.this, TransitionActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(WelcomeActivity.this, SharedActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent.setClass(WelcomeActivity.this, LeafLoadingActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent.setClass(WelcomeActivity.this, BouncingBalls.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private List<Selector> getDataList() {
        List<Selector> selectors = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Selector selector = new Selector();
            selector.setTitle(titles[i]);
            selector.setDescription(descriptions[i]);
            selectors.add(selector);
        }
        return selectors;
    }
}
