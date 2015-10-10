package com.elite.animation.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elite.animation.R;

/**
 * Elite Group
 * Created by wjc133 on 2015/10/10.
 */
public class FragmentTransitionActivity extends Activity {
    private boolean mShowingBack = false;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_transition);
        btn = (Button) findViewById(R.id.btn_start);

        FrontFragment frontFragment = new FrontFragment();
        addFragment(frontFragment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackFragment backFragment = new BackFragment();
                replaceFragment(backFragment);
            }
        });
    }

    private void addFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
    }

    private void replaceFragment(Fragment fragment) {
        mShowingBack = getFragmentManager().getBackStackEntryCount() != 0;
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.flip_right_in, R.animator.flip_left_out,
                        R.animator.flip_left_in, R.animator.flip_right_out)
                .replace(R.id.container, fragment).addToBackStack(null).commit();
    }
}
