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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica);

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


                        cuantos(reference);

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


    }


    public void cuantos(DatabaseReference reference1) {


        DatabaseReference reference = reference1;


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


        for (int i = 0; i < respuestas.size(); i++) {

            //  Log.e("ArrayList",""+respuestas.get(i));


        }


    }


    public void cambiarNumero(int valor) {

        respuestas.add(valor);
    }
}
