package com.example.rms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class CustomerMenuFragment extends Fragment {

    private RecyclerView rvMenu;
    private MenuAdapterCustomer adapter;
    private ArrayList<MenuItem> combinedMenuList;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_menu, container, false);

        rvMenu = view.findViewById(R.id.rvMenu);
        combinedMenuList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("menu");

        // Step 1: Add existing hardcoded items first
        combinedMenuList.addAll(DataManager.menuItems);

        adapter = new MenuAdapterCustomer(combinedMenuList);
        rvMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMenu.setAdapter(adapter);

        // Step 2: Fetch and append new items from Firebase
        fetchNewItemsFromFirebase();

        return view;
    }

    private void fetchNewItemsFromFirebase() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Clear and re-add local items to avoid duplicates on every update
                combinedMenuList.clear();
                combinedMenuList.addAll(DataManager.menuItems);

                // Append items from Firebase
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    MenuItem item = postSnapshot.getValue(MenuItem.class);
                    if (item != null) {
                        item.setId(postSnapshot.getKey());
                        combinedMenuList.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
