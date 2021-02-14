package com.emanuelgalvao.qrcodeapp.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.emanuelgalvao.qrcodeapp.R;
import com.emanuelgalvao.qrcodeapp.entity.QRCode;
import com.emanuelgalvao.qrcodeapp.utils.DatabaseUtils;
import com.emanuelgalvao.qrcodeapp.utils.QRCodeUtils;
import com.emanuelgalvao.qrcodeapp.utils.ShareUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DetailsDialog extends Dialog {

    private final Context context;
    private final QRCode qrCode;
    private Bitmap bitmap;
    private final OnClickButtonCallback onClickButtonCallback;

    public interface OnClickButtonCallback {
        void onClickButton(Intent intent, boolean refreshList);
    }

    public DetailsDialog(@NonNull Context context, QRCode qrCode, OnClickButtonCallback onClickButtonCallback) {
        super(context);
        this.context = context;
        this.qrCode = qrCode;
        this.onClickButtonCallback = onClickButtonCallback;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_details);

        getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.rounded_card));

        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setCancelable(false);

        ImageView ivClose = findViewById(R.id.ivClose);
        ImageView ivQRCode = findViewById(R.id.ivQRCode);
        TextView tvContent = findViewById(R.id.tvContent);

        LinearLayout llCopy = findViewById(R.id.llCopy);
        LinearLayout llSearch = findViewById(R.id.llSearch);
        LinearLayout llBrowse = findViewById(R.id.llBrowse);
        LinearLayout llShareText = findViewById(R.id.llShareText);
        LinearLayout llShareImage = findViewById(R.id.llShareImage);
        LinearLayout llDelete = findViewById(R.id.llDelete);

        tvContent.setMovementMethod(new ScrollingMovementMethod());

        bitmap = QRCodeUtils.generateQRCode(qrCode.getQrCodeContent());
        ivQRCode.setImageBitmap(bitmap);
        tvContent.setText(qrCode.getQrCodeContent());

        if(qrCode.getQrCodeContent().contains("www.") || qrCode.getQrCodeContent().contains("http://") || qrCode.getQrCodeContent().contains("https://"))
            llSearch.setVisibility(View.GONE);
        else
            llBrowse.setVisibility(View.GONE);

        ivClose.setOnClickListener(onClickListener());
        llCopy.setOnClickListener(onClickListener());
        llSearch.setOnClickListener(onClickListener());
        llBrowse.setOnClickListener(onClickListener());
        llShareText.setOnClickListener(onClickListener());
        llShareImage.setOnClickListener(onClickListener());
        llDelete.setOnClickListener(onClickListener());
    }

    @SuppressLint("NonConstantResourceId")
    private View.OnClickListener onClickListener() {
        return v -> {
            switch (v.getId()) {
                case R.id.ivClose:
                    dismiss();
                    break;
                case R.id.llCopy:
                    ShareUtils.copyText(context, qrCode.getQrCodeContent());
                    break;
                case R.id.llSearch:
                    try {
                        onClickButtonCallback.onClickButton(ShareUtils.openSearchAndBrowse("http://www.google.com/search?q=" + URLEncoder.encode(qrCode.getQrCodeContent(), "UTF-8")), false);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.llBrowse:
                    onClickButtonCallback.onClickButton(ShareUtils.openSearchAndBrowse(qrCode.getQrCodeContent()), false);
                    break;
                case R.id.llShareText:
                    onClickButtonCallback.onClickButton(ShareUtils.shareText(qrCode.getQrCodeContent()), false);
                    break;
                case R.id.llShareImage:
                    onClickButtonCallback.onClickButton(ShareUtils.shareImage(context, bitmap), false);
                    break;
                case R.id.llDelete:
                    DatabaseUtils.getDaoSession().getQRCodeDao().delete(qrCode);
                    onClickButtonCallback.onClickButton(null, true);
                    dismiss();
            }
        };
    }
}
