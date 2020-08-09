package com.example.smartcity.View.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartcity.Model.RentalDTO;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.DatesUtilitaries;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RentalRenterPaymentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.labelItemText)
    TextView item;

    @BindView(R.id.dates)
    TextView dates;

    @BindView(R.id.rentalPrice)
    TextView rentalPrice;

    @BindView(R.id.renter)
    TextView renter;

    @BindView(R.id.pay)
    Button pay;


    public RentalRenterPaymentViewHolder(@NonNull View rentalView)
    {
        super(rentalView);
        ButterKnife.bind(this,rentalView);
    }

    public void updateRental(RentalDTO rental)
    {
        this.item.setText(rental.getItemTitle());
        this.dates.setText(DatesUtilitaries.FormattedDate(rental.getDateFrom()) + " - " +  DatesUtilitaries.FormattedDate(rental.getDateTo()));
        this.rentalPrice.setText(String.valueOf(rental.getPaidPrice()));
        this.renter.setText("Demandé par " + rental.getRenterFirstName() + " " + rental.getRenterLastName());
        this.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}
