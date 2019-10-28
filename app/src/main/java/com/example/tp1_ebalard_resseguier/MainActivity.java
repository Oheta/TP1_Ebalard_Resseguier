package com.example.tp1_ebalard_resseguier;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        final Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showFragment(mFragment1);
            }
        });

        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showFragment(mFragment2);
            }
        });
    }

    //Gestion des fragments
    private final Fragment1 mFragment1;
    private final Fragment2 mFragment2;

    {
        mFragment1 = new Fragment1();
        mFragment2 = new Fragment2();
    }
    private void showFragment(final Fragment fragment) {
        if (fragment == null) {
            return;
        }

        // Begin a fragment transaction.
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        // We can also animate the changing of fragment.
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        // Replace current fragment by the new one.
        ft.replace(R.id.nav_view, fragment);
        // Null on the back stack to return on the previous fragment when user
        // press on back button.
        ft.addToBackStack(null);

        // Commit changes.
        ft.commit();
    }
}
