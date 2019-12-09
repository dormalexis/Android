package com.example.smartcity;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.Model.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name) TextView nameText;
    @BindView(R.id.description) TextView descriptionText;
    @BindView(R.id.price) TextView priceText;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        //nameText = itemView.findViewById(R.id.name);
        //descriptionText = itemView.findViewById(R.id.description);
        //priceText = itemView.findViewById(R.id.price);
        ButterKnife.bind(this, itemView);
    }

    public void updateAnnonces(Item item)
    {
        this.nameText.setText(item.getName());
        this.descriptionText.setText(item.getDescription());
        this.priceText.setText(String.valueOf(item.getPricePerDay()));
    }
}
