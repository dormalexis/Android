package com.example.smartcity.View.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.GlideApp;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.description) TextView descriptionText;
    @BindView(R.id.price) TextView priceText;
    @BindView(R.id.image) ImageView image;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        //nameText = itemView.findViewById(R.id.name);
        //descriptionText = itemView.findViewById(R.id.description);
        //priceText = itemView.findViewById(R.id.price);
        ButterKnife.bind(this, itemView);
    }

    public void updateAnnonces(Item item, Context context)
    {
        this.descriptionText.setText(item.getDescription());
        this.priceText.setText(context.getString(R.string.dailyPrice) + " - " +String.valueOf(item.getPricePerDay()) + "€");

        if(item.getPictures().size() != 0)
        {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.ic_add);
            requestOptions.error(R.drawable.ic_check);

            GlideApp.with(context)
                    .load(item.getPictures().get(0).getPath())
                    .apply(requestOptions)
                    .override(100, 200)
                    .into(image);
        }
    }
}
