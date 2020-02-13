package com.example.smartcity.View.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.GlideApp;


import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.name) TextView descriptionText;
    @BindView(R.id.price) TextView priceText;
    private ItemAdapter.OnItemListener onItemListener;
    @BindView(R.id.picture) ImageView image;
    @BindView(R.id.ratingBar) RatingBar ratingBar;
    @BindView(R.id.rateNumber) TextView rateNumber;
    @BindView(R.id.visibility) TextView isVisible;

    public ItemViewHolder(@NonNull View itemView, ItemAdapter.OnItemListener onItemListener) {
        super(itemView);
        //nameText = itemView.findViewById(R.id.name);
        //descriptionText = itemView.findViewById(R.id.description);
        //priceText = itemView.findViewById(R.id.price);
        ButterKnife.bind(this, itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    public void updateAnnonces(Item item, Context context)
    {
        this.descriptionText.setText(item.getName());
        this.priceText.setText(context.getString(R.string.dailyPrice) + " - " +String.valueOf(item.getPricePerDay()) + "€");
        this.ratingBar.setRating(item.getNbStars());
        this.rateNumber.setText(context.getString(R.string.rateNumber) + " " + item.getNbAvis() + " " + context.getString(R.string.avis));
        if(!item.getVisible())
        {
            this.isVisible.setText(context.getString(R.string.hidden));
            this.isVisible.setTextColor(Color.RED);
        }
        if(item.getPictures().size() != 0)
        {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.ic_add);
            requestOptions.error(R.drawable.ic_check);

            GlideApp.with(context)
                    .load(item.getPictures().get(0).getPath())
                    .apply(requestOptions)
                    .override(100, 100)
                    .into(image);
        }
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}
