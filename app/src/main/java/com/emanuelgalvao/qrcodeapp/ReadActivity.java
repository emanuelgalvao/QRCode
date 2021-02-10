package com.emanuelgalvao.qrcodeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Objects;

public class ReadActivity extends AppCompatActivity {

    private ImageView ivBack;
    private LinearLayout llRead;
    private TextView tvQRCodeContent;
    private LinearLayout llResult;
    private LinearLayout llCopy;
    private LinearLayout llSearch;
    private LinearLayout llBrowse;
    private LinearLayout llShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Objects.requireNonNull(getSupportActionBar()).hide();

        ivBack = findViewById(R.id.ivBack);
        llRead = findViewById(R.id.llRead);
        tvQRCodeContent = findViewById(R.id.tvQRCodeContent);
        llResult = findViewById(R.id.llResult);
        llCopy = findViewById(R.id.llCopy);
        llSearch = findViewById(R.id.llSearch);
        llBrowse = findViewById(R.id.llBrowse);
        llShare = findViewById(R.id.llShare);

        llResult.setVisibility(View.GONE);

        ivBack.setOnClickListener(onClickListener());
        llRead.setOnClickListener(onClickListener());
        llCopy.setOnClickListener(onClickListener());
        llSearch.setOnClickListener(onClickListener());
        llBrowse.setOnClickListener(onClickListener());
        llShare.setOnClickListener(onClickListener());
    }

    private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ivBack:
                        finish();
                        break;
                    case R.id.llRead:
                        IntentIntegrator scanner = new IntentIntegrator(ReadActivity.this);
                        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                        //scanner.setBeepEnabled(false) //retira o beep ao scannear
                        scanner.initiateScan();
                        break;
                    case R.id.llCopy:
                        break;
                    case R.id.llSearch:
                        break;
                    case R.id.llBrowse:
                        break;
                    case R.id.llShare:
                        break;
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            if (intentResult != null) {
                if (intentResult.getContents() != null) {

                    llResult.setVisibility(View.VISIBLE);
                    tvQRCodeContent.setText(intentResult.getContents());
                }
            }
        }
    }
}