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
        
        ArrayList<Order> userOrders = new ArrayList<>();
        for (Order o : DataManager.orders) {
            if (o.getCustomerUsername().equals(DataManager.currentUser.getUsername())) {
                userOrders.add(o);
            }
        }

        OrderAdapter adapter = new OrderAdapter(userOrders, false, null);
        rvOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrders.setAdapter(adapter);

        return view;
    }
}
