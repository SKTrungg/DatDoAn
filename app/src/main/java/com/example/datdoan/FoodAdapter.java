package com.example.datdoan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    public interface OnFoodClickListener {
        void onFoodClick(Food food);
    }

    private List<Food> foodList;
    private final OnFoodClickListener listener;

    public FoodAdapter(List<Food> foodList, OnFoodClickListener listener) {
        this.foodList = foodList;
        this.listener = listener;
    }

    public void updateList(List<Food> newList) {
        this.foodList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.bind(food, listener);
    }

    @Override
    public int getItemCount() {
        return foodList != null ? foodList.size() : 0;
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgFood;
        private final TextView tvFoodName;
        private final TextView tvFoodDescription;
        private final TextView tvFoodPrice;
        private final TextView btnViewDetail;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvFoodDescription = itemView.findViewById(R.id.tvFoodDescription);
            tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice);
            btnViewDetail = itemView.findViewById(R.id.btnViewDetail);
        }

        public void bind(Food food, OnFoodClickListener listener) {
            imgFood.setImageResource(food.getImageResId());
            tvFoodName.setText(food.getName());
            tvFoodDescription.setText(food.getDescription());
            tvFoodPrice.setText(PriceUtils.formatPrice(food.getPrice()));

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onFoodClick(food);
                }
            });

            btnViewDetail.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onFoodClick(food);
                }
            });
        }
    }
}