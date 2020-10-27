package com.example.lista_produtos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbGateway {

    private static DbGateway dbGateway;
    private SQLiteDatabase db;

    public static DbGateway getInstance(Context context) {
        if (dbGateway == null) {
            dbGateway = new DbGateway(context);
        }

        return dbGateway;
    }

    private DbGateway(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase(); // abriu uma conexão para escrever dados no database
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
