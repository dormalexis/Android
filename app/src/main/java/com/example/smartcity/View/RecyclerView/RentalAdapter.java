package com.example.smartcity.View.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartcity.Model.RentalDTO;
import com.example.smartcity.R;

import java.util.List;


public class RentalAdapter extends RecyclerView.Adapter<RentalViewHolder> {

    private List<RentalDTO> rentals;

    public RentalAdapter(){

    }

    @NonNull
    @Override
    public RentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rental_infos,parent,false);
        return new RentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalViewHolder holder, int position) {

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
