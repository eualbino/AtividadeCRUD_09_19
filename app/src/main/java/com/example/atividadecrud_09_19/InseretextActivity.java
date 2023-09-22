package com.example.atividadecrud_09_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class InseretextActivity extends AppCompatActivity {

    private TextView txtTexto;
    private RadioGroup radioGroup;
    private Button btnInsere;
    private Button btnCancela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inseretext);
        txtTexto = findViewById(R.id.txtTexto);
        radioGroup = findViewById(R.id.radioGroup);
        btnInsere = findViewById(R.id.btnInsere);
        btnCancela = findViewById(R.id.btnCancela);

        btnInsere.setOnClickListener(new handleInsere());
        btnCancela.setOnClickListener(new handleCancela());
    }

    class handleInsere implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent i = getIntent();
            String produto = txtTexto.getText().toString();
            if (!produto.equals("")) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.radVermelho) {
                    i.putExtra("texto", produto);
                    i.putExtra("cor", "vermelho");
                    setResult(RESULT_OK, i);
                    finish();
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radVerde) {
                    i.putExtra("texto", produto);
                    i.putExtra("cor", "verde");
                    setResult(RESULT_OK, i);
                    finish();
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radAzul) {
                    i.putExtra("texto", produto);
                    i.putExtra("cor", "azul");
                    setResult(RESULT_OK, i);
                    finish();
                } else {
                    Toast.makeText(InseretextActivity.this, "Selecione alguma cor!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(InseretextActivity.this, "Insira algum produto no campo!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class handleCancela implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent i = new Intent();
            setResult(RESULT_CANCELED, i);
            finish();
        }
    }
}