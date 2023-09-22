package com.example.atividadecrud_09_19;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AdapterProduto extends ArrayAdapter<Produto> {
    private ArrayList<Produto> produtos;
    private Context context;

    public AdapterProduto(Context context, ArrayList<Produto> produtos) {
        super(context, R.layout.item_produto, produtos);
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.item_produto, parent, false);
        TextView lblProduto = itemView.findViewById(R.id.lblProduto);
        lblProduto.setText(produtos.get(position).getTexto());
        if(produtos.get(position).getCor().equals("azul")){
            lblProduto.setTextColor(Color.BLUE);
        }else if(produtos.get(position).getCor().equals("verde")){
            lblProduto.setTextColor(Color.GREEN);
        }else if(produtos.get(position).getCor().equals("vermelho")){
            lblProduto.setTextColor(Color.RED);
        }
        return itemView;
    }
}
