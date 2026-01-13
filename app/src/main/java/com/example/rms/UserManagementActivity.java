package com.example.rms;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserManagementActivity extends AppCompatActivity {

    private RecyclerView rvUsers;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        rvUsers = findViewById(R.id.rvUsers);
        
        adapter = new UserAdapter(DataManager.users, new UserAdapter.OnUserToggleListener() {
            @Override
            public void onToggle(int position) {
                User user = DataManager.users.get(position);
                if (user.getStatus().equals("ACTIVE")) {
                    user.setStatus("INACTIVE");
                } else {
                    user.setStatus("ACTIVE");
                }
                adapter.notifyItemChanged(position);
            }
        });

        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(adapter);
    }
}
