package com.emanuelgalvao.qrcodeapp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class QRCodeRead {

    @Id(autoincrement = true)
    private Long id;

    private String qrCodeContent;

    @Generated(hash = 1766411820)
    public QRCodeRead(Long id, String qrCodeContent) {
        this.id = id;
        this.qrCodeContent = qrCodeContent;
    }

    public QRCodeRead(String qrCodeContent) {
        this.qrCodeContent = qrCodeContent;
    }

    @Generated(hash = 1694222151)
    public QRCodeRead() {
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
