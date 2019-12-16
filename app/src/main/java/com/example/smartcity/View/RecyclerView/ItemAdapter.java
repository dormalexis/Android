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

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Item> items;
    private OnItemListener onItemListener;
    private Context context;

    public ItemAdapter(List<Item> items,OnItemListener onItemListener) {
        this.items = items;
        this.onItemListener = onItemListener;
    }
    public ItemAdapter(OnItemListener onItemListener) {this.onItemListener = onItemListener;}
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item, parent, false);
        return new ItemViewHolder(view,onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.updateAnnonces(this.items.get(position), context);
    }

    @Override
    public int getItemCount() {
        return this.items == null ? 0 : this.items.size();
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }

}
