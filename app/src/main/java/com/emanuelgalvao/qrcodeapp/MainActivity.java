package com.emanuelgalvao.qrcodeapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(onItemSelect());

        ImageView ivBack = navigationView.getHeaderView(0).findViewById(R.id.ivBack);
        ImageView ivMenu = findViewById(R.id.ivMenu);
        LinearLayout llRead = findViewById(R.id.llRead);
        LinearLayout llGenerate = findViewById(R.id.llGenerate);

        ivBack.setOnClickListener(onClickButton());
        ivMenu.setOnClickListener(onClickButton());
        llRead.setOnClickListener(onClickButton());
        llGenerate.setOnClickListener(onClickButton());

    }

    @SuppressLint("NonConstantResourceId")
    private View.OnClickListener onClickButton() {
        return v -> {
            switch (v.getId()) {
                case R.id.ivMenu:
                    drawerLayout.openDrawer(navigationView);
                    break;
                case R.id.ivBack:
                    drawerLayout.closeDrawer(navigationView);
                    break;
                case R.id.llRead:
                    openActivity(ReadActivity.class);
                    break;
                case R.id.llGenerate:
                    openActivity(GenerateActivity.class);
                    break;
            }
        };
    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }

    private void openActivityPassingType(Class<?> activityClass, String typeActivity) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        Bundle bundle = new Bundle();
        bundle.putString("typeActivity", typeActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    private NavigationView.OnNavigationItemSelectedListener onItemSelect() {
        return item -> {
            switch (item.getItemId()) {
                case R.id.nav_read:
                    openActivityPassingType(ReadAndGeneratedQRCodes.class, "read");
                    break;
                case R.id.nav_generate:
                    openActivityPassingType(ReadAndGeneratedQRCodes.class, "generated");
                    break;
                case R.id.nav_configuration:
                    openActivity(ConfigurationActivity.class);
                    break;
            }
            return false;
        };
    }
}