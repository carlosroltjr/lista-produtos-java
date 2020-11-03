package com.example.lista_produtos.database.entity;

import android.provider.BaseColumns;

public final class CategoriaEntity implements BaseColumns {

    private CategoriaEntity() {}

    public static final String TABLE_NAME = "categoria";
    public  static final String COLUMN_NAME_DESCRICAO = "descricao";
}
