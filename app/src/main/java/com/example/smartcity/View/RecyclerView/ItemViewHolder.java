package com.example.smartcity.View.RecyclerView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity.DataAccess.ViewModel.FavoriteViewModel;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.GlideApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    
    @BindView(R.id.title) TextView title;
    @BindView(R.id.price) TextView priceText;
    @BindView(R.id.ratingBar) RatingBar ratingBar;
    @BindView(R.id.rateNumber) TextView rateNumber;
    @BindView(R.id.visibility) TextView isVisible;
    @BindView(R.id.ownerIdentity) TextView ownerIdentity;
    @BindView(R.id.favorite) FloatingActionButton favorite;
    @BindView(R.id.viewPagerItem) ViewPager pictures;


    private FavoriteViewModel favoriteViewModel = new FavoriteViewModel();
    private ItemAdapter.OnItemListener onItemListener;

    public ItemViewHolder(@NonNull View itemView, ItemAdapter.OnItemListener onItemListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    public void updateAnnonces(Item item, Context context)
    {
        this.title.setText(item.getTitle());
        this.priceText.setText(context.getString(R.string.dailyPrice) + " - " + String.valueOf(item.getPricePerDay()) + " €");
        this.ratingBar.setRating(item.getNbStars());
        this.rateNumber.setText("(" + item.getNbAvis() + ")");
        if(item.getFavorite())
        {
            favorite.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FF5252")));
        }
        if(item.getVisible() != null && !item.getVisible())
        {
            this.isVisible.setText(context.getString(R.string.hidden));
            this.isVisible.setTextColor(Color.RED);
        }

        this.ownerIdentity.setText(item.getOwnerFirstName() + " " + item.getOwnerName());

        ImageAdapter adapterView = new ImageAdapter(item);
        pictures.setAdapter(adapterView);

        favorite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(item.getFavorite())
                {
                    favorite.setImageTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                }

                else {
                    favorite.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FF5252")));
                }
                item.setFavorite(!item.getFavorite());
                Log.i("Alexis", item.getItemId() + "");
                favoriteViewModel.favorite(item.getItemId());
            }
        });
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}
