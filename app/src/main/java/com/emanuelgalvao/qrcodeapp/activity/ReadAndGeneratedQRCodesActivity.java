package com.emanuelgalvao.qrcodeapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emanuelgalvao.qrcodeapp.R;
import com.emanuelgalvao.qrcodeapp.adapter.SavedQRCodesAdapter;
import com.emanuelgalvao.qrcodeapp.dialog.DetailsDialog;
import com.emanuelgalvao.qrcodeapp.entity.QRCode;
import com.emanuelgalvao.qrcodeapp.utils.DatabaseUtils;

import java.util.List;
import java.util.Objects;

public class ReadAndGeneratedQRCodesActivity extends AppCompatActivity implements SavedQRCodesAdapter.OnClickItemCallback, DetailsDialog.OnClickButtonCallback {

    private String typeActivity;
    private RecyclerView rvSavedQRCodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_and_generated_qr_codes);

        Objects.requireNonNull(getSupportActionBar()).hide();

        ImageView ivBack = findViewById(R.id.ivBack);
        TextView tvSavedTitle = findViewById(R.id.tvSavedTitle);
        rvSavedQRCodes = findViewById(R.id.rvSavedQRCodes);

        ivBack.setOnClickListener(onClickBack());

        Bundle bundle = getIntent().getExtras();
        typeActivity = bundle.getString("typeActivity");

        switch (typeActivity) {
            case "read":
                tvSavedTitle.setText(R.string.qr_codes_read);
                break;
            case "generated":
                tvSavedTitle.setText(R.string.qr_codes_generated);
                break;
        }

        loadAdapterList();
    }

    private void loadAdapterList() {
        List<QRCode> qrCodeList = DatabaseUtils.getQRCodeListFromDatabase(typeActivity);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvSavedQRCodes.setLayoutManager(layoutManager);
        SavedQRCodesAdapter adapter = new SavedQRCodesAdapter(qrCodeList, this);
        rvSavedQRCodes.setAdapter(adapter);
    }

    private View.OnClickListener onClickBack() {
        return v -> finish();
    }

    @Override
    public void onClickItem(QRCode qrCode) {
        openDialog(qrCode);
    }

    private void openDialog(QRCode qrCode) {
        DetailsDialog detailsDialog = new DetailsDialog(this, qrCode, this);
        detailsDialog.show();
    }

    @Override
    public void onClickButton(Intent intent, boolean refreshList) {
        if(refreshList)
            loadAdapterList();
        else
            startActivity(intent);
    }
}