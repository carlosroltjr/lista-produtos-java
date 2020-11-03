package com.example.lista_produtos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lista_produtos.database.contract.CategoriaContract;
import com.example.lista_produtos.database.contract.ProcutoContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.produto";
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProcutoContract.criarTabela());
        db.execSQL((CategoriaContract.criarTabela()));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(ProcutoContract.removerTabela());
        db.execSQL(CategoriaContract.removerTabela());
        db.execSQL(CategoriaContract.criarTabela());
        db.execSQL(ProcutoContract.criarTabela());
    }
}
