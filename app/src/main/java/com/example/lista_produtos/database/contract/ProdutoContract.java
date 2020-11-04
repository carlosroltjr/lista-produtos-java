package com.example.lista_produtos.database.contract;

import com.example.lista_produtos.database.entity.CategoriaEntity;
import com.example.lista_produtos.database.entity.ProdutoEntity;

public final class ProdutoContract {

    private ProdutoContract() {}

    public static final String criarTabela() {
        return "CREATE TABLE " + ProdutoEntity.TABLE_NAME + " (" +
                ProdutoEntity._ID + " INTEGER PRIMARY KEY," +
                ProdutoEntity.COLUMN_NAME_NOME + " TEXT," +
                ProdutoEntity.COLUMN_NAME_VALOR + " REAL," +
                ProdutoEntity.COLUMN_NAME_ID_CATEGORIA + " INTEGER," +
                "FOREIGN KEY (" + ProdutoEntity.COLUMN_NAME_ID_CATEGORIA + ") REFERENCES " +
                CategoriaEntity.TABLE_NAME + "(" + CategoriaEntity._ID + "))";
    }

    public static final String removerTabela() {
        return "DROP TABLE IF EXISTS " + ProdutoEntity.TABLE_NAME;
    }
}
