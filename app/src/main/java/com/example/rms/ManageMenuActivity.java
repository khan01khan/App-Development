package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ManageMenuActivity extends AppCompatActivity {

    private RecyclerView rvMenu;
    private MenuAdapterAdmin adapter;
    private Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_menu);

        rvMenu = findViewById(R.id.rvMenu);
        btnAddItem = findViewById(R.id.btnAddItem);

        setupRecyclerView();

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageMenuActivity.this, AddEditMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new MenuAdapterAdmin(DataManager.menuItems, new MenuAdapterAdmin.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                Intent intent = new Intent(ManageMenuActivity.this, AddEditMenuActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                DataManager.menuItems.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        rvMenu.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
