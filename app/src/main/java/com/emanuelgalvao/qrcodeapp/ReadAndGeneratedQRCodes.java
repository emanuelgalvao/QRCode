package com.emanuelgalvao.qrcodeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

public class ReadAndGeneratedQRCodes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_and_generated_qr_codes);

        Objects.requireNonNull(getSupportActionBar()).hide();

    }
}