package com.example.rms;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEditMenuActivity extends AppCompatActivity {

    private TextInputEditText etName, etPrice, etCategory;
    private Button btnSave;
    private TextView tvTitle;
    private String itemId = null;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_menu);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("menu");

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etCategory = findViewById(R.id.etCategory);
        btnSave = findViewById(R.id.btnSave);
        tvTitle = findViewById(R.id.tvTitle);

        if (getIntent().hasExtra("itemId")) {
            itemId = getIntent().getStringExtra("itemId");
            tvTitle.setText("Edit Menu Item");
            loadItemDetails();
        }

        btnSave.setOnClickListener(v -> saveItem());
    }

    private void loadItemDetails() {
        mDatabase.child(itemId).get().addOnSuccessListener(snapshot -> {
            MenuItem item = snapshot.getValue(MenuItem.class);
            if (item != null) {
                etName.setText(item.getName());
                etPrice.setText(String.valueOf(item.getPrice()));
                etCategory.setText(item.getCategory());
            }
        });
    }

    private void saveItem() {
        String name = etName.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();
        String category = etCategory.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty() || category.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        
        if (itemId == null) {
            itemId = mDatabase.push().getKey();
        }

        MenuItem item = new MenuItem(itemId, name, price, R.drawable.ic_restaurant_logo, category);
        mDatabase.child(itemId).setValue(item).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
