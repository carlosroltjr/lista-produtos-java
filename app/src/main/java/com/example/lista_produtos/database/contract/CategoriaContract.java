package com.example.lista_produtos.database.contract;

import com.example.lista_produtos.database.entity.CategoriaEntity;

public final class CategoriaContract {

    private CategoriaContract() {}

    public static final String criarTabela() {
        return "CREATE TABLE " + CategoriaEntity.TABLE_NAME + " (" +
                CategoriaEntity._ID + " INTEGER PRIMARY KEY," +
                CategoriaEntity.COLUMN_NAME_DESCRICAO + " TEXT)";
    }

    public static final String removerTabela() {
        return "DORP TABLE IF EXISTS " + CategoriaEntity.TABLE_NAME;
    }
}
