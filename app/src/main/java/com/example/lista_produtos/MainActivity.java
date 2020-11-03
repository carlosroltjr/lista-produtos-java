package com.example.lista_produtos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lista_produtos.database.ProdutoDAO;
import com.example.lista_produtos.modelo.Produto;

public class MainActivity extends AppCompatActivity {

    private ListView listViewProdutos;
    private ArrayAdapter<Produto> adapterProdutos;
    private  int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewProdutos = findViewById(R.id.listView_produtos);

        definirOnClickListenerListView();
        definirOnLongClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        adapterProdutos = new ArrayAdapter<Produto>(MainActivity.this,
                android.R.layout.simple_list_item_1, produtoDAO.listar());

        listViewProdutos.setAdapter(adapterProdutos);
    }

    private void definirOnClickListenerListView() {
        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Produto produtoClicado = adapterProdutos.getItem(i);
                Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
                intent.putExtra("produtoEdicao", produtoClicado);
                startActivity(intent);
            }
        });
    }

    private void definirOnLongClickListenerListView() {
        listViewProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Produto produtoClicado = adapterProdutos.getItem(i);
                ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());

                new AlertDialog.Builder(MainActivity.this).setIcon((android.R.drawable.ic_delete))
                        .setTitle("Confirmar exclusão")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapterProdutos.remove(produtoClicado);
                                adapterProdutos.notifyDataSetChanged();
                                produtoDAO.excluir(produtoClicado);
                                Toast.makeText(MainActivity.this, "Produto excluído", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Não", null).show();
                return true;
            }
        });
    }

    public void onClickNovoProduto(View v) {
        Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
        startActivity(intent);
    }

    public void onClickCategorias(View v) {
        Intent intent = new Intent(MainActivity.this, ListarCategoriasActivity.class);
        startActivity(intent);
        finish();
    }
}