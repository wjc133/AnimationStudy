package com.elite.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

/**
 * Elite Group
 * Created by wjc133 on 2015/10/8.
 */
public class OldAnimationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_animation);
        final Button oldBtn = (Button) findViewById(R.id.btn_old);
        oldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OldAnimationActivity.this, R.string.string_old_demo, Toast.LENGTH_LONG).show();
            }
        });

        Button startBtn = (Button) findViewById(R.id.btn_old_start);
        Button stayBtn = (Button) findViewById(R.id.btn_old_start_stay);

        stayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 400, 0, 0);
                translateAnimation.setDuration(1000);
                translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                translateAnimation.setFillAfter(true);
                oldBtn.startAnimation(translateAnimation);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 400, 0, 0);
                translateAnimation.setDuration(1000);
                translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                oldBtn.startAnimation(translateAnimation);
            }
        });
    }
}
