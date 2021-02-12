package com.emanuelgalvao.qrcodeapp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class QRCodeGenerated {

    @Id(autoincrement = true)
    private Long id;

    private String qrCodeContent;

    public QRCodeGenerated(String qrCodeContent) {
        this.qrCodeContent = qrCodeContent;
    }

    @Generated(hash = 1488127640)
    public QRCodeGenerated(Long id, String qrCodeContent) {
        this.id = id;
        this.qrCodeContent = qrCodeContent;
    }

    @Generated(hash = 2099822345)
    public QRCodeGenerated() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrCodeContent() {
        return qrCodeContent;
    }

    public void setQrCodeContent(String qrCodeContent) {
        this.qrCodeContent = qrCodeContent;
    }
}
