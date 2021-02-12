package com.emanuelgalvao.qrcodeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.emanuelgalvao.qrcodeapp.entity.QRCodeGenerated;
import com.emanuelgalvao.qrcodeapp.entity.QRCodeGeneratedDao;
import com.emanuelgalvao.qrcodeapp.entity.QRCodeRead;
import com.emanuelgalvao.qrcodeapp.entity.QRCodeReadDao;
import com.emanuelgalvao.qrcodeapp.utils.DBUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Objects;

public class GenerateActivity extends AppCompatActivity {

    private ImageView ivBack;
    private EditText etQRCodeContent;
    private LinearLayout llGenerate;
    private LinearLayout llResult;
    private ImageView ivQRCodeGenerated;
    private LinearLayout llSave;
    private LinearLayout llShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        Objects.requireNonNull(getSupportActionBar()).hide();

        ivBack = findViewById(R.id.ivBack);
        etQRCodeContent = findViewById(R.id.etQRCodeContent);
        llGenerate = findViewById(R.id.llGenerate);
        llResult = findViewById(R.id.llResult);
        ivQRCodeGenerated = findViewById(R.id.ivQRCodeGenerated);
        llSave = findViewById(R.id.llSave);
        llShare = findViewById(R.id.llShare);

        llResult.setVisibility(View.GONE);

        ivBack.setOnClickListener(onClickListener());
        llGenerate.setOnClickListener(onClickListener());
        llSave.setOnClickListener(onClickListener());
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
                    case R.id.llGenerate:
                        String text = etQRCodeContent.getText().toString();
                        if (!text.equals("")) {
                            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                            try {
                                BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,500,500);
                                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(etQRCodeContent.getWindowToken(), 0);

                                saveQRCodeGemeratedOnDatabase(text);

                                llResult.setVisibility(View.VISIBLE);
                                ivQRCodeGenerated.setImageBitmap(bitmap);
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case R.id.llSave:
                        break;
                    case R.id.llShare:
                        break;
                }
            }
        };
    }

    private void saveQRCodeGemeratedOnDatabase(String QRCodeContent) {
        QRCodeGeneratedDao qrCodeGeneratedDao = DBUtils.getDaoSession().getQRCodeGeneratedDao();
        QRCodeGenerated qrCodeGenerated = new QRCodeGenerated(QRCodeContent);
        qrCodeGeneratedDao.insert(qrCodeGenerated);
    }
}