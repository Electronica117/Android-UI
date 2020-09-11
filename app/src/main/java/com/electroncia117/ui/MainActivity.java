package com.electroncia117.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;

import com.electroncia117.electronica117_ui.ColorPiker;
import com.electroncia117.electronica117_ui.Grafica;
import com.electroncia117.electronica117_ui.Termometro;
import com.electroncia117.electronica117_ui.TermometroCircular;

/**
 Edgar Antonio Domínguez Ramírez
 Curso Udemy ESP32
 2020
 */

public class MainActivity extends AppCompatActivity {

    SeekBar bar;
    SeekBar barG;
    SeekBar barB;
    Termometro term;
    TermometroCircular termometroCircular;
    Grafica graf;
    ColorPiker piker;
    float[] test = new float[724];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        term = findViewById(R.id.Termometro);
        bar = findViewById(R.id.seekBar);
        barG = findViewById(R.id.BarG);
        barB = findViewById(R.id.BarB);
        graf = findViewById(R.id.Grafica);
        piker = findViewById(R.id.Piker);

        termometroCircular = findViewById(R.id.termometroCircular);
        bar.setMax(255);
        barB.setMax(255);
        barG.setMax(255);

        term.setMax(255);
        term.gradientEnabled(true);
        term.staticText(true);
        term.setTextSize(40);

        termometroCircular.setUnits("%h");
        termometroCircular.gradientEnabled(true);
        termometroCircular.setMax(255);


        graf.valueEnabled(true);
        graf.setMaxAmplitude(255);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                term.setTemperature(progress);
                termometroCircular.setTemperature(progress);
                graf.upDate(progress);
                piker.setR(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        barG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               piker.setG(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        barB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               piker.setB(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
}