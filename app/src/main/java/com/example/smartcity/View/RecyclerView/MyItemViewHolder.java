package com.example.smartcity.View.RecyclerView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.smartcity.MainActivity;
import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.View.Fragment.HomeFragment;
import com.example.smartcity.View.Fragment.ItemDetailsFragment;
import com.example.smartcity.View.Fragment.ProfileFragment;
import com.example.smartcity.View.Fragment.UpdateItemFragment;
import com.google.firebase.database.collection.LLRBNode;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.smartcity.Utilitaries.App.getContext;

public class MyItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title) TextView title;
    @BindView(R.id.price) TextView priceText;
    @BindView(R.id.visibility) TextView isVisible;
    @BindView(R.id.viewPagerItem) ViewPager pictures;

    public MyItemViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateAnnonces(Item item, Context context)
    {
        this.title.setText(item.getTitle());
        this.priceText.setText(context.getString(R.string.dailyPrice) + " - " + item.getPricePerDay() + " €");

        if(item.getVisible() != null)
        {
            if(!item.getVisible())
            {
                this.isVisible.setText(context.getString(R.string.hidden));
                this.isVisible.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF5252")));
            }

            else {
                this.isVisible.setText(context.getString(R.string.isVisible));
                this.isVisible.setTextColor(ColorStateList.valueOf(Color.parseColor("#95DA0A")));
            }

        }
        ImageAdapter adapterView = new ImageAdapter(item);
        pictures.setAdapter(adapterView);
    }

}