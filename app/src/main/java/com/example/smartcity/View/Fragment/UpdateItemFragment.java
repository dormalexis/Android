package com.example.smartcity.View.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.cloudinary.android.MediaManager;
import com.example.smartcity.DataAccess.ViewModel.ItemViewModel;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UpdateItemFragment extends Fragment {


    @BindView(R.id.updateCategorySpinner)
    Spinner categoriesList;
    @BindView(R.id.updateItemName)
    EditText name;
    @BindView(R.id.updateItemDescription)
    EditText description;
    @BindView(R.id.updatePrice)
    EditText price;
    @BindView(R.id.updateItem)
    Button confirmation;
    @BindView(R.id.updatePictureButton)
    Button addPicture;
    @BindView(R.id.updatePicture)
    ImageView picture;
    @BindView(R.id.updateIsVisible)
    CheckBox isVisible;
    Bitmap photo;
    private Item itemSelected;
    ItemViewModel itemModel;

    public UpdateItemFragment(Item itemSelected)
    {
        this.itemSelected = itemSelected;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_item,container,false);
        ButterKnife.bind(this, view);
        name.setText(itemSelected.getName());
        description.setText(itemSelected.getDescription());
        isVisible.setChecked(itemSelected.getVisible());
        price.setText(itemSelected.getPricePerDay().toString());
        confirmation.setOnClickListener(confirmationListener);
        return view;
    }



    private View.OnClickListener confirmationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Item item = new Item();
            item.setItemId(itemSelected.getItemId());
            itemModel = new ItemViewModel(getContext());
            item.setName(name.getText().toString());
            item.setDescription(description.getText().toString());
            item.setVisible(isVisible.isChecked());
            item.setPricePerDay(Double.valueOf(price.getText().toString()));
            item.setOwner(1);
            //ItemCategory itemCat = (ItemCategory) categoriesList.getSelectedItem();
            //ItemCategory itemCat = (ItemCategory) categoriesList.getSelectedItem();
            //item.setItemCategory(itemCat.getCategoryId());
            item.setItemCategory(itemSelected.getItemCategory());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            itemModel.updateItem(item);
        }
    };
}
