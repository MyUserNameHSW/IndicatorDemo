package com.hsw.indictordemo.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hsw.indictordemo.R;

/**
 * @author heshuai
 * created on: 2020/7/8 6:54 PM
 * description: recyclerView指示器
 */
public class RecyclerViewIndicator extends FrameLayout {

    private View rootView;
    private View indView;

    public RecyclerViewIndicator(@NonNull Context context) {
        this(context, null);
    }

    public RecyclerViewIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerViewIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View root = inflate(context, R.layout.app_viewpager_indicator, this);
        rootView = root.findViewById(R.id.root);
        indView = root.findViewById(R.id.ind_view);
    }

    int indViewWidth = 0;
    int indViewHeight = 0;
    float rate = 0f;

    /**
     * 绑定recyclerView
     * @param recyclerView
     * @param orientation 排列方向
     *
     *  这里用到的recyclerView的三个方法
     *  computeHorizontalScrollExtent/computeVerticalScrollExtent 当前显示在屏幕上的总长度
     *  computeHorizontalScrollOffset/computeVerticalScrollOffset 当前滑动的总长度
     *  computeHorizontalScrollRange/computeVerticalScrollRange recylerView内部的总长度
     */
    public void setWithRecyclerView(final RecyclerView recyclerView, int orientation) {
        final boolean isHorizontal = orientation == RecyclerView.HORIZONTAL;
        rootView.post(new Runnable() {
            @Override
            public void run() {
                float scrollRange = isHorizontal ? recyclerView.computeHorizontalScrollRange() : recyclerView.computeVerticalScrollRange();
                float scrollExtent = isHorizontal ? recyclerView.computeHorizontalScrollExtent() : recyclerView.computeVerticalScrollExtent();
                LayoutParams layoutParams = (LayoutParams) indView.getLayoutParams();

                if (isHorizontal) {
                    //算出比例
                    rate = (float) getWidth() / scrollRange;
                    //由显示在屏幕上的总长度算出滑块长度
                    indViewWidth = (int) (scrollExtent * rate);
                    layoutParams.height = LayoutParams.MATCH_PARENT;
                    layoutParams.width = indViewWidth;
                } else {
                    rate = (float) getHeight() / scrollRange;
                    layoutParams.width = LayoutParams.MATCH_PARENT;
                    indViewHeight = (int) (scrollExtent * rate);
                    layoutParams.height = indViewHeight;
                }
                indView.setLayoutParams(layoutParams);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int scrollOffset = isHorizontal ? recyclerView.computeHorizontalScrollOffset() : recyclerView.computeVerticalScrollOffset();
                //由recyclerView滑动距离算出滑块移动距咯
                if (isHorizontal) {
                    int left = (int) (scrollOffset * rate);
                    indView.layout(left, indView.getTop(), left + indViewWidth, indView.getBottom());
                } else {
                    int top = (int) (scrollOffset * rate);
                    indView.layout(indView.getLeft(), top, indView.getRight(), top + indViewHeight);
                }

            }
        });
    }
}
