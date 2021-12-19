package com.example.organizeupv3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.organizeupv3.models.User;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organizeupv3.databinding.ActivityNavigationBinding;

import java.util.List;

public class NavigationActivity extends AppCompatActivity {
    private User user;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent in = getIntent();
        user = (User) in.getSerializableExtra("UserDetails");
        if(binding.navView.getMenu().findItem(R.id.nav_rate).isChecked()){
            binding.appBarNavigation.fab.hide();
        }else if (binding.navView.getMenu().findItem(R.id.nav_places).isChecked()
                || binding.navView.getMenu().findItem(R.id.nav_plans).isChecked()
                || binding.navView.getMenu().findItem(R.id.nav_groups).isChecked()){
            binding.appBarNavigation.fab.show();
        }
        setSupportActionBar(binding.appBarNavigation.toolbar);
        binding.appBarNavigation.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Based on the selected navigation menu item create a different intent
                if (binding.navView.getMenu().findItem(R.id.nav_plans).isChecked()
                        || binding.navView.getMenu().findItem(R.id.nav_home).isChecked()) {
                    Intent inCreatePlan = new Intent(getApplicationContext(), CreatePlanActivity.class);
                    inCreatePlan.putExtra("userDetails", user);
                    startActivity(inCreatePlan);
                } else if (binding.navView.getMenu().findItem(R.id.nav_groups).isChecked()) {
                    Intent inCreatePlan = new Intent(getApplicationContext(), CreateGroupActivity.class);
                    inCreatePlan.putExtra("userDetails", user);
                    startActivity(inCreatePlan);
                } else if (binding.navView.getMenu().findItem(R.id.nav_places).isChecked()) {
                    Intent inCreatePlace = new Intent(getApplicationContext(), CreatePlaceActivity.class);
                    inCreatePlace.putExtra("userDetails", user);
                    startActivity(inCreatePlace);
                }

            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_groups, R.id.nav_plans, R.id.nav_about, R.id.nav_rate, R.id.nav_places)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        MenuItem itemModify = menu.findItem(R.id.action_modify);
        MenuItem itemDelete = menu.findItem(R.id.action_delete);
        if(binding.navView.getMenu().findItem(R.id.nav_home).isChecked()){
            itemModify.setVisible(false);
            itemDelete.setVisible(false);
        }else if (binding.navView.getMenu().findItem(R.id.nav_places).isChecked()
                || binding.navView.getMenu().findItem(R.id.nav_plans).isChecked()
                || binding.navView.getMenu().findItem(R.id.nav_groups).isChecked()){
            itemModify.setVisible(true);
            itemDelete.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent inSettings = new Intent(getApplicationContext(), SettingsActivity.class);
            inSettings.putExtra("userDetails", user);
            startActivity(inSettings);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}