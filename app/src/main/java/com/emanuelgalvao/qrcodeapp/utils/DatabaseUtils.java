package com.emanuelgalvao.qrcodeapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.emanuelgalvao.qrcodeapp.entity.DaoMaster;
import com.emanuelgalvao.qrcodeapp.entity.DaoSession;
import com.emanuelgalvao.qrcodeapp.entity.QRCode;
import com.emanuelgalvao.qrcodeapp.entity.QRCodeDao;

import java.util.List;

public class DatabaseUtils {

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

    public static void saveQRCodeOnDatabase(String qrCodeContent, String type) {
        QRCodeDao qrCodeReadDao = DatabaseUtils.getDaoSession().getQRCodeDao();
        QRCode qrCode = new QRCode(qrCodeContent, type);
        qrCodeReadDao.insert(qrCode);
    }

    public static List<QRCode> getQRCodeListFromDatabase(String type) {
        QRCodeDao qrCodeReadDao = DatabaseUtils.getDaoSession().getQRCodeDao();
        return qrCodeReadDao.queryBuilder()
                .where(QRCodeDao.Properties.Type.eq(type))
                .orderDesc(QRCodeDao.Properties.Id)
                .list();
    }
}
