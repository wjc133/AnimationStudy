package com.elite.animation.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.elite.animation.R;
import com.elite.animation.test.CustomView;

/**
 * Elite Group
 * Created by wjc133 on 2015/10/10.
 */
public class TestActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private SeekBar startSeek;
    private SeekBar sweepSeek;
    private CustomView mArcRect;
    private TextView mStartView;
    private TextView mSweepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_test);

        mStartView = (TextView) findViewById(R.id.text_start_num);
        mSweepView = (TextView) findViewById(R.id.text_sweep_num);
        startSeek = (SeekBar) findViewById(R.id.seekbar_start_angle);
        sweepSeek = (SeekBar) findViewById(R.id.seekbar_sweep_angle);
        mArcRect = (CustomView) findViewById(R.id.arcrectf);

        configView();
    }

    private void configView() {
        startSeek.setMax(360);
        startSeek.setProgress(CustomView.DEFAULT_START_ANGLE);
        startSeek.setOnSeekBarChangeListener(this);

        sweepSeek.setMax(360);
        sweepSeek.setProgress(CustomView.DEFAULT_SWEEP_ANGLE);
        sweepSeek.setOnSeekBarChangeListener(this);

        mStartView.setText(CustomView.DEFAULT_START_ANGLE + "度");
        mSweepView.setText(CustomView.DEFAULT_SWEEP_ANGLE + "度");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == startSeek) {
            mArcRect.setStartAngle(progress);
            mStartView.setText(progress + "度");
        }else if (seekBar == sweepSeek) {
            mArcRect.setSweepAngle(progress);
            mSweepView.setText(progress + "度");
        }
        mArcRect.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
