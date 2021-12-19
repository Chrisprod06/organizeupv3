package com.example.organizeupv3.activities;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizeupv3.R;
import com.example.organizeupv3.SQL.DBHelper;
import com.example.organizeupv3.adapters.PlansRecyclerAdapter;
import com.example.organizeupv3.models.Plan;

import java.util.ArrayList;
import java.util.List;

public class PlansListActivity extends AppCompatActivity {

    private AppCompatActivity activity = PlansListActivity.this;
    private RecyclerView recyclerViewPlans;
    private List<Plan> listPlans;
    private PlansRecyclerAdapter plansRecyclerAdapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        getSupportActionBar().setTitle("");
        initializeViews();
        initializeObjects();
    }

    private void initializeViews(){
        recyclerViewPlans = findViewById(R.id.rvPlans);
    }

    private void initializeObjects(){
        listPlans = new ArrayList<>();
        plansRecyclerAdapter = new PlansRecyclerAdapter(listPlans);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPlans.setLayoutManager(mLayoutManager);
        recyclerViewPlans.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPlans.setHasFixedSize(true);
        recyclerViewPlans.setAdapter(plansRecyclerAdapter);
        dbHelper = new DBHelper(activity);
        getDataFromSQLite();
    }

    @SuppressLint("StaticFieldLeak")
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listPlans.clear();
                listPlans.addAll(dbHelper.getAllPlans());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                plansRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();

    }
}
