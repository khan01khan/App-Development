package com.example.rms;

import android.os.Bundle;
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

public class UserManagementActivity extends AppCompatActivity {

    private RecyclerView rvUsers;
    private UserAdapter adapter;
    private ArrayList<User> userList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        rvUsers = findViewById(R.id.rvUsers);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        userList = new ArrayList<>();

        adapter = new UserAdapter(userList, position -> {
            User user = userList.get(position);
            String newStatus = user.getStatus().equals("ACTIVE") ? "INACTIVE" : "ACTIVE";
            
            // Update in Firebase
            mDatabase.child(user.getUid()).child("status").setValue(newStatus)
                .addOnSuccessListener(aVoid -> {
                    user.setStatus(newStatus);
                    adapter.notifyItemChanged(position);
                })
                .addOnFailureListener(e -> Toast.makeText(UserManagementActivity.this, "Failed to update: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(adapter);

        fetchUsers();
    }

    private void fetchUsers() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user != null) {
                        userList.add(user);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserManagementActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
