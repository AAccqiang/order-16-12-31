package com.example.administrator.orderapp.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(parent.getChildItemId(view)!= 0){
            outRect.bottom = space;
        }
    }
}
