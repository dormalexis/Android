package com.example.smartcity.View.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.DataAccess.ViewModel.RentalViewModel;
import com.example.smartcity.Model.Rental;
import com.example.smartcity.Model.RentalDTO;
import com.example.smartcity.R;
import com.example.smartcity.Utilitaries.DatesUtilitaries;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import butterknife.BindView;


public class RentalOwnerValidationAdapter extends RecyclerView.Adapter<RentalOwnerValidationViewHolder> {

    private List<RentalDTO> rentals;
    @BindView(R.id.noRentalsWaitingForValidation)
    MaterialCardView noRentalsWaitingForValidation;

    public RentalOwnerValidationAdapter(){

    }

    @NonNull
    @Override
    public RentalOwnerValidationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rental_owner_validation,parent,false);
        return new RentalOwnerValidationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalOwnerValidationViewHolder holder, int position) {
        RentalDTO rental = this.rentals.get(position);
        holder.item.setText(rental.getItemTitle());
        holder.dates.setText(DatesUtilitaries.FormattedDate(rental.getDateFrom()) + " - " +  DatesUtilitaries.FormattedDate(rental.getDateTo()));
        holder.rentalPrice.setText(String.valueOf(rental.getPaidPrice()));
        holder.renter.setText(rental.getRenterFirstName() + " " + rental.getRenterLastName());
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RentalViewModel().validRental(rental.getRentalId(), true);
                rentals.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();

            }
        });
        holder.deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RentalViewModel().validRental(rental.getRentalId(), false);
                rentals.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.rentals == null ? 0 : this.rentals.size();
    }

    public void setRentals(List<RentalDTO> rentals)
    {
        this.rentals = rentals;
    }

}
