package com.emanuelgalvao.qrcodeapp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Configuration {

    @Id(autoincrement = true)
    private Long id;
    private boolean playBeap;
    private String language;

    public Configuration(boolean playBeap, String language) {
        this.playBeap = playBeap;
        this.language = language;
    }

    @Generated(hash = 2037596214)
    public Configuration(Long id, boolean playBeap, String language) {
        this.id = id;
        this.playBeap = playBeap;
        this.language = language;
    }

    @Generated(hash = 365253574)
    public Configuration() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPlayBeap() {
        return playBeap;
    }

    public void setPlayBeap(boolean playBeap) {
        this.playBeap = playBeap;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean getPlayBeap() {
        return this.playBeap;
    }
}
