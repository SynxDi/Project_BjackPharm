package com.example.project_bjackpharm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.project_bjackpharm.PageAdapter;
import com.example.project_bjackpharm.R;
import com.example.project_bjackpharm.database.DatabaseHelper;
import com.example.project_bjackpharm.database.Database_Medicine;
import com.example.project_bjackpharm.database.Database_Transaction;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    public static Integer userIds;
    Button logOutBtn ;
    Button aboutUsBtn;
    public static ArrayList<Database_Transaction> transaksi = new ArrayList<>();
    ArrayList<Database_Medicine> Medicine = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getIntent().getExtras();
// =========================================//
        //UI Component
        String userIdPosition = getIntent().getStringExtra("userIdposition");
//        Log.e("ABCDE", userIdPosition);
//        int Id_User_now = userIdPosition != null ? Integer.parseInt(userIdPosition) : 0;
        userIds = userIdPosition != null ? Integer.parseInt(userIdPosition) : 0;;
//        userIds = Integer.valueOf(getIntent().getStringExtra("userIdposition"));
// =========================================//
        Log.i("Id",userIds.toString() );
        logOutBtn = findViewById(R.id.logOutHomeBtn);
        aboutUsBtn = findViewById(R.id.aboutUsBtn);
        Medicine = Activity_SignIn.Meds;
        Log.i("ABC", userIds.toString());

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        databaseHelper.addMedicine_into_Database(Medicine);
        transaksi = databaseHelper.getTransac(userIds);

        // Buat Logout nya
        logOutBtn.setOnClickListener(e->{
            startActivity(new Intent(HomeActivity.this, Activity_SignIn.class));
        });

        // Buat about us
        aboutUsBtn.setOnClickListener(e->{
            startActivity(new Intent(HomeActivity.this, Activity_AboutUs.class));

        });

        ViewPager viewPager_homepage = findViewById(R.id.homeViewPager);
        viewPager_homepage.setAdapter(new PageAdapter((getSupportFragmentManager())));

        TabLayout tabLayout_homepage = findViewById(R.id.homeTabLayout);
        tabLayout_homepage.setupWithViewPager(viewPager_homepage);
    }
}