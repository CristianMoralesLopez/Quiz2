package com.cristian.quiz2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;

public class Estadistica extends AppCompatActivity {


    private FirebaseDatabase db;
    private TextView[] labels;
    private ArrayList<Integer> respuestas;
    private TextView[] radioButtons;
    private Button actualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica);
        radioButtons = new TextView[20];
        actualizar = findViewById(R.id.actualizar);


        radioButtons[0] = findViewById(R.id.LP1radioButton1);
        radioButtons[1] = findViewById(R.id.LP1radioButton2);
        radioButtons[2] = findViewById(R.id.LP1radioButton3);
        radioButtons[3] = findViewById(R.id.LP1radioButton4);
        radioButtons[4] = findViewById(R.id.LP2radioButton1);
        radioButtons[5] = findViewById(R.id.LP2radioButton2);
        radioButtons[6] = findViewById(R.id.LP2radioButton3);
        radioButtons[7] = findViewById(R.id.LP2radioButton4);
        radioButtons[8] = findViewById(R.id.LP3radioButton1);
        radioButtons[9] = findViewById(R.id.LP3radioButton2);
        radioButtons[10] = findViewById(R.id.LP3radioButton3);
        radioButtons[11] = findViewById(R.id.LP3radioButton4);
        radioButtons[12] = findViewById(R.id.LP4radioButton1);
        radioButtons[13] = findViewById(R.id.LP4radioButton2);
        radioButtons[14] = findViewById(R.id.LP4radioButton3);
        radioButtons[15] = findViewById(R.id.LP4radioButton4);
        radioButtons[16] = findViewById(R.id.LP5radioButton1);
        radioButtons[17] = findViewById(R.id.LP5radioButton2);
        radioButtons[18] = findViewById(R.id.LP5radioButton3);
        radioButtons[19] = findViewById(R.id.LP5radioButton4);

        respuestas = new ArrayList<>();


        db = FirebaseDatabase.getInstance();


         new Thread(new Runnable() {
            @Override
            public void run() {

                WEBUtilDomi webUtilDomi = new WEBUtilDomi();


                try {

                    int j = 1;
                    int k = 1;

                    for (int i = 0; i < 20; i++) {

                        DatabaseReference reference = db.getReference().child("Pregunta " + j).child("opcion " + k);

                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int cuantos = 0;


                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                                    cuantos++;


                                }

                                cambiarNumero(cuantos);
                                Log.e("prueba", "" + cuantos);

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                       // cuantos(reference);

                        String respuesta = webUtilDomi.GETrequest("https://prueba-ed1d4.firebaseio.com/Pregunta " + j + "/opcion " + k + ".json");

                        //  String[] finalmente = respuesta.split(",");

                        //  Log.e("prueba final" ,""+finalmente.length);


                        if (k < 4) {

                            k++;
                        } else if (k == 4) {

                            k = 1;
                            j++;
                        }


                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();









        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(3000);

                    int cantidad = respuestas.get(0) + respuestas.get(1) + respuestas.get(2)+ respuestas.get(3);


                    int [] porcentajes = new int[20];

                    int a =1;
                    int b =1;

                    for (int i =0; i<porcentajes.length;i++){
                        porcentajes [i] = (respuestas.get(i) * 100)/cantidad;

                        String cadena = radioButtons[i].getText().toString();

                        radioButtons[i].setText(cadena + " "+ porcentajes[i]+"%");

                    }





                    actualizar.setEnabled(false);
                } catch (InterruptedException e) {


                    e.printStackTrace();
                }


            }
        });






    }





    public void cambiarNumero(int valor) {

        respuestas.add(valor);
    }
}
