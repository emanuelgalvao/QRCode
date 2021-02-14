package com.emanuelgalvao.qrcodeapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.emanuelgalvao.qrcodeapp.R;

import java.util.Objects;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}