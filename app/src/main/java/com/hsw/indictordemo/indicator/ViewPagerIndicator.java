package com.hsw.indictordemo.indicator;

import android.content.Context;

import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.hsw.indictordemo.R;

/**
 * @author heshuai
 * created on: 2020/7/8 6:54 PM
 * description: viewPager指示器
 */
public class ViewPagerIndicator extends FrameLayout {

    private View rootView;
    private View indView;

    public ViewPagerIndicator(@NonNull Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View root = inflate(context, R.layout.app_viewpager_indicator, this);
        rootView = root.findViewById(R.id.root);
        indView = root.findViewById(R.id.ind_view);
    }

    int indViewWidth = 0;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public void setWithViewPager(ViewPager viewPager) {
        //如果没有adapter，则隐藏不显示
        if (null == viewPager.getAdapter()) {
            setVisibility(GONE);
            Log.e(getClass().getSimpleName(), "no adapter");
            return;
        }
        //获取viewPager中fragment的数量
        final int count = viewPager.getAdapter().getCount();
        if (count == 0) {
            return;
        }

        //加载到window之后再进行view宽度的获取
        rootView.post(new Runnable() {
            @Override
            public void run() {
                //获取当前滑块的宽度
                indViewWidth = getWidth() / count;
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) indView.getLayoutParams();
                layoutParams.width = indViewWidth;
                indView.setLayoutParams(layoutParams);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             *
             * @param position
             * @param positionOffset [0，1]中的值，指示在位置处与页面的偏移百分比。
             * @param positionOffsetPixels 以像素为单位的值，表示与位置的偏移量。
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //获取滑块距父布局左侧的距离
                int left = (int) (position * indViewWidth + positionOffset * indViewWidth);

                //重新布局滑块view
                indView.layout(left, indView.getTop(), left + indViewWidth, indView.getBottom());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
