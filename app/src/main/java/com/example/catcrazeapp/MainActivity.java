package com.example.catcrazeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.catcrazeapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new SearchRecyclerFragment();
        swapFragment(fragment);


        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_search) {
                    Fragment fragment = new SearchRecyclerFragment();
                    swapFragment(fragment);//swap fragment to the search fragment
                    return true;

                } else if (menuItem.getItemId() == R.id.nav_fav) {
                    Fragment fragment = new FavouriteRecyclerFragment();
                    swapFragment(fragment); //swap fragment to favourite fragment
                    return true;
                }
                return false;
            }
        });

    }
    /**
     * Helper method to change the fragment displayed in the activity. We put this here so we don't
     * have to repeat the code every time we want to saw
     * @param fragment: instance of the fragment to go into the slot
     */
    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }
}
