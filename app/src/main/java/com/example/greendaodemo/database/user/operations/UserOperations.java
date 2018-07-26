package com.example.greendaodemo.database.user.operations;

import android.content.Context;

import com.example.greendaodemo.database.base.BaseRepo;
import com.example.greendaodemo.database.base.UserEntityDao;
import com.example.greendaodemo.database.user.entity.UserEntity;

import java.util.List;


/**
 * Created by user on 10/12/17.
 */

public class UserOperations extends BaseRepo {

    private static UserOperations instance = null;
    private UserEntityDao dao;

    private UserOperations(Context context) {
        super();
        dao = daoSession.getUserEntityDao();
    }

    public static UserOperations getInstance(Context context) {
        if (instance == null) {
            instance = new UserOperations(context);
        }
        return instance;
    }


    public void insertUserList(List<UserEntity> userEntities) {
        dao.insertOrReplaceInTx(userEntities);
    }


    public void insertUser(UserEntity userEntity) {
        dao.insertOrReplaceInTx(userEntity);
    }

    public List<UserEntity> getUserEntity() {
        return dao.queryBuilder()
                .list();
    }

    public void delete(Long userId) {
        dao.deleteByKey(userId);
    }


    public void upDate(UserEntity userEntity) {
        dao.updateInTx(userEntity);
    }

}