package com.emanuelgalvao.qrcodeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();

        ImageView ivMenu = findViewById(R.id.ivMenu);
        LinearLayout llRead = findViewById(R.id.llRead);
        LinearLayout llGenerate = findViewById(R.id.llGenerate);

        ivMenu.setOnClickListener(onClickButton());
        llRead.setOnClickListener(onClickButton());
        llGenerate.setOnClickListener(onClickButton());

    }

    @SuppressLint("NonConstantResourceId")
    private View.OnClickListener onClickButton() {
        return v -> {
            switch (v.getId()) {
                case R.id.ivMenu:
                    Toast.makeText(this, "Ola", Toast.LENGTH_SHORT).show();
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
}