package com.emanuelgalvao.qrcodeapp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class QRCode {

    @Id(autoincrement = true)
    private Long id;
    private String qrCodeContent;
    private String type;

    public QRCode(String qrCodeContent, String type) {
        this.qrCodeContent = qrCodeContent;
        this.type = type;
    }

    @Generated(hash = 102740268)
    public QRCode(Long id, String qrCodeContent, String type) {
        this.id = id;
        this.qrCodeContent = qrCodeContent;
        this.type = type;
    }

    @Generated(hash = 670865276)
    public QRCode() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
