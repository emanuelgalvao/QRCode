package com.emanuelgalvao.qrcodeapp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.emanuelgalvao.qrcodeapp.R;
import com.emanuelgalvao.qrcodeapp.utils.DatabaseUtils;
import com.emanuelgalvao.qrcodeapp.utils.QRCodeUtils;
import com.emanuelgalvao.qrcodeapp.utils.ShareUtils;

import java.util.Objects;

public class GenerateActivity extends AppCompatActivity {

    private EditText etQRCodeContent;
    private LinearLayout llResult;
    private ImageView ivQRCodeGenerated;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        Objects.requireNonNull(getSupportActionBar()).hide();

        ImageView ivBack = findViewById(R.id.ivBack);
        etQRCodeContent = findViewById(R.id.etQRCodeContent);
        LinearLayout llGenerate = findViewById(R.id.llGenerate);
        llResult = findViewById(R.id.llResult);
        ivQRCodeGenerated = findViewById(R.id.ivQRCodeGenerated);
        LinearLayout llShare = findViewById(R.id.llShare);

        llResult.setVisibility(View.GONE);

        ivBack.setOnClickListener(onClickListener());
        llGenerate.setOnClickListener(onClickListener());
        llShare.setOnClickListener(onClickListener());
    }

    @SuppressLint("NonConstantResourceId")
    private View.OnClickListener onClickListener() {
        return v -> {
            switch (v.getId()) {
                case R.id.ivBack:
                    finish();
                    break;
                case R.id.llGenerate:
                    String text = etQRCodeContent.getText().toString();
                    if (!text.equals("")) {
                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(etQRCodeContent.getWindowToken(), 0);

                        DatabaseUtils.saveQRCodeOnDatabase(text, "generated");

                        llResult.setVisibility(View.VISIBLE);
                        bitmap = QRCodeUtils.generateQRCode(text);
                        ivQRCodeGenerated.setImageBitmap(bitmap);
                    }
                    break;
                case R.id.llShare:
                    startActivity(ShareUtils.shareImage(getApplicationContext(), bitmap));
                    break;
            }
        };
    }
}