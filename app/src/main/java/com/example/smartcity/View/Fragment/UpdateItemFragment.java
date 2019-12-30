package com.example.smartcity.View.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.example.smartcity.DataAccess.ViewModel.CategoryViewModel;
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
    @BindView(R.id.deleteItemButton)
    Button deleteItemButton;

    private Bitmap photo;
    private Item itemSelected;
    private ItemViewModel itemModel;
    private CategoryViewModel categoryModel;

    public UpdateItemFragment(Item itemSelected)
    {
        this.itemSelected = itemSelected;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemModel = new ItemViewModel(getContext());
        categoryModel = new CategoryViewModel(getContext());
        categoryModel.getCategories().observe(this,categories -> {
            if(categories.isErrorDetected())
            {
                Toast.makeText(getContext(),categories.getErrorCode().getMessage(),Toast.LENGTH_LONG);
            }
            else
            {
                ArrayAdapter<ItemCategory> adapter = new ArrayAdapter<ItemCategory>(getContext(), android.R.layout.simple_spinner_dropdown_item,categories.getObject());
                categoriesList.setAdapter(adapter);

                for (int i = 0; i < categoriesList.getCount(); i++) {
                    ItemCategory categorySelect= (ItemCategory)categoriesList.getItemAtPosition(i);

                    if (categorySelect.getCategoryId().equals(itemSelected.getItemCategory())) {
                        categoriesList.setSelection(i);
                        break;
                    }
                }
            }
        });
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
        deleteItemButton.setOnClickListener(deleteListener);
        return view;
    }


    private View.OnClickListener confirmationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Item item = new Item(getContext());
            item.setItemId(itemSelected.getItemId());
            itemModel = new ItemViewModel(getContext());
            String exceptionMessage = "";
            try {item.setName(name.getText().toString());}
            catch (Exception e) { exceptionMessage += e.getMessage()+ "\n";}
            try {item.setDescription(description.getText().toString());}
            catch (Exception e) { exceptionMessage += e.getMessage()+ "\n";}
            try {item.setPricePerDay(price.getText().toString());}
            catch (Exception e) { exceptionMessage += e.getMessage()+ "\n";}

            if(exceptionMessage != "")
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle(R.string.errorRegisterFormTitle);
                builder.setMessage(exceptionMessage);
                builder.create().show();
            }

            else {
                item.setVisible(isVisible.isChecked());
                ItemCategory itemCat = (ItemCategory) categoriesList.getSelectedItem();
                item.setItemCategory(itemCat.getCategoryId());

                //item.setOwner(1);
                //ItemCategory itemCat = (ItemCategory) categoriesList.getSelectedItem();
                //ItemCategory itemCat = (ItemCategory) categoriesList.getSelectedItem();
                //item.setItemCategory(itemCat.getCategoryId());
                //item.setItemCategory(itemSelected.getItemCategory());
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                itemModel.updateItem(item).observe(getViewLifecycleOwner(), itemUpdate -> {
                    if (itemUpdate.isErrorDetected()) {
                        Toast.makeText(getContext(), itemUpdate.getErrorCode().getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), R.string.UpdateItemOk, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    };

    private View.OnClickListener deleteListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            itemModel.deleteItem(itemSelected.getItemId()).observe(getViewLifecycleOwner(),itemDelete ->{
                if(itemDelete.isErrorDetected())
                {
                    Toast.makeText(getContext(),itemDelete.getErrorCode().getMessage(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getContext(),R.string.DeleteItemOk,Toast.LENGTH_LONG).show();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,new MyItemsFragment());
                    transaction.addToBackStack(new HomeFragment().getClass().getName());
                    transaction.commit();
                }
            });


        }
    };
}
