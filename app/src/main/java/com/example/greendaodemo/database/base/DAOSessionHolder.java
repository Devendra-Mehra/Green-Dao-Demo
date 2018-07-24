package com.example.greendaodemo.database.base;


public class DAOSessionHolder {
    private static volatile DAOSessionHolder singleton = null;
    private DaoSession daoSession;

    public static DAOSessionHolder getInstance() {
        if (singleton == null) {
            synchronized (DAOSessionHolder.class) {
                if (singleton == null) {
                    singleton = new DAOSessionHolder();
                }
            }
        }
        return singleton;
    }

    public void putDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}