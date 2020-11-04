package com.example.lista_produtos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lista_produtos.database.contract.CategoriaContract;
import com.example.lista_produtos.database.contract.ProdutoContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.produto v2";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProdutoContract.criarTabela());
        db.execSQL((CategoriaContract.criarTabela()));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(ProdutoContract.removerTabela());
        db.execSQL(CategoriaContract.removerTabela());
        db.execSQL(CategoriaContract.criarTabela());
        db.execSQL(ProdutoContract.criarTabela());
    }
}
