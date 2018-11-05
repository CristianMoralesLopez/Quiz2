package com.cristian.quiz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Encuesta extends AppCompatActivity {

    private RadioGroup[] groups;
    private RadioButton[] radioButtons;
    private TextView[] labels;
    private String[] respuestas;
    private Button btnEnviarEncuesta;
    private FirebaseDatabase db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);

        db = FirebaseDatabase.getInstance();


        groups = new RadioGroup[5];
        radioButtons = new RadioButton[20];
        labels = new TextView[5];
        respuestas = new String[5];
        btnEnviarEncuesta = findViewById(R.id.btnEnviarEncuesta);

        groups[0] = findViewById(R.id.radio1);
        groups[1] = findViewById(R.id.radio2);
        groups[2] = findViewById(R.id.radio3);
        groups[3] = findViewById(R.id.radio4);
        groups[4] = findViewById(R.id.radio5);

        radioButtons[0] = findViewById(R.id.P1radioButton1);
        radioButtons[1] = findViewById(R.id.P1radioButton2);
        radioButtons[2] = findViewById(R.id.P1radioButton3);
        radioButtons[3] = findViewById(R.id.P1radioButton4);
        radioButtons[4] = findViewById(R.id.P2radioButton1);
        radioButtons[5] = findViewById(R.id.P2radioButton2);
        radioButtons[6] = findViewById(R.id.P2radioButton3);
        radioButtons[7] = findViewById(R.id.P2radioButton4);
        radioButtons[8] = findViewById(R.id.P3radioButton1);
        radioButtons[9] = findViewById(R.id.P3radioButton2);
        radioButtons[10] = findViewById(R.id.P3radioButton3);
        radioButtons[11] = findViewById(R.id.P3radioButton4);
        radioButtons[12] = findViewById(R.id.P4radioButton1);
        radioButtons[13] = findViewById(R.id.P4radioButton2);
        radioButtons[14] = findViewById(R.id.P4radioButton3);
        radioButtons[15] = findViewById(R.id.P4radioButton4);
        radioButtons[16] = findViewById(R.id.P5radioButton1);
        radioButtons[17] = findViewById(R.id.P5radioButton2);
        radioButtons[18] = findViewById(R.id.P5radioButton3);
        radioButtons[19] = findViewById(R.id.P5radioButton4);

        labels[0] = findViewById(R.id.lblPregunta1);
        labels[1] = findViewById(R.id.lblPregunta2);
        labels[2] = findViewById(R.id.lblPregunta3);
        labels[3] = findViewById(R.id.lblPregunta4);
        labels[4] = findViewById(R.id.lblPregunta5);

        btnEnviarEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j = 0;
                int cuantos = 0;
                for (int i = 0; i < radioButtons.length; i++) {


                    if (radioButtons[i].isChecked()) {


                        respuestas[j] = radioButtons[i].getText().toString();

                        labels[j].setText(radioButtons[i].getText().toString());
                        j++;

                        cuantos++;

                    }
                }

                if (cuantos == 5) {

                    Toast.makeText(Encuesta.this, "Correcto", Toast.LENGTH_SHORT).show();


                    int[] opciones = new int[5];


                    for (int i = 0; i < opciones.length; i++) {

                        String temporal =""+respuestas[i].charAt(0);

                        if (temporal.equals("a")) {

                            opciones[i] = 1;

                        } else if (temporal.equals("b")) {
                            opciones[i] = 2;

                        } else if (temporal.equals("c")) {
                            opciones[i] = 3;

                        } else {
                            opciones[i] = 4;

                        }


                    }


                    for(int i= 0; i<5;i++){

                        DatabaseReference reference = db.getReference().child("Pregunta " + (i+1)).child("opcion "+ opciones[i]);

                        reference.push().setValue(respuestas[i]);

                    }


                    Intent i = new Intent(Encuesta.this,Principal.class);

                    startActivity(i);


                } else {
                    Toast.makeText(Encuesta.this, "incorrecto", Toast.LENGTH_SHORT).show();
                }


            }


        });


    }
}
