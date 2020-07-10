package com.example.smartcity.View.RecyclerView;

import androidx.recyclerview.widget.LinearLayoutManager;

import static com.example.smartcity.Utilitaries.App.getContext;


public class LayoutManagerRecyclerView extends LinearLayoutManager {

    private boolean isScrollEnabled = false;

    public LayoutManagerRecyclerView() {
        super(getContext());
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
