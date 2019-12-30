package com.example.smartcity.View.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity.DataAccess.ViewModel.RentalViewModel;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Rental;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.GlideApp;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ItemDetailsFragment extends Fragment {

    @BindView(R.id.titleItemDetailsText)
    TextView titleText;
    @BindView(R.id.detailsItemImage)
    ImageView detailsItemImage;
    @BindView(R.id.descriptionItemText)
    TextView descriptionItemText;
    @BindView(R.id.countImageText)
    TextView countImageText;
    @BindView(R.id.nextImageButton)
    ImageButton nextImageButton;
    @BindView(R.id.previousImageButton)
    ImageButton previousImageButton;



    private Item itemSelected;
    private GregorianCalendar dateFrom;
    private GregorianCalendar dateTo;
    private Rental rental;
    private RentalViewModel rentalViewModel;

    public ItemDetailsFragment(Item itemSelected)
    {
        this.itemSelected = itemSelected;
        dateFrom = new GregorianCalendar();
        dateTo = new GregorianCalendar();
        this.rentalViewModel = new RentalViewModel(getContext());
        this.rental = new Rental();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        ButterKnife.bind(this,view);

        previousImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(true);
            }
        });
        nextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(false);
            }
        });


        displayImage(0);
        changeTextImage(0,itemSelected.getPictures().size());

        titleText.setText(itemSelected.getName());
        descriptionItemText.setText(itemSelected.getDescription());
        return view;
    }


    private void changeImage(Boolean isPrevious)
    {
        int nbImages = itemSelected.getPictures().size();
        int iImage = 0;

        if(isPrevious && iImage != 0)
        {
            iImage--;
        }
        if(!isPrevious && iImage < nbImages)
        {
            iImage++;
        }
        changeTextImage(iImage,nbImages);
        displayImage(iImage);
    }

    private void changeTextImage(int iImage, int nbImage)
    {
        if(iImage != 0)
        {
            countImageText.setText((iImage + 1)  + " / " + nbImage);
        }
        else countImageText.setText((iImage)  + " / " + nbImage);

    }

    private void displayImage(int iImage)
    {
        if(itemSelected.getPictures().size() > 0)
        {
            Context context = getContext();
            RequestOptions requestOptions = new RequestOptions();
            // TODO : Modifier les drawable montrés par défaut en cas de non chargement de l'image
            requestOptions.placeholder(R.drawable.ic_add);
            requestOptions.error(R.drawable.ic_check);

            GlideApp.with(context)
                    .load(itemSelected.getPictures().get(iImage).getPath())
                    .apply(requestOptions)
                    .override(100, 200)
                    .into(detailsItemImage);
        }

    }

}