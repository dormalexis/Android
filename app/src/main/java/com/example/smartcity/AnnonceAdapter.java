package com.example.smartcity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AnnonceAdapter extends RecyclerView.Adapter<AnnonceViewHolder> {

    private ArrayList<Annonce> annonces;


    public AnnonceAdapter(ArrayList<Annonce> annonces) { this.annonces = annonces;}
    @NonNull
    @Override
    public AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item, parent, false);
        return new AnnonceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position) {
        holder.updateAnnonces(this.annonces.get(position));
    }

    @Override
    public int getItemCount() {
        return this.annonces == null ? 0 : this.annonces.size();
    }


}
