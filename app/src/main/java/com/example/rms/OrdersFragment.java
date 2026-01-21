package com.example.rms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        RecyclerView rvOrders = view.findViewById(R.id.rvOrders);
        
        boolean isAdmin = DataManager.currentUser != null && DataManager.currentUser.isAdmin();
        ArrayList<Order> displayOrders = new ArrayList<>();

        if (isAdmin) {
            // Admin sees all orders (History of all customer orders)
            displayOrders.addAll(DataManager.orders);
        } else {
            // Regular user sees only their orders
            for (Order o : DataManager.orders) {
                if (DataManager.currentUser != null && o.getCustomerUsername().equals(DataManager.currentUser.getUsername())) {
                    displayOrders.add(o);
                }
            }
        }

        OrderAdapter adapter = new OrderAdapter(displayOrders, isAdmin, (position, newStatus) -> {
            Order order = displayOrders.get(position);
            order.setStatus(newStatus);
            
            // Show notification for status change
            NotificationHelper.showNotification(getContext(), "Order Status Updated", 
                "Order " + order.getOrderId() + " is now " + newStatus);

            // Find the order in DataManager.orders and update it there as well if they are different lists
            for (Order o : DataManager.orders) {
                if (o.getOrderId().equals(order.getOrderId())) {
                    o.setStatus(newStatus);
                    break;
                }
            }
            rvOrders.getAdapter().notifyItemChanged(position);
        });
        
        rvOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrders.setAdapter(adapter);

        return view;
    }
}
