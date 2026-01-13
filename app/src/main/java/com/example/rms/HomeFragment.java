package com.example.rms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvPopular;
    private MenuAdapterCustomer adapter;
    private ArrayList<MenuItem> displayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvPopular = view.findViewById(R.id.rvPopular);
        displayList = new ArrayList<>(DataManager.menuItems);
        adapter = new MenuAdapterCustomer(displayList);

        rvPopular.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPopular.setAdapter(adapter);

        setupCategoryButtons(view);

        return view;
    }

    private void setupCategoryButtons(View v) {
        v.findViewById(R.id.catAll).setOnClickListener(view -> updateList("All"));
        v.findViewById(R.id.catFastFood).setOnClickListener(view -> updateList("Fast Food"));
        v.findViewById(R.id.catMain).setOnClickListener(view -> updateList("Main Course"));
        v.findViewById(R.id.catSides).setOnClickListener(view -> updateList("Sides"));
        v.findViewById(R.id.catDessert).setOnClickListener(view -> updateList("Dessert"));
    }

    private void updateList(String category) {
        displayList.clear();
        displayList.addAll(DataManager.getItemsByCategory(category));
        adapter.notifyDataSetChanged();
    }
}
