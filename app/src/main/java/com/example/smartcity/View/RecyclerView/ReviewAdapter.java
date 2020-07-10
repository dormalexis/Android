package com.example.smartcity.View.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartcity.Model.Review;
import com.example.smartcity.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private List<Review> reviews;
    private OnItemListener onItemListener;
    private Context context;

    public ReviewAdapter(OnItemListener onItemListener) {this.onItemListener = onItemListener;}
    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.review, parent, false);
        return new ReviewViewHolder(view,onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.updateAnnonces(this.reviews.get(position), context);
    }

    @Override
    public int getItemCount() {
        return this.reviews == null ? 0 : this.reviews.size();
    }

    public void setItems(List<Review> items) {
        this.reviews = items;
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }

}
