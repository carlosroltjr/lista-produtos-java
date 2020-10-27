package com.example.lista_produtos.database.entity;

import android.provider.BaseColumns;

public final class ProdutoEntity implements BaseColumns {

    private ProdutoEntity() {}

    public static final String TABLE_NAME = "produto";
    public static final String COLUMN_NAME_NOME = "nome";
    public static  final String COLUMN_NAME_VALOR = "valor";
}
