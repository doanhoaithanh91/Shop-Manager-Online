package com.shopmanageronline.shopmanageronline.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ThanhDH - LA on 5/13/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shopmanageronline.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PROVIDER = "provider";
    public static final String PROVIDER_COLUMN_ID = "_id";
    public static final String PROVIDER_COLUMN_NAME = "name";
    public static final String PROVIDER_COLUMN_PHONE = "phone";
    public static final String PROVIDER_COLUMN_EMAIL = "email";
    public static final String PROVIDER_COLUMN_ADDRESS = "address";
    public static final String PROVIDER_COLUMN_NOTE = "note";
    public static final String[] PROVIDER_COLUMNS = {PROVIDER_COLUMN_ID, PROVIDER_COLUMN_NAME, PROVIDER_COLUMN_PHONE, PROVIDER_COLUMN_EMAIL, PROVIDER_COLUMN_ADDRESS, PROVIDER_COLUMN_NOTE};

    private static final String DATABASE_CREATE_PROVIDER = "create table "
            + TABLE_PROVIDER + "( "
            + PROVIDER_COLUMN_ID + " integer primary key autoincrement, "
            + PROVIDER_COLUMN_NAME + " text not null, "
            + PROVIDER_COLUMN_PHONE + " text, "
            + PROVIDER_COLUMN_EMAIL + " text, "
            + PROVIDER_COLUMN_ADDRESS + " text, "
            + PROVIDER_COLUMN_NOTE + " text);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_PROVIDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROVIDER);
        onCreate(db);
    }
}
