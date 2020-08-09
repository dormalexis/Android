package com.example.smartcity.View.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity.DataAccess.ViewModel.RentalViewModel;
import com.example.smartcity.Model.Item;
import com.example.smartcity.Model.Rental;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.DatesUtilitaries;
import com.example.smartcity.Utilitaries.GlideApp;
import com.example.smartcity.View.DisplayToast;
import com.example.smartcity.View.RecyclerView.ReviewAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;



public class ConfirmRentalFragment extends Fragment implements ReviewAdapter.OnItemListener {

    @BindView(R.id.rentalTitle)
    TextView rentalTitle;

    @BindView(R.id.pricePerDay)
    TextView pricePerDay;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.startDate)
    TextView start;

    @BindView(R.id.endDate)
    TextView end;

    @BindView(R.id.totalPrice)
    TextView totalPrice;

    @BindView(R.id.bookButton)
    Button bookButton;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Date startDate;
    Date endDate;
    Item item;

    RentalViewModel rentalViewModel = new RentalViewModel();

    public ConfirmRentalFragment(Date start, Date end, Item item) {

        this.startDate = start;
        this.endDate = end;
        this.item = item;
    }

    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirmrental, container, false);
        ButterKnife.bind(this, view);
        rentalTitle.setText(item.getTitle());
        pricePerDay.setText(item.getPricePerDay() + "€ / jour");
        Double price = item.getPricePerDay() * DatesUtilitaries.DaysBetween(startDate, endDate);
        totalPrice.setText(price.toString() + "€");
        start.setText(DatesUtilitaries.FormattedDate(startDate));
        end.setText(DatesUtilitaries.FormattedDate(endDate));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.drawable.ic_check);

        GlideApp.with(getContext())
                .load(item.getPictures().get(0).getPath())
                .apply(requestOptions)
                .into(image);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rentalViewModel.postRental(new Rental(startDate, endDate, item.getItemId(), price)).observe(getViewLifecycleOwner(), rental -> {
                    if (!rental.isErrorDetected()) {
                        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(getContext())
                                .setMessage(R.string.postRentalOk)
                                .setPositiveButton(R.string.agree, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                        transaction.replace(R.id.fragment_container, new HomeFragment());
                                        transaction.commit();
                                    }
                                }
                                );
                        dialogBuilder.create().show();

                    }
                    else {
                        DisplayToast.display(rental.getErrorCode());
                    }

                });
            }});

        return view;

    }



    @Override
    public void onItemClick(int position) {

    }


}


