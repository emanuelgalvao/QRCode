package com.emanuelgalvao.qrcodeapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.emanuelgalvao.qrcodeapp.R;
import com.emanuelgalvao.qrcodeapp.entity.Configuration;
import com.emanuelgalvao.qrcodeapp.utils.DatabaseUtils;

import java.util.Objects;

public class ConfigurationActivity extends AppCompatActivity {

    private Configuration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        Objects.requireNonNull(getSupportActionBar()).hide();

        configuration = DatabaseUtils.getConfigurationFromDatabase();

        ImageView ivBack = findViewById(R.id.ivBack);
        Switch swBeap = findViewById(R.id.swBeap);

        swBeap.setChecked(configuration.getPlayBeap());

        ivBack.setOnClickListener(onClickBack());
        swBeap.setOnCheckedChangeListener(onCheckedChangeListener());
    }

    private View.OnClickListener onClickBack() {
        return v -> finish();
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                configuration.setPlayBeap(isChecked);
                DatabaseUtils.updateConfiguration(configuration);
            }
        };
    }
}