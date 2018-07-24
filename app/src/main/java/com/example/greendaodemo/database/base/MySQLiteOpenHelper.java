package com.example.greendaodemo.database.base;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, DaoHelper.getAllDaos());
    }
}