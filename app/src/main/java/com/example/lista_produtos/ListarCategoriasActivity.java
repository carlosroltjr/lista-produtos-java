package com.example.lista_produtos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lista_produtos.database.CategoriaDAO;
import com.example.lista_produtos.modelo.Categoria;

public class ListarCategoriasActivity extends AppCompatActivity {

    private ListView listViewCategorias;
    private ArrayAdapter<Categoria> adapterCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_categorias);
        listViewCategorias = findViewById((R.id.listView_categorias));
        definirOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CategoriaDAO categoriaDAO = new CategoriaDAO(getBaseContext());
        adapterCategorias = new ArrayAdapter<Categoria>(ListarCategoriasActivity.this,
                android.R.layout.simple_list_item_1,
                categoriaDAO.listar());
        listViewCategorias.setAdapter((adapterCategorias));
    }

    private void definirOnClickListenerListView() {
        listViewCategorias.setOnItemClickListener(((parent, view, position, id) -> {
            Categoria categoriaClicada = adapterCategorias.getItem(position);
            Intent intent = new Intent(ListarCategoriasActivity.this, CadastroCategoriaActivity.class);
            intent.putExtra("categoriaEdicao", categoriaClicada);
            startActivity(intent);
        }));
    }

    public void onClickNovaCategoria(View v) {
        Intent intent = new Intent(ListarCategoriasActivity.this, CadastroCategoriaActivity.class);
        startActivity(intent);
    }

    public void onClickProdutos(View v) {
        Intent intent = new Intent(ListarCategoriasActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}