package com.example.rms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MenuAdapterCustomer extends RecyclerView.Adapter<MenuAdapterCustomer.ViewHolder> {

    private ArrayList<MenuItem> menuItems;

    public MenuAdapterCustomer(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText("Rs. " + item.getPrice());
        holder.ivImage.setImageResource(item.getImageResource());

        holder.btnAddToCart.setOnClickListener(v -> {
            boolean found = false;
            for (CartItem cartItem : DataManager.cart) {
                if (cartItem.getMenuItem().getName().equals(item.getName())) {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                DataManager.cart.add(new CartItem(item, 1));
            }
            Toast.makeText(v.getContext(), item.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView ivImage;
        Button btnAddToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvFoodName);
            tvPrice = itemView.findViewById(R.id.tvFoodPrice);
            ivImage = itemView.findViewById(R.id.ivFoodImage);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
