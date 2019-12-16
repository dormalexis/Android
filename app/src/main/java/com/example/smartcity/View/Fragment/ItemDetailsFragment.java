package com.example.smartcity.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity.Model.Item;
import com.example.smartcity.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailsFragment extends Fragment {

    private Item itemSelected;

    @BindView(R.id.titleItemDetailsText)
    TextView titleText;
    @BindView(R.id.detailsItemImage)
    ImageView detailsItemImage;
    @BindView(R.id.descriptionItemText)
    TextView descriptionItemText;

    public ItemDetailsFragment(Item itemSelected)
    {
        this.itemSelected = itemSelected;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        ButterKnife.bind(this,view);

        titleText.setText(itemSelected.getDescription());
        //detailsItemImage.setImageURI(itemSelected.getPictures().get(0).getPath());

        return view;
    }

}
