package com.example.greendaodemo.database.user.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserEntity {


    @Id(autoincrement = true)
    @Property(nameInDb = "user_id")
    private Long userId;
    @Property(nameInDb = "user_name")
    private String userName;
    @Property(nameInDb = "user_state")
    private String userState;

    @Generated(hash = 1592471859)
    public UserEntity(Long userId, String userName, String userState) {
        this.userId = userId;
        this.userName = userName;
        this.userState = userState;
    }

    @Generated(hash = 1433178141)
    public UserEntity() {
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userState='" + userState + '\'' +
                '}';
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
