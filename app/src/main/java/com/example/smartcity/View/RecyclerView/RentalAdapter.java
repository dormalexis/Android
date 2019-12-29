package com.example.smartcity.View.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.DataAccess.Service.RentalService;
import com.example.smartcity.Model.Rental;
import com.example.smartcity.R;

import java.util.List;

public class RentalAdapter extends RecyclerView.Adapter<RentalViewHolder> {

    private List<Rental> rentals;
    private Context context;


    public RentalAdapter(){
    }

    @NonNull
    @Override
    public RentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_rental_details,parent,false);
        return new RentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalViewHolder holder, int position) {
        holder.updateRental(this.rentals.get(position),context);
    }

    @Override
    public int getItemCount() {
        return this.rentals == null ? 0 : this.rentals.size();
    }

    public void setRentals(List<Rental> rentals)
    {
        this.rentals = rentals;
    }

    public interface OnRentalListerner{
        void onRentalClick(int position);
    }
}
