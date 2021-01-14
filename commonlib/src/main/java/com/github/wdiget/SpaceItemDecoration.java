package com.github.wdiget;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author liusir_xy
 * @date 2018/1/18 16:15
 * @desc {设置RecyclerView间隔}
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    //间隔距离
    private int mSpace = 0;
    private int leftSpace = 0, topSpace = 0, rightSpace = 0, dowSpace = 0;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = leftSpace;
        outRect.right = rightSpace;
        outRect.bottom = dowSpace;
        outRect.top = topSpace;
        int position = parent.getChildAdapterPosition(view);
        if (position % 2 == 0) {
            outRect.right = rightSpace == 0 ? mSpace : rightSpace;
        } else {
            outRect.left = leftSpace == 0 ? mSpace : leftSpace;
        }
//        if (parent.getChildAdapterPosition(view)>1){
//            outRect.top = mSpace*2;
//        }
    }

    public SpaceItemDecoration(int space, int leftSpace, int topSpace, int rightSpace, int dowSpace) {
        this.mSpace = space;
        this.leftSpace = leftSpace;
        this.topSpace = topSpace;
        this.dowSpace = dowSpace;
        this.rightSpace = rightSpace;
    }
}


