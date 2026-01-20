package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ManageMenuActivity extends AppCompatActivity {

    private RecyclerView rvMenu;
    private MenuAdapterAdmin adapter;
    private Button btnAddItem;
    private DatabaseReference mDatabase;
    private ArrayList<MenuItem> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_menu);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("menu");
        menuList = new ArrayList<>();
        rvMenu = findViewById(R.id.rvMenu);
        btnAddItem = findViewById(R.id.btnAddItem);

        adapter = new MenuAdapterAdmin(menuList, new MenuAdapterAdmin.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                Intent intent = new Intent(ManageMenuActivity.this, AddEditMenuActivity.class);
                intent.putExtra("itemId", menuList.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                String id = menuList.get(position).getId();
                mDatabase.child(id).removeValue().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ManageMenuActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        rvMenu.setAdapter(adapter);

        btnAddItem.setOnClickListener(v -> startActivity(new Intent(this, AddEditMenuActivity.class)));

        fetchMenu();
    }

    private void fetchMenu() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                menuList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    MenuItem item = postSnapshot.getValue(MenuItem.class);
                    if (item != null) {
                        item.setId(postSnapshot.getKey());
                        menuList.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ManageMenuActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
