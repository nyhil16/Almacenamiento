package com.example.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private Button bt1, bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditText
        et1 = findViewById(R.id.etName);
        et2 = findViewById(R.id.etTelDir);
        SharedPreferences preferences = getSharedPreferences("Informacion", Context.MODE_PRIVATE);
        et2.setText(preferences.getString(et1.getText().toString(), ""));

        //Button
        bt1 = findViewById(R.id.btnGuardar);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("Informacion", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = preferences.edit().putString(et1.getText().toString(), et2.getText().toString());
                prefEditor.commit();

                et1.setText("");
                et2.setText("");
            }
        });

        bt2 = findViewById(R.id.btnBuscar);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("Informacion", Context.MODE_PRIVATE);
                String savedPref = preferences.getString(et1.getText().toString(), null);
                if (preferences.contains(et1.getText().toString())) {
                    et2.setText(savedPref);
                } else {
                    et2.setText("No se encontro registro");
                }
            }
        });
    }
}