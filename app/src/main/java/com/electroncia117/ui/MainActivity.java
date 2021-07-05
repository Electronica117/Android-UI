package com.electroncia117.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.electroncia117.electronica117_ui.Grafica;
import com.electroncia117.electronica117_ui.IndicadorCircular;
import com.electroncia117.electronica117_ui.Termometro;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;

/**
 Edgar Antonio Domínguez Ramírez
 Curso Udemy ESP32
 2020
 */

public class MainActivity extends AppCompatActivity {

    private IndicadorCircular indicador;
    private IndicadorCircular indicador2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicador = findViewById(R.id.indicadorCircular);
        indicador2 = findViewById(R.id.indicadorCircular2);

        indicador.setMax(200);
        indicador.setColor(Color.RED);
        indicador.setValue(117);
        indicador.setUnits("°C");

        indicador2.setMax(200);
        indicador2.setValue(117);
        indicador2.setColorGradient_1(Color.BLUE);
        indicador2.setColorGradient_2(Color.RED);
        indicador2.gradientEnabled(true);
        indicador2.setUnits("°F");

    }

}