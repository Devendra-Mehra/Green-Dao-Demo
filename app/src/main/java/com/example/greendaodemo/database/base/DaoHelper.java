package com.example.greendaodemo.database.base;


import org.greenrobot.greendao.AbstractDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Can be Auto Generated using Annotation Processing
 * Getting all the classes with @UserEntity annotation and adding it to the list
 */
public class DaoHelper {

    public static List<Class<? extends AbstractDao<?, ?>>> getAllDaos() {
        List<Class<? extends AbstractDao<?, ?>>> daos = new ArrayList<>();
        daos.add(UserEntityDao.class);

        return daos;
    }
}