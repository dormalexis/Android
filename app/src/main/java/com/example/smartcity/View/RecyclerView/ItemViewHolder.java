package com.example.smartcity.View.RecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.Model.Item;
import com.example.smartcity.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.description) TextView descriptionText;
    @BindView(R.id.price) TextView priceText;
    @BindView(R.id.owner) TextView owner;
    private ItemAdapter.OnItemListener onItemListener;

    public ItemViewHolder(@NonNull View itemView, ItemAdapter.OnItemListener onItemListener) {
        super(itemView);

        //nameText = itemView.findViewById(R.id.name);
        //descriptionText = itemView.findViewById(R.id.description);
        //priceText = itemView.findViewById(R.id.price);
        ButterKnife.bind(this, itemView);

        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    public void updateAnnonces(Item item)
    {
        this.descriptionText.setText(item.getDescription());
        this.priceText.setText(String.valueOf(item.getPricePerDay()));
        this.owner.setText(item.getOwner().getStreetName());
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}
