package com.elite.animation.ui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Elite Group
 * Created by wjc133 on 2015/10/8.
 */
public class ObjectAnimatorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();
        Button btn = null;
        ObjectAnimator animator = ObjectAnimator.ofFloat(btn, View.ALPHA, 0f);
    }
}
