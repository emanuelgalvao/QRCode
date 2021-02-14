package com.emanuelgalvao.qrcodeapp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Configuration {

    @Id
    private Long id;
    private boolean playBeap;

    public Configuration(boolean playBeap) {
        this.playBeap = playBeap;
    }

    @Generated(hash = 365253574)
    public Configuration() {
    }

    @Generated(hash = 1615774208)
    public Configuration(Long id, boolean playBeap) {
        this.id = id;
        this.playBeap = playBeap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlayBeap(boolean playBeap) {
        this.playBeap = playBeap;
    }

    public boolean getPlayBeap() {
        return this.playBeap;
    }
}
