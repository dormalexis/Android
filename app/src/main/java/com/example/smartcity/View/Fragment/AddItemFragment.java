
package com.example.smartcity.View.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcity.DataAccess.ViewModel.CategoryViewModel;
import com.example.smartcity.DataAccess.ViewModel.ItemViewModel;
import com.example.smartcity.DataAccess.ViewModel.PictureViewModel;
import com.example.smartcity.Exception.ImageException;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.Model.Picture;
import com.example.smartcity.R;
import com.example.smartcity.View.DisplayToast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddItemFragment extends Fragment {

    @BindView(R.id.categorySpinner)
    Spinner categoriesList;
    @BindView(R.id.addItemName)
    TextInputLayout name;
    @BindView(R.id.addItemDescription)
    TextInputLayout description;
    @BindView(R.id.addItemPrice)
    TextInputLayout price;
    @BindView(R.id.addItemConfirmation)
    Button confirmation;
    @BindView(R.id.addPicture)
    Button addPicture;
    @BindView(R.id.picture)
    ImageView picture;
    Bitmap photo;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ItemViewModel itemModel;
    private CategoryViewModel categoryModel;
    private PictureViewModel pictureModel;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        categoryModel = new CategoryViewModel(getContext());
        categoryModel.getCategories(getResources().getConfiguration().locale.toString()).observe(this, categories -> {
            if (categories.isErrorDetected()) {
                DisplayToast.display(categories.getErrorCode());
            } else {
                categoriesList.setAdapter(new ArrayAdapter<ItemCategory>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories.getObject()));
            }

        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additem, container, false);
        ButterKnife.bind(this, view);
        confirmation.setOnClickListener(confirmationListener);
        addPicture.setOnClickListener(gallery);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        BottomNavigationView bottomBar = getActivity().findViewById(R.id.bottom_navigation);
        //
        if(bottomBar != null) bottomBar.setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        BottomNavigationView bottomBar = getActivity().findViewById(R.id.bottom_navigation);
        bottomBar.setVisibility(View.VISIBLE);

        // Todo : Change the fact the bottom bar come back to visible after
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("spinnerCategory", categoriesList.getSelectedItemPosition());
        outState.putString("name",name.getEditText().getText().toString());
        outState.putString("description",description.getEditText().getText().toString());
        outState.putString("price",price.getEditText().getText().toString());
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null)
        {
            categoriesList.setSelection(savedInstanceState.getInt("sprinnerCategory"));
            name.getEditText().setText(savedInstanceState.getString("name"));
            description.getEditText().setText(savedInstanceState.getString("description"));
            price.getEditText().setText(savedInstanceState.getString("price"));
        }
    }


    private View.OnClickListener confirmationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Item item = new Item();
            Picture picture = new Picture();
            itemModel = new ItemViewModel(getContext());
            pictureModel = new PictureViewModel(getContext());
            String exceptionMessage = "";

            try {
                item.setTitle(name.getEditText().getText().toString());
            } catch (Exception e) {
                exceptionMessage += e.getMessage() + "\n";
            }

            try {
                item.setPricePerDay(price.getEditText().getText().toString());
            } catch (Exception e) {
                exceptionMessage += e.getMessage() + "\n";
            }

            try {
                item.setDescription(description.getEditText().getText().toString());
            } catch (Exception e) {
                exceptionMessage += e.getMessage() + "\n";
            }

            if (photo == null) {
                exceptionMessage += new ImageException().getMessage();
            }

            if (!exceptionMessage.equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle(R.string.errorAddItem);
                builder.setMessage(exceptionMessage);
                builder.create().show();
            } else {
                item.setVisible(true);
                ItemCategory itemCat = (ItemCategory) categoriesList.getSelectedItem();
                item.setItemCategory(itemCat.getCategory());
                //item.setPicture(photo);
                itemModel.postItem(item).observe(getViewLifecycleOwner(), item1 -> {
                    if (item1.isErrorDetected()) {
                        DisplayToast.display(item1.getErrorCode());
                    } else {
                        DisplayToast.displaySpecific(R.string.postItemOk);
                    }
                });

            }
        }

    };

    private View.OnClickListener gallery = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Selectionner une photo"), 1);

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            try {
                photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                picture.setImageBitmap(photo);

            } catch (IOException e) {
                // TODO : Gérer l'exception
            }
        }
    }
}