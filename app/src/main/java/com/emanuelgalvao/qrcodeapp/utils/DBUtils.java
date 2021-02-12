package com.emanuelgalvao.qrcodeapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.emanuelgalvao.qrcodeapp.entity.DaoMaster;
import com.emanuelgalvao.qrcodeapp.entity.DaoSession;

public class DBUtils {

    private static DaoSession daoSession;

    public static void initializeDatabase(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "qr_code_read_and_generator");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
