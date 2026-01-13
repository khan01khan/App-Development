package com.example.rms;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private ArrayList<Order> orders;
    private boolean isAdmin;
    private OnOrderActionListener listener;

    public interface OnOrderActionListener {
        void onAction(int position, String newStatus);
    }

    public OrderAdapter(ArrayList<Order> orders, boolean isAdmin, OnOrderActionListener listener) {
        this.orders = orders;
        this.isAdmin = isAdmin;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.tvOrderId.setText("Order #" + order.getOrderId());
        holder.tvCustomer.setText("User: " + order.getCustomerUsername());
        holder.tvTotal.setText("Total: Rs. " + order.getTotalAmount());
        holder.tvStatus.setText(order.getStatus());
        holder.tvDate.setText(order.getDate());

        // Status coloring
        if (order.getStatus().equals("PENDING")) holder.tvStatus.setTextColor(Color.RED);
        else if (order.getStatus().equals("APPROVED")) holder.tvStatus.setTextColor(Color.BLUE);
        else if (order.getStatus().equals("DELIVERED")) holder.tvStatus.setTextColor(Color.GREEN);

        if (isAdmin) {
            holder.btnAction.setVisibility(View.VISIBLE);
            if (order.getStatus().equals("PENDING")) {
                holder.btnAction.setText("Approve");
                holder.btnAction.setOnClickListener(v -> listener.onAction(position, "APPROVED"));
            } else if (order.getStatus().equals("APPROVED")) {
                holder.btnAction.setText("Deliver");
                holder.btnAction.setOnClickListener(v -> listener.onAction(position, "DELIVERED"));
            } else {
                holder.btnAction.setVisibility(View.GONE);
            }
        } else {
            holder.btnAction.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderId, tvCustomer, tvTotal, tvStatus, tvDate;
        Button btnAction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvCustomer = itemView.findViewById(R.id.tvOrderCustomer);
            tvTotal = itemView.findViewById(R.id.tvOrderTotal);
            tvStatus = itemView.findViewById(R.id.tvOrderStatus);
            tvDate = itemView.findViewById(R.id.tvOrderDate);
            btnAction = itemView.findViewById(R.id.btnOrderAction);
        }
    }
}
