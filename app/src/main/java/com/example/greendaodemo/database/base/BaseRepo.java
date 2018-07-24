package com.example.greendaodemo.database.base;


import com.example.greendaodemo.application.Application;

import org.greenrobot.greendao.database.Database;

public class BaseRepo {

    private static BaseRepo instance = null;
    protected DaoSession daoSession;

    public BaseRepo() {
        daoSession = DAOSessionHolder.getInstance().getDaoSession();
        if (daoSession == null) {
            MySQLiteOpenHelper helper = new MySQLiteOpenHelper(Application.getContext(), "greenDaoDemo.db");
            Database db = helper.getWritableDb();
            daoSession = new DaoMaster(db).newSession();
            DAOSessionHolder.getInstance().putDaoSession(daoSession);
        }
    }

    public static BaseRepo getInstance() {
        if (instance == null) {
            instance = new BaseRepo();
        }
        return instance;
    }

    public void truncate(Class classToDelete) {
        daoSession.deleteAll(classToDelete);
    }


    public void truncateAllTables() {
        daoSession.getDatabase().beginTransaction();
        try {
            DaoMaster.dropAllTables(daoSession.getDatabase(), true);
            DaoMaster.createAllTables(daoSession.getDatabase(), true);
            daoSession.getDatabase().setTransactionSuccessful();
        } finally {
            daoSession.getDatabase().endTransaction();
        }
    }
}