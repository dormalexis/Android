package com.example.smartcity.View.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.Model.Item;
import com.example.smartcity.R;
import com.example.smartcity.View.RecyclerView.ItemViewHolder;

import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemViewHolder> {

    private List<Item> items;
    private Context context;

    public MyItemAdapter() {}
    @NonNull
    @Override
    public MyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_myitem, parent, false);
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemViewHolder holder, int position) {
        holder.updateAnnonces(this.items.get(position), context);
    }

    @Override
    public int getItemCount() {
        return this.items == null ? 0 : this.items.size();
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
