package com.example.greendaodemo.database.user.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserEntity {

    private String uId;

    @Generated(hash = 1497869205)
    public UserEntity(String uId) {
        this.uId = uId;
    }

    @Generated(hash = 1433178141)
    public UserEntity() {
    }

    public String getUId() {
        return this.uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

   
}
