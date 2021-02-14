package com.emanuelgalvao.qrcodeapp.utils;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.emanuelgalvao.qrcodeapp.BuildConfig;

import java.io.File;
import java.io.FileOutputStream;

public class ShareUtils {

    public static Intent shareText(String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");

        return Intent.createChooser(sendIntent, "Share QR Code content");
    }

    @SuppressLint("SetWorldReadable")
    public static Intent shareImage(Context context, Bitmap bitmap) {
        Intent sendIntent = null;
        try {
            File file = new File(context.getExternalCacheDir(), File.separator +"qrCode.jpg");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            sendIntent = new Intent(android.content.Intent.ACTION_SEND);
            sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri photoURI = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID +".provider", file);
            sendIntent.putExtra(Intent.EXTRA_STREAM, photoURI);
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            sendIntent.setType("image/jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Intent.createChooser(sendIntent, "Share QR Code Image");
    }

    public static Intent openSearchAndBrowse(String url) {
        Uri uri = Uri.parse(url);
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public static void copyText(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Copied Text!", Toast.LENGTH_SHORT).show();
    }
}
