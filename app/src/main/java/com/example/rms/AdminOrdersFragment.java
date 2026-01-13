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

public class AdminOrdersFragment extends Fragment {

    private OrderAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        RecyclerView rvOrders = view.findViewById(R.id.rvOrders);
        
        adapter = new OrderAdapter(DataManager.orders, true, new OrderAdapter.OnOrderActionListener() {
            @Override
            public void onAction(int position, String newStatus) {
                DataManager.orders.get(position).setStatus(newStatus);
                adapter.notifyItemChanged(position);
            }
        });

        rvOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrders.setAdapter(adapter);

        return view;
    }
}
