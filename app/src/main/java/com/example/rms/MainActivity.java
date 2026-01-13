package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Setup Drawer Toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Check if user is logged in
        if (DataManager.currentUser == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // Setup Nav Header
        setupNavHeader();

        // Load correct menu
        if (DataManager.currentUser.isAdmin()) {
            navigationView.inflateMenu(R.menu.admin_drawer);
            if (savedInstanceState == null) {
                loadFragment(new AdminDashboardFragment());
                navigationView.setCheckedItem(R.id.nav_admin_dash);
            }
        } else {
            navigationView.inflateMenu(R.menu.customer_drawer);
            if (savedInstanceState == null) {
                loadFragment(new HomeFragment());
                navigationView.setCheckedItem(R.id.nav_home);
            }
        }
    }

    private void setupNavHeader() {
        View headerView = navigationView.getHeaderView(0);
        TextView tvUsername = headerView.findViewById(R.id.tvNavUsername);
        TextView tvRole = headerView.findViewById(R.id.tvNavRole);

        tvUsername.setText(DataManager.currentUser.getUsername());
        tvRole.setText(DataManager.currentUser.isAdmin() ? "Administrator" : "Valued Customer");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            DataManager.currentUser = null;
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else if (id == R.id.nav_home) {
            loadFragment(new HomeFragment());
        } else if (id == R.id.nav_menu) {
            loadFragment(new CustomerMenuFragment());
        } else if (id == R.id.nav_cart) {
            startActivity(new Intent(this, CartActivity.class));
        } else if (id == R.id.nav_orders) {
            loadFragment(new OrdersFragment());
        } else if (id == R.id.nav_admin_dash) {
            loadFragment(new AdminDashboardFragment());
        } else if (id == R.id.nav_manage_menu) {
            startActivity(new Intent(this, ManageMenuActivity.class));
        } else if (id == R.id.nav_manage_users) {
            startActivity(new Intent(this, UserManagementActivity.class));
        } else if (id == R.id.nav_manage_orders) {
            loadFragment(new AdminOrdersFragment());
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
