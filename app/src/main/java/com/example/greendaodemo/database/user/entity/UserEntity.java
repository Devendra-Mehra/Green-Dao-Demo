package com.example.greendaodemo.database.user.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserEntity {

    @Id
    private String uId;
    private String userName;
    private String stateCity;

    @Override
    public String toString() {
        return "UserEntity{" +
                "uId='" + uId + '\'' +
                ", userName='" + userName + '\'' +
                ", stateCity='" + stateCity + '\'' +
                '}';
    }

    @Generated(hash = 354220560)
    public UserEntity(String uId, String userName, String stateCity) {
        this.uId = uId;
        this.userName = userName;
        this.stateCity = stateCity;
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

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStateCity() {
        return this.stateCity;
    }

    public void setStateCity(String stateCity) {
        this.stateCity = stateCity;
    }


}
