package com.polistudios.lacocinamagica;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseApp;
import com.polistudios.lacocinamagica.database.AppDatabase;
import com.polistudios.lacocinamagica.database.DatabaseCon;
import com.polistudios.lacocinamagica.database.entity.Category;
import com.polistudios.lacocinamagica.databinding.ActivityMainBinding;
import com.polistudios.lacocinamagica.lib.BCrypt;
import com.polistudios.lacocinamagica.models.ListItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        try {
            AppDatabase db = new DatabaseCon().instance(getApplicationContext());
            db.categoryDAO().deleteAll();

            Category c = new Category("Desayuno"); c.id = 0;
            db.categoryDAO().insert(c);
        } catch (Exception e) {
            Log.e("DATABASE", e.toString());
        }

        try {
            AppDatabase db = new DatabaseCon().instance(getApplicationContext());
            Category c = new Category("Comida"); c.id = 1;
            db.categoryDAO().insert(c);

            c = new Category("Cena"); c.id = 2;
            db.categoryDAO().insert(c);
        } catch (Exception e) {
            Log.e("DATABASE", e.toString());
        }

        try {
            AppDatabase db = new DatabaseCon().instance(getApplicationContext());
            Category c = new Category("Cena"); c.id = 2;
            db.categoryDAO().insert(c);
        } catch (Exception e) {
            Log.e("DATABASE", e.toString());
        }


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

        FirebaseApp.initializeApp(this);
        Log.d("PASSWORD GENERATE", BCrypt.hashpw("Miguel1128#22", BCrypt.gensalt(12)));
    }
}