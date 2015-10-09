package com.elite.animation.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.elite.animation.R;

/**
 * Elite Group
 * Created by wjc133 on 2015/9/25.
 */
public class AlbumActivity extends AppCompatActivity {
    private Animator mCurrentAnimator;
    private ImageView expandImageView;
    private GridLayout mGridLayout;

    private int[] mThumbDrawables = {R.drawable.food1, R.drawable.food2, R.drawable.food3,
            R.drawable.food4, R.drawable.food5, R.drawable.food6, R.drawable.food7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_album);
        mGridLayout = (GridLayout) findViewById(R.id.grid_pic);
        expandImageView = (ImageView) findViewById(R.id.expanded_image);
        for (int i = 0; i < mThumbDrawables.length; i++) {
            final int enterNum=i;
            ImageView imgView= (ImageView) mGridLayout.getChildAt(i);
            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    zoomFromThumb(v,mThumbDrawables[enterNum]);
                }
            });
        }
    }

    private void zoomFromThumb(final View v, int thumbResId) {
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        expandImageView.setImageResource(thumbResId);

        //为放大的图片计算开始和结束时的边界
        final Rect startBounds = new Rect();
        final Rect endBounds = new Rect();
        final Point globaleOffset = new Point();

        //缩略图的边界大小就是放大图的初始边界
        v.getGlobalVisibleRect(startBounds);
        //将container的偏移值作为边界的原点
        findViewById(R.id.container).getGlobalVisibleRect(endBounds, globaleOffset);
        startBounds.offset(-globaleOffset.x, -globaleOffset.y);
        endBounds.offset(-globaleOffset.x, -globaleOffset.y);

        //使用中心裁剪的技术让初始的边界纵横比保持和结束时的一致，这样可以防止在动画时出现不正常的图像伸缩，同时也计算缩放比（设定最终的缩放比为1）
        final float startScale;
        //先判断最终的纵横比和初始的纵横比哪个大（也就是说：“缩略图”和“container”哪个更“宽”）
        //如果container更“宽”，已高度计算缩放系数。
        if ((float) endBounds.width() / endBounds.height() > (float) startBounds.width() / startBounds.height()) {
            startScale = (float) startBounds.height() / endBounds.height();
            //startWidth是开始时边界的最终宽度，是以其和最终照片的高度比为系数，乘以最终宽度得出的；
            //deltaWidth
            float startWidth = startScale * endBounds.width();
            //调整过后如果比原来宽了，那么应该左边界向左挪，右边界向右挪
            //调整过后比原来窄，那么应该左边界往右挪，右边界往左挪
            //如果不设置这个，多出来的那一部分应该是会统一加到右边界去了。（猜测。。。）
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            startScale = (float) startBounds.width() / endBounds.width();
            float startHeight = startScale * endBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        //计算告一段落，我们要准备开始写动画了
        //放大图出现的时候，让原来的缩略图先暂时消失
//        v.setAlpha(0f);
        v.setVisibility(View.INVISIBLE);
        //让放大图可见
        expandImageView.setVisibility(View.VISIBLE);

        //轴心点移到左上角
        expandImageView.setPivotX(0f);
        expandImageView.setPivotY(0f);

        //创建Animator,放大，同时位置从原来的地方移到放大后的地方
        ObjectAnimator transX = ObjectAnimator.ofFloat(expandImageView, View.X, startBounds.left, endBounds.left);
        ObjectAnimator transY = ObjectAnimator.ofFloat(expandImageView, View.Y, startBounds.top, endBounds.top);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(expandImageView, View.SCALE_X, startScale, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(expandImageView, View.SCALE_Y, startScale, 1f);
        AnimatorSet set = new AnimatorSet();
        set.play(transX).with(transY).with(scaleX).with(scaleY);
        set.setDuration(500);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        expandImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }
                ObjectAnimator transX = ObjectAnimator.ofFloat(expandImageView, View.X, endBounds.left, startBounds.left);
                ObjectAnimator transY = ObjectAnimator.ofFloat(expandImageView, View.Y, endBounds.top, startBounds.top);
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(expandImageView, View.SCALE_X, 1f, startScale);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(expandImageView, View.SCALE_Y, 1f, startScale);
                AnimatorSet set = new AnimatorSet();
                set.play(transX).with(transY).with(scaleX).with(scaleY);
                set.setDuration(500);
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        mCurrentAnimator = null;
                        expandImageView.setVisibility(View.INVISIBLE);
                        v.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mCurrentAnimator = null;
                        expandImageView.setVisibility(View.INVISIBLE);
                        v.setVisibility(View.VISIBLE);
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}
