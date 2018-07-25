package com.example.greendaodemo.database.user.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserEntity {


    private String userName;
    private String userState;

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", userState='" + userState + '\'' +
                '}';
    }

    @Generated(hash = 143625408)
    public UserEntity(String userName, String userState) {
        this.userName = userName;
        this.userState = userState;
    }
    @Generated(hash = 1433178141)
    public UserEntity() {
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserState() {
        return this.userState;
    }
    public void setUserState(String userState) {
        this.userState = userState;
    }

}
