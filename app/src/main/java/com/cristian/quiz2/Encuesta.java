package com.cristian.quiz2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Encuesta extends AppCompatActivity {

    private RadioGroup[] groups;
    private RadioButton[] radioButtons;
    private TextView[]  labels;
    private String [] respuestas;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);



        groups = new RadioGroup[5];
        radioButtons = new RadioButton[20];
        labels =  new TextView[5];

        groups [0] = findViewById(R.id.radio1);
        groups [1] = findViewById(R.id.radio2);
        groups [2] = findViewById(R.id.radio3);
        groups [3] = findViewById(R.id.radio4);
        groups [4] = findViewById(R.id.radio5);

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






    }
}
