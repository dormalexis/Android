package com.example.smartcity.View.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ViewUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.Model.Rental;
import com.example.smartcity.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RentalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

   @BindView(R.id.labelItemText)
    TextView labelItemText;
   @BindView(R.id.isValided)
    TextView isValidedText;

   private RentalAdapter.OnRentalListerner onRentalListerner;

   public RentalViewHolder(@NonNull View rentalView, RentalAdapter.OnRentalListerner onRentalListerner)
   {
       super(rentalView);
       ButterKnife.bind(this,rentalView);
       this.onRentalListerner = onRentalListerner;
       rentalView.setOnClickListener(this);
   }

   public void updateRental(Rental rental, Context context)
   {
       this.labelItemText.setText(rental.getItem());
       if(rental.isValid() ==null)
       {
            isValidedText.setText("En cours de validation...");
       }
       else
       {
           isValidedText.setText(rental.isValid() ? "Validé !" : "Annulé");
       }
   }


    @Override
    public void onClick(View v) {
        onRentalListerner.onRentalClick(getAdapterPosition());
    }
}
