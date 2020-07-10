package com.example.smartcity.View.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartcity.Model.RentalDTO;
import com.example.smartcity.R;

import java.util.List;


public class RentalOwnerValidationAdapter extends RecyclerView.Adapter<RentalOwnerValidationViewHolder> {

    private List<RentalDTO> rentals;

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

        holder.updateRental(this.rentals.get(position));
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
