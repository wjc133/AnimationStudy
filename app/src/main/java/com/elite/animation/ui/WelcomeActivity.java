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
    private String[] titles = {"View Animator", "Property Animator", "ViewPropertyAnimator", "Shared Elements Transition", "More..."};
    private String[] descriptions = {"Android 2.x使用的动画机制", "Android 3.0推出的属性动画机制", "针对View进行优化了的属性动画", "Android 5.0的Activity之间可以使用共享元素切换", "coming soon……"};

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
                        intent.setClass(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(WelcomeActivity.this, AlbumActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(WelcomeActivity.this, SharedActivity.class);
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
