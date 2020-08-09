package com.example.smartcity.View.RecyclerView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Review;
import com.example.smartcity.R;
import com.google.firebase.database.collection.LLRBNode;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.smartcity.Utilitaries.App.getContext;

public class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.itemDetailsRenterIdentity) TextView renterIdentity;
    @BindView(R.id.itemsDetailsRenterRating) RatingBar rating;
    @BindView(R.id.itemDetailsComment) TextView comment;

    private ReviewAdapter.OnItemListener onItemListener;

    public ReviewViewHolder(@NonNull View itemView, ReviewAdapter.OnItemListener onItemListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}