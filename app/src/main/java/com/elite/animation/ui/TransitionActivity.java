package com.elite.animation.ui;

import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.elite.animation.R;
import com.elite.animation.transition.CustomTransition;

/**
 * Elite Group
 * Created by wjc133 on 2015/10/10.
 */
public class TransitionActivity extends Activity {
    private Scene scenea;
    private Scene sceneb;
    private boolean isChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        Button btn_transition = (Button) findViewById(R.id.main_btn_transition);
        FrameLayout content = (FrameLayout) findViewById(R.id.main_content);
        scenea = Scene.getSceneForLayout(content, R.layout.scenea, this);
        sceneb = Scene.getSceneForLayout(content, R.layout.sceneb, this);

//        final TransitionSet transitionSet = (TransitionSet) TransitionInflater.from(this).inflateTransition(R.transition.fadesize);
        final Transition remove = new ChangeBounds();
        final Transition customTransition = new CustomTransition();
        final TransitionSet cus2 = new TransitionSet();
        customTransition.setDuration(1500);
        remove.setDuration(1500);
        cus2.setOrdering(TransitionSet.ORDERING_TOGETHER);
        cus2.addTransition(remove);
        cus2.addTransition(customTransition);

        btn_transition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChanged) {
                    TransitionManager.go(sceneb, cus2);
                    isChanged = true;
                } else {
                    TransitionManager.go(scenea, cus2);
                    isChanged = false;
                }
            }
        });
    }
}
