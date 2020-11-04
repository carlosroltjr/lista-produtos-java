package com.example.lista_produtos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.lista_produtos.database.entity.CategoriaEntity;
import com.example.lista_produtos.modelo.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private DbGateway dbGateway;
    private final String SQL_LSITAR_TODOS = "SELECT * FROM " + CategoriaEntity.TABLE_NAME;

    public CategoriaDAO(Context context) {
        dbGateway = DbGateway.getInstance(context);
    }

    public boolean salvar(Categoria categoria) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriaEntity.COLUMN_NAME_DESCRICAO, categoria.getDescricao());

        if (categoria.getId() > 0) {
            return dbGateway.getDb().update(CategoriaEntity.TABLE_NAME,
                    contentValues,
                    CategoriaEntity._ID + "=?",
                    new String[]{String.valueOf((categoria.getId()))}) > 0;
        }

        return dbGateway.getDb().insert(CategoriaEntity.TABLE_NAME, null, contentValues) > 0;
    }

    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();

        Cursor cursor = dbGateway.getDb().rawQuery(SQL_LSITAR_TODOS, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(CategoriaEntity._ID));
            String descricao = cursor.getString(cursor.getColumnIndex(CategoriaEntity.COLUMN_NAME_DESCRICAO));
            categorias.add(new Categoria(id, descricao));
        }

        cursor.close();

        return categorias;
    }
}
