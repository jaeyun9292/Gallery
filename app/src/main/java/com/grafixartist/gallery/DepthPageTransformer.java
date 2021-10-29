package com.grafixartist.gallery;

import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;

public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    // 각 스와이프할때 페이지별 포지션
    // 0페이지일때  0 1
    // 1페이지일때 -1 0 1
    // 1페이지에서 2페이지로 반쯤 넘어갔을때 -1.5 -0.5 0.5
    // 2페이지에서 3페이지로 반쯤 넘어갔을때 -1.5 -0.5 0.5
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.

            Log.e("TAG", String.valueOf(position));
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}