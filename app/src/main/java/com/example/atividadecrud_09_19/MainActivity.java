package com.example.atividadecrud_09_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
//Lucas Gonçalves e Marcos Stanquin
public class MainActivity extends AppCompatActivity {

    SQLiteDatabase bd;

    private Button btnInserir;
    private ListView listaProdutos;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private Produto produto;
    private AdapterProduto adaptador;
    private Cursor dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaProdutos = findViewById(R.id.listaProdutos);
        btnInserir = findViewById(R.id.btnInserir);

        Intent i = getIntent();
        bd = openOrCreateDatabase("bd", MODE_PRIVATE, null);
        String createDB = "CREATE TABLE IF NOT EXISTS produtos (id INTEGER PRIMARY KEY AUTOINCREMENT, texto VARCHAR, cor VARCHAR)";
        bd.execSQL(createDB);

        String listar = "SELECT id, texto, cor from produtos";
        dados = bd.rawQuery(listar, null);

        if (dados.getCount() > 0) {
            while (dados.moveToNext()) {
                Produto p = new Produto();
                p.setId(dados.getInt(dados.getColumnIndex("id")));
                p.setTexto(dados.getString(dados.getColumnIndex("texto")));
                p.setCor(dados.getString(dados.getColumnIndex("cor")));
                produtos.add(p);
            }
        }

        btnInserir.setOnClickListener(new handleInserir());

        adaptador = new AdapterProduto(this, produtos);
        listaProdutos.setAdapter(adaptador);

        listaProdutos.setOnItemClickListener(new EscutadorLista());
        listaProdutos.setLongClickable( true );
        listaProdutos.setOnItemLongClickListener(new EscutadorLista());
    }

    class handleInserir implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), InseretextActivity.class);
            startActivityForResult(i, 1);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String texto = data.getStringExtra("texto");
            String cor = data.getStringExtra("cor");
            String cmd = "INSERT INTO produtos(texto, cor) values ('" + texto + "', '" + cor + "')";
            bd.execSQL(cmd);

            String listar = "SELECT id, texto, cor from produtos";
            dados = bd.rawQuery(listar, null);
            produtos.clear();
            if (dados.getCount() > 0) {
                while (dados.moveToNext()) {
                    Produto p = new Produto();
                    p.setId(dados.getInt(dados.getColumnIndex("id")));
                    p.setTexto(dados.getString(dados.getColumnIndex("texto")));
                    p.setCor(dados.getString(dados.getColumnIndex("cor")));
                    produtos.add(p);
                }
            }
            adaptador.notifyDataSetChanged();
        }
    }

    private class EscutadorLista implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(MainActivity.this, produtos.get(i).getTexto()+" \n"+produtos.get(i).getCor().toUpperCase(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            String cmd = "DELETE FROM produtos where id="+produtos.get(i).getId();
            bd.execSQL(cmd);
            produtos.remove(i);
            adaptador.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Item deletado com sucesso!", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}