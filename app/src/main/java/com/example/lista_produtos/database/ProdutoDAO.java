package com.example.lista_produtos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.lista_produtos.database.entity.CategoriaEntity;
import com.example.lista_produtos.database.entity.ProdutoEntity;
import com.example.lista_produtos.modelo.Categoria;
import com.example.lista_produtos.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private DbGateway dbGateway;
    private final String SQL_LISTAR_TODOS = "SELECT produto._id, nome, valor, idcategoria, descricao FROM " +
            ProdutoEntity.TABLE_NAME + " INNER JOIN " + CategoriaEntity.TABLE_NAME + " ON " +
            ProdutoEntity.COLUMN_NAME_ID_CATEGORIA + " = " + CategoriaEntity.TABLE_NAME + "." + CategoriaEntity._ID;

    public ProdutoDAO(Context context) {
        dbGateway = DbGateway.getInstance(context);
    }

    public boolean salvar(Produto produto) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ProdutoEntity.COLUMN_NAME_NOME, produto.getNome());
        contentValues.put(ProdutoEntity.COLUMN_NAME_VALOR, produto.getValor());
        contentValues.put(ProdutoEntity.COLUMN_NAME_ID_CATEGORIA, produto.getCategoria().getId());

        if (produto.getId() > 0) {
            return dbGateway.getDb().update(ProdutoEntity.TABLE_NAME,
                    contentValues,
                    ProdutoEntity._ID + "=?",
                    new String[]{String.valueOf(produto.getId())}) > 0;
        }

        return dbGateway.getDb().insert(ProdutoEntity.TABLE_NAME, null, contentValues) > 0;
    }

    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        Cursor cursor = dbGateway.getDb().rawQuery(SQL_LISTAR_TODOS, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ProdutoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(ProdutoEntity.COLUMN_NAME_NOME));
            Float valor = cursor.getFloat(cursor.getColumnIndex(ProdutoEntity.COLUMN_NAME_VALOR));
            int idCategoria = cursor.getInt(cursor.getColumnIndex(ProdutoEntity.COLUMN_NAME_ID_CATEGORIA));
            String descricao = cursor.getString(cursor.getColumnIndex(CategoriaEntity.COLUMN_NAME_DESCRICAO));
            Categoria categoria = new Categoria(idCategoria, descricao);

            produtos.add(new Produto(id, nome, valor, categoria));
        }

        cursor.close();

        return produtos;
    }

    public void excluir(Produto produto) {
        dbGateway.getDb().delete(ProdutoEntity.TABLE_NAME,
                ProdutoEntity._ID + " LIKE ?", new String[]{String.valueOf(produto.getId())});
    }
}
