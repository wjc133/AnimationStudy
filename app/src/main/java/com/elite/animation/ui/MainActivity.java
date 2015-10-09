package com.elite.animation.ui;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elite.animation.R;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Button btn, btn_new;
    private RelativeLayout content;
    private boolean mShowingBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Point point = new Point(0, 100);
        Point point2 = new Point(100, 0);
        text = (TextView) findViewById(R.id.text_hello);
        btn = (Button) findViewById(R.id.btn_start);
        btn_new = (Button) findViewById(R.id.btn_new);
        content = (RelativeLayout) findViewById(R.id.main_content);

        FrontFragment frontFragment = new FrontFragment();
        addFragment(frontFragment);
        int newColor = 0xFF889900;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(text, "x", 0f, content.getWidth() - text.getWidth());
                animator.setInterpolator(new DecelerateInterpolator());
                animator.setDuration(5000);
                animator.start();
            }
        });

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.animate().rotationYBy(720).setDuration(3000);
            }
        });
//        ValueAnimator anim = ValueAnimator.ofObject(new (), point, point2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                BackFragment backFragment = new BackFragment();
                replaceFragment(backFragment);
                return true;
        }
        return false;
    }

    private void addFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().add(R.id.main_frame, fragment).commit();
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
                .replace(R.id.main_frame, fragment).addToBackStack(null).commit();
    }
}
