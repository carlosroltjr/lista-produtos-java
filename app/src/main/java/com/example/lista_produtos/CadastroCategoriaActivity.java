package com.example.lista_produtos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lista_produtos.database.CategoriaDAO;
import com.example.lista_produtos.modelo.Categoria;

public class CadastroCategoriaActivity extends AppCompatActivity {

    private int id = 0;
    private EditText editTextDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);
        setTitle("Cadastro de Categoria");
        editTextDescricao = findViewById((R.id.editText_descricao));
        carregarCategoria();
    }

    public void carregarCategoria() {
        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("categoriaEdicao") != null) {
            Categoria categoria = (Categoria) intent.getExtras().get("categoriaEdicao");
            editTextDescricao.setText(categoria.getDescricao());
            id = categoria.getId();
        }
    }

    public  void onClickVoltar(View v) { finish(); }

    public void onClickSalvar(View v) {
        String nome = editTextDescricao.getText().toString();
        Categoria categoria = new Categoria(id, nome);
        CategoriaDAO categoriaDAO = new CategoriaDAO(getBaseContext());
        boolean salvou = categoriaDAO.salvar(categoria);
        if (salvou) {
            finish();
        } else {
            Toast.makeText(CadastroCategoriaActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }
}