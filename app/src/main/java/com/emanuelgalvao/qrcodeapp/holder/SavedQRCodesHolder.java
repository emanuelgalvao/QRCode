package com.emanuelgalvao.qrcodeapp.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emanuelgalvao.qrcodeapp.R;

public class SavedQRCodesHolder extends RecyclerView.ViewHolder {

    public LinearLayout llItemClick;
    public TextView tvQRCodeContent;

    public SavedQRCodesHolder(View view) {
        super(view);
        llItemClick = view.findViewById(R.id.llItemClick);
        tvQRCodeContent = view.findViewById(R.id.tvQRCodeContent);
    }
}
