package com.emanuelgalvao.qrcodeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emanuelgalvao.qrcodeapp.R;
import com.emanuelgalvao.qrcodeapp.entity.QRCode;
import com.emanuelgalvao.qrcodeapp.holder.SavedQRCodesHolder;

import java.util.List;

public class SavedQRCodesAdapter extends RecyclerView.Adapter<SavedQRCodesHolder> {

    public interface OnClickItemCallback {
        void onClickItem(QRCode qrCode);
    }

    private final List<QRCode> listSavedQRCodes;
    private final OnClickItemCallback onClickItemCallback;

    public SavedQRCodesAdapter(List<QRCode> listSavedQRCodes, OnClickItemCallback onClickItemCallback) {
        this.listSavedQRCodes = listSavedQRCodes;
        this.onClickItemCallback = onClickItemCallback;
    }

    @NonNull
    @Override
    public SavedQRCodesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SavedQRCodesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_saved_qr_codes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SavedQRCodesHolder holder, int position) {

        QRCode qrCode = listSavedQRCodes.get(position);

        holder.tvQRCodeContent.setText(qrCode.getQrCodeContent());
        holder.llItemClick.setOnClickListener(onClickListener(qrCode));
    }

    @Override
    public int getItemCount() {
        return listSavedQRCodes.size();
    }

    private View.OnClickListener onClickListener(QRCode qrCode) {
        return v -> onClickItemCallback.onClickItem(qrCode);
    }
}
