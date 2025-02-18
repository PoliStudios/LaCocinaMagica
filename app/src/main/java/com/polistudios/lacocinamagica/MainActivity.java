package com.polistudios.lacocinamagica;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.polistudios.lacocinamagica.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        final CalendarFragment calendarFragment = new CalendarFragment();
        final ReelsFragment reelsFragment = new ReelsFragment();
        final HomeFragment homeFragment = new HomeFragment();
        final SearchRecipesFragment recipesFragment = new SearchRecipesFragment();
        final AccountFragment accountFragment = new AccountFragment();

        b.mainBottomNavBar.setOnItemSelectedListener(item -> {
            Fragment f = null;

            if(item.getItemId() == R.id.action_calendar)
                f = calendarFragment;

            if(item.getItemId() == R.id.action_reels)
                f = reelsFragment;

            if(item.getItemId() == R.id.action_home)
                f = homeFragment;

            if(item.getItemId() == R.id.action_recipes)
                f = recipesFragment;

            if(item.getItemId() == R.id.action_account)
                f = accountFragment;


            if(f != null)
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragContainer, f).commit();

            return true;
        });
    }
}