package com.example.rms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<CartItem> cartItems;
    private OnCartUpdateListener listener;

    public interface OnCartUpdateListener {
        void onRemove(int position);
    }

    public CartAdapter(ArrayList<CartItem> cartItems, OnCartUpdateListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.tvName.setText(item.getMenuItem().getName());
        holder.tvPriceQty.setText("Rs. " + item.getMenuItem().getPrice() + " x " + item.getQuantity());
        holder.tvTotal.setText("Rs. " + item.getTotalPrice());
        holder.ivImage.setImageResource(item.getMenuItem().getImageResource());

        holder.btnRemove.setOnClickListener(v -> listener.onRemove(position));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPriceQty, tvTotal;
        ImageView ivImage;
        ImageButton btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCartItemName);
            tvPriceQty = itemView.findViewById(R.id.tvCartItemPrice);
            tvTotal = itemView.findViewById(R.id.tvCartItemTotal);
            ivImage = itemView.findViewById(R.id.ivCartItemImage);
            btnRemove = itemView.findViewById(R.id.btnRemoveCartItem);
        }
    }
}
