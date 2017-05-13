package com.shopmanageronline.shopmanageronline.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.shopmanageronline.shopmanageronline.entity.Provider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThanhDH - LA on 5/13/2017.
 */

public class DBManager {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long create(Provider provider) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.PROVIDER_COLUMN_NAME, provider.getName());
        values.put(DBHelper.PROVIDER_COLUMN_PHONE, provider.getPhone());
        values.put(DBHelper.PROVIDER_COLUMN_EMAIL, provider.getEmail());
        values.put(DBHelper.PROVIDER_COLUMN_ADDRESS, provider.getAddress());
        values.put(DBHelper.PROVIDER_COLUMN_NOTE, provider.getNote());

        return db.insert(DBHelper.TABLE_PROVIDER, null, values);
    }

    public List<Provider> getAllProviders() {
        List<Provider> providers = new ArrayList<Provider>();
        Cursor cursor = db.query(DBHelper.TABLE_PROVIDER, DBHelper.PROVIDER_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Provider provider = cursorToProvider(cursor);
            providers.add(provider);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return providers;
    }

    public int deleteProvider(long id) {
        return db.delete(DBHelper.TABLE_PROVIDER, DBHelper.PROVIDER_COLUMN_ID + " = " + id, null);
    }

    private Provider cursorToProvider(Cursor cursor) {
        Provider provider = new Provider();
        provider.setId(cursor.getLong(0));
        provider.setName(cursor.getString(1));
        provider.setPhone(cursor.getString(2));
        provider.setEmail(cursor.getString(3));
        provider.setAddress(cursor.getString(4));
        provider.setNote(cursor.getString(5));
        return provider;
    }
}
