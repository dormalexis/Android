package com.example.smartcity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.smartcity.View.Fragment.AddItemFragment;
import com.example.smartcity.View.Fragment.CheckFragment;
import com.example.smartcity.View.Fragment.HomeFragment;
import com.example.smartcity.View.Fragment.LogInFragment;
import com.example.smartcity.View.Fragment.MyItemsFragment;
import com.example.smartcity.View.Fragment.MyRentalsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO : utilsier bind

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Changer de layout
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

    }

    // Changer de layout
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;
            case R.id.nav_myItems:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyItemsFragment()).commit();
                break;
            case R.id.nav_addItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddItemFragment()).commit();
                break;
            case R.id.nav_check:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CheckFragment()).commit();
                break;
            case R.id.nav_myRentals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyRentalsFragment()).commit();
                break;
        }

        return true;
    }


}
