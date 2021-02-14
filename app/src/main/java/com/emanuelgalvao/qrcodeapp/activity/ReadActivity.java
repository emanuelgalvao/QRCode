package com.emanuelgalvao.qrcodeapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.emanuelgalvao.qrcodeapp.R;
import com.emanuelgalvao.qrcodeapp.utils.DatabaseUtils;
import com.emanuelgalvao.qrcodeapp.utils.ShareUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

public class ReadActivity extends AppCompatActivity {

    private TextView tvQRCodeContent;
    private LinearLayout llResult;
    private LinearLayout llBrowse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Objects.requireNonNull(getSupportActionBar()).hide();

        ImageView ivBack = findViewById(R.id.ivBack);
        LinearLayout llRead = findViewById(R.id.llRead);
        tvQRCodeContent = findViewById(R.id.tvQRCodeContent);
        llResult = findViewById(R.id.llResult);
        LinearLayout llCopy = findViewById(R.id.llCopy);
        LinearLayout llSearch = findViewById(R.id.llSearch);
        llBrowse = findViewById(R.id.llBrowse);
        LinearLayout llShare = findViewById(R.id.llShare);

        llResult.setVisibility(View.GONE);

        ivBack.setOnClickListener(onClickListener());
        llRead.setOnClickListener(onClickListener());
        llCopy.setOnClickListener(onClickListener());
        llSearch.setOnClickListener(onClickListener());
        llBrowse.setOnClickListener(onClickListener());
        llShare.setOnClickListener(onClickListener());
    }

    @SuppressLint("NonConstantResourceId")
    private View.OnClickListener onClickListener() {
        return v -> {
            switch (v.getId()) {
                case R.id.ivBack:
                    finish();
                    break;
                case R.id.llRead:
                    llResult.setVisibility(View.GONE);
                    IntentIntegrator scanner = new IntentIntegrator(ReadActivity.this);
                    scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                    //scanner.setBeepEnabled(false);
                    scanner.setPrompt(getString(R.string.scan_qr_code));
                    scanner.setCaptureActivity(CaptureActivityPortrait.class);
                    scanner.initiateScan();
                    break;
                case R.id.llCopy:
                    ShareUtils.copyText(this, tvQRCodeContent.getText().toString());
                    break;
                case R.id.llSearch:
                    try {
                        startActivity(ShareUtils.openSearchAndBrowse("http://www.google.com/search?q=" + URLEncoder.encode(tvQRCodeContent.getText().toString(), "UTF-8")));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.llBrowse:
                    startActivity(ShareUtils.openSearchAndBrowse(tvQRCodeContent.getText().toString()));
                    break;
                case R.id.llShare:
                    startActivity(ShareUtils.shareText(tvQRCodeContent.getText().toString()));
                    break;
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

                    if(intentResult.getContents().contains("www.") || intentResult.getContents().contains("http://") || intentResult.getContents().contains("https://"))
                        llBrowse.setVisibility(View.VISIBLE);
                    else
                        llBrowse.setVisibility(View.GONE);

                    DatabaseUtils.saveQRCodeOnDatabase(intentResult.getContents(), "read");
                }
            }
        }
    }
}