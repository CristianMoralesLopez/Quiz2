package com.cristian.quiz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    private Button btnNuevoRegistro;
    private Button btnEstadisticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnEstadisticas = findViewById(R.id.Estadisticas);
        btnNuevoRegistro = findViewById(R.id.NuevoRegistro);

        btnNuevoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this, Encuesta.class);

                startActivity(i);

            }
        });


    }
}
