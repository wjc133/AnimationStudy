package com.elite.animation.transition;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * Elite Group
 * Created by wjc133 on 2015/9/22.
 */
public class CustomTransition extends Transition {

    private static final String BACKGROUD_KEY = "com.elite.transitiontraining:CustomTransition:backgroud";

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        View v = transitionValues.view;
        if (v != null) {
            transitionValues.values.put(BACKGROUD_KEY, v.getBackground());
        }
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }
        Drawable startBackground = (Drawable) startValues.values.get(BACKGROUD_KEY);
        Drawable endBackgroud = (Drawable) endValues.values.get(BACKGROUD_KEY);
        final View view = endValues.view;
        if (startBackground instanceof ColorDrawable && endBackgroud instanceof ColorDrawable) {
            ColorDrawable startColor = (ColorDrawable) startBackground;
            ColorDrawable endColor = (ColorDrawable) endBackgroud;
            if (startColor.getColor() != endColor.getColor()) {
                ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(), startColor.getColor(), endColor.getColor());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Object value = animation.getAnimatedValue();
                        if (value != null) {
                            view.setBackgroundColor((Integer) value);
                        }
                    }
                });
                return animator;
            }
        }
        return null;
    }
}
