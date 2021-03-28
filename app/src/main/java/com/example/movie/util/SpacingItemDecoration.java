package com.example.movie.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingItemDecoration extends RecyclerView.ItemDecoration {

    private final int space;

    public SpacingItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, View view, RecyclerView parent,@NonNull RecyclerView.State state) {
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int position = parent.getChildViewHolder(view).getAdapterPosition();
        if(gridLayoutManager != null){
            float spanSize = gridLayoutManager.getSpanSizeLookup().getSpanSize(position);
            float totalSpanSize = gridLayoutManager.getSpanCount();

            float n = totalSpanSize / spanSize;
            float c = layoutParams.getSpanIndex() / spanSize;

            float leftPadding = space * ((n - c) / n);
            float rightPadding = space * ((c + 1) / n);

            outRect.left = (int) leftPadding;
            outRect.right = (int) rightPadding;
        }

    }
}