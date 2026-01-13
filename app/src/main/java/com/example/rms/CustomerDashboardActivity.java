package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerDashboardActivity extends AppCompatActivity {

    private RecyclerView rvMenu;
    private MenuAdapterCustomer adapter;
    private Button btnViewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        rvMenu = findViewById(R.id.rvCustomerMenu);
        btnViewCart = findViewById(R.id.btnViewCart);

        adapter = new MenuAdapterCustomer(DataManager.menuItems);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        rvMenu.setAdapter(adapter);

        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDashboardActivity.this, CartActivity.class));
            }
        });
    }
}
