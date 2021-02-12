package com.emanuelgalvao.qrcodeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emanuelgalvao.qrcodeapp.entity.QRCodeRead;
import com.emanuelgalvao.qrcodeapp.entity.QRCodeReadDao;
import com.emanuelgalvao.qrcodeapp.utils.DBUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

public class ReadActivity extends AppCompatActivity {

    private ImageView ivBack;
    private LinearLayout llRead;
    private TextView tvQRCodeContent;
    private LinearLayout llResult;
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
        llSearch = findViewById(R.id.llSearch);
        llBrowse = findViewById(R.id.llBrowse);
        llShare = findViewById(R.id.llShare);

        llResult.setVisibility(View.GONE);

        ivBack.setOnClickListener(onClickListener());
        llRead.setOnClickListener(onClickListener());
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
                    case R.id.llSearch:
                        try {
                            String query = URLEncoder.encode(tvQRCodeContent.getText().toString(), "UTF-8");
                            Uri uri = Uri.parse("http://www.google.com/#q=" + query);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
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

                    saveQRCodeReadOnDatabase(intentResult.getContents());
                }
            }
        }
    }

    private void saveQRCodeReadOnDatabase(String QRCodeContent) {
        QRCodeReadDao qrCodeReadDao = DBUtils.getDaoSession().getQRCodeReadDao();
        QRCodeRead qrCodeRead = new QRCodeRead(QRCodeContent);
        qrCodeReadDao.insert(qrCodeRead);
    }
}