package com.example.smartcity.View.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;
import com.cloudinary.utils.ObjectUtils;
import com.example.smartcity.DataAccess.ViewModel.CategoryViewModel;
import com.example.smartcity.DataAccess.ViewModel.ItemViewModel;
import com.example.smartcity.DataAccess.ViewModel.PictureViewModel;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.ItemCategory;
import com.example.smartcity.Model.ItemResponseAPI;
import com.example.smartcity.Model.Picture;
import com.example.smartcity.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddItemFragment extends Fragment {

    @BindView(R.id.categorySpinner)
    Spinner categoriesList;
    @BindView(R.id.addItemName)
    EditText name;
    @BindView(R.id.addItemDescription)
    EditText description;
    @BindView(R.id.addItemPrice)
    EditText price;
    @BindView(R.id.addItemConfirmation)
    Button confirmation;
    @BindView(R.id.addPicture)
    Button addPicture;
    @BindView(R.id.picture)
    ImageView picture;
    Bitmap photo;

    private ItemViewModel itemModel;
    private CategoryViewModel categoryModel;
    private PictureViewModel pictureModel;

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        categoryModel = new CategoryViewModel(getContext());
        categoryModel.getCategories().observe(this,categories -> {
            categoriesList.setAdapter(new ArrayAdapter<ItemCategory>(getContext(), android.R.layout.simple_spinner_dropdown_item,categories));
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additem,container,false);
        ButterKnife.bind(this, view);
        confirmation.setOnClickListener(confirmationListener);
        addPicture.setOnClickListener(gallery);
        return view;
    }

    private View.OnClickListener confirmationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Item item = new Item();
            Picture picture = new Picture();
            itemModel = new ItemViewModel(getContext());
            pictureModel = new PictureViewModel(getContext());


            item.setName(name.getText().toString());
            item.setDescription(description.getText().toString());
            item.setVisible(true);
            item.setPricePerDay(Double.valueOf(price.getText().toString()));
            ItemCategory itemCat = (ItemCategory) categoriesList.getSelectedItem();
            item.setItemCategory(itemCat.getCategoryId());

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            /*
            Map config = new HashMap();
            config.put("cloud_name", "locapp");
            config.put("api_key", "731592778186861");
            config.put("api_secret", "tW7qsDmldy7IP-aLhxj6XWLnh-A");
            MediaManager.init(getContext(), config);
            String idUpload = MediaManager.get().upload(byteArray).dispatch(getContext());
            */

            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "locapp",
                    "api_key", "731592778186861",
                    "api_secret", "tW7qsDmldy7IP-aLhxj6XWLnh-A"));


            Thread threadCloudinary = new Thread(new Runnable() {
                public void run() {
                    try
                    {
                        Map uploadResult = cloudinary.uploader().upload(byteArray,
                                ObjectUtils.asMap("folder", "Locapp/"));
                        String idCloudinary = (String) uploadResult.get("public_id");
                        picture.setPath("https://res.cloudinary.com/locapp/image/upload/v1576324417/"+idCloudinary);
                    }
                    catch (IOException e )
                    {
                        Log.d("image", "problème upload " + e.getMessage());
                    }
                }
            });

            threadCloudinary.start();
            try {
                threadCloudinary.join();
                itemModel.postItem(item).observe(getViewLifecycleOwner(), item1 -> {
                    picture.setItem(item1.getItemId());
                    pictureModel.postPicture(picture);
                });
            }
            catch (Exception e)
            {

            }



        }
    };

    private View.OnClickListener gallery = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Selectionner des photos"), 1);

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if(requestCode == 1){
            //String requestId = MediaManager.get().upload("test.png").dispatch();
            try {
                photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                picture.setImageBitmap(photo);
                /*
                Map config = new HashMap();
                config.put("cloudinary://731592778186861:tW7qsDmldy7IP-aLhxj6XWLnh-A@locapp", "cloudinary://@locapp");
                MediaManager.init(getContext(), config);
                String requestId = MediaManager.get().upload(data.getData().getPath()).dispatch();

                 */
            }
            catch(IOException e) {
                Log.i("ici", "aie");
            }
        }
    }



}
