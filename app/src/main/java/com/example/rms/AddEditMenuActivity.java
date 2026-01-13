package com.example.rms;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class AddEditMenuActivity extends AppCompatActivity {

    private TextInputEditText etName, etPrice, etCategory;
    private Button btnSave;
    private TextView tvTitle;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_menu);

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etCategory = findViewById(R.id.etCategory);
        btnSave = findViewById(R.id.btnSave);
        tvTitle = findViewById(R.id.tvTitle);

        if (getIntent().hasExtra("position")) {
            position = getIntent().getIntExtra("position", -1);
            MenuItem item = DataManager.menuItems.get(position);
            etName.setText(item.getName());
            etPrice.setText(String.valueOf(item.getPrice()));
            etCategory.setText(item.getCategory());
            tvTitle.setText("Edit Menu Item");
        }

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String priceStr = etPrice.getText().toString().trim();
            String category = etCategory.getText().toString().trim();

            if (name.isEmpty() || priceStr.isEmpty() || category.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double price = Double.parseDouble(priceStr);

            if (position == -1) {
                // Add new item - use a default logo for new items
                DataManager.menuItems.add(new MenuItem(name, price, R.drawable.ic_restaurant_logo, category));
                Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
            } else {
                // Update existing
                MenuItem item = DataManager.menuItems.get(position);
                item.setName(name);
                item.setPrice(price);
                item.setCategory(category);
                Toast.makeText(this, "Item updated successfully", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}
