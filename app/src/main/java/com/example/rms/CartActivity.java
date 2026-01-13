package com.example.rms;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvCart;
    private CartAdapter adapter;
    private TextView tvTotalBill;
    private Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCart = findViewById(R.id.rvCart);
        tvTotalBill = findViewById(R.id.tvTotalBill);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        setupRecyclerView();
        updateTotalBill();

        btnPlaceOrder.setOnClickListener(v -> {
            if (DataManager.cart.isEmpty()) {
                Toast.makeText(CartActivity.this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            } else {
                // Create New Order
                String orderId = "ORD" + System.currentTimeMillis();
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                double total = calculateTotal();
                
                Order newOrder = new Order(orderId, DataManager.currentUser.getUsername(), 
                        DataManager.cart, total, date);
                DataManager.orders.add(newOrder);

                Toast.makeText(CartActivity.this, "Order Placed! ID: " + orderId, Toast.LENGTH_LONG).show();
                DataManager.cart.clear();
                finish();
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new CartAdapter(DataManager.cart, position -> {
            DataManager.cart.remove(position);
            adapter.notifyItemRemoved(position);
            updateTotalBill();
        });
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(adapter);
    }

    private void updateTotalBill() {
        tvTotalBill.setText("Rs. " + calculateTotal());
    }

    private double calculateTotal() {
        double total = 0;
        for (CartItem item : DataManager.cart) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
