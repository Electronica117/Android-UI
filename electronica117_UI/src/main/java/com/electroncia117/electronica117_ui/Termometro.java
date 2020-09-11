package com.electroncia117.electronica117_ui;

/**
    Edgar Antonio Domínguez Ramírez
    Curso Udemy ESP32
    2020
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;


public class Termometro extends View {

    private float TempMax;
    private float Temperatura;
    private float Temp;
    private Paint BordeTermometro = new Paint();
    private Paint FondoTermometro = new Paint();
    private Paint RellenoTerm = new Paint();
    private Paint LineasTermometro = new Paint();
    private int separacionLineasDeMedicion;
    private String unidad = "°C";
    private float count = 0;
    private Paint TextPaint = new Paint();
    private Path path = new Path();
    private int color = getResources().getColor(R.color.Electronica117);
    private int colorGradient_1 = getResources().getColor(R.color.Gradient_1);
    private int colorGradient_2 = getResources().getColor(R.color.Gradient_2);
    private int colorText = Color.WHITE;
    private Shader linearGradientShader;
    private boolean Gradiente = false;
    private boolean TextoEstatico = false;
    private int TextoTamaño  = 40;
    private int xText;

    public Termometro(Context context) {
        super(context);
    }
    public Termometro(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public Termometro(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public Termometro(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.reset();
        int width = getWidth();
        int height = getHeight();
        ConfigLines();

        canvas.drawRoundRect(new RectF(0, 0, width, height), width/2, width/2, BordeTermometro);
        canvas.drawRoundRect(new RectF(10, 10, width-10, height-10), (width-20)/2, (width-20)/2, FondoTermometro);
        Temp = Temp>TempMax?TempMax:Temp;
        Temperatura = ((10+width-height)/TempMax) * Temp + height - width;
        if(Gradiente){
            linearGradientShader = new LinearGradient(width/2, 0, width/2, height, colorGradient_2, colorGradient_1, Shader.TileMode.MIRROR);
            RellenoTerm.setShader(linearGradientShader);
        }
        canvas.drawRoundRect(new RectF(10, Temperatura, width-10, height-10), (width-20)/2, (width-20)/2, RellenoTerm);
        separacionLineasDeMedicion = (int) (((height-10-((width-20))) - ((width-30)/2)) / 30);
        count=0;
        for(int i=0; i<30; i++){
            path.moveTo(10, ((width-20)/2)+count);
            path.lineTo(width/4, ((width-20)/2)+count);
            count+=separacionLineasDeMedicion;
        }
        canvas.drawPath(path, LineasTermometro);
        xText = width/2-TextoTamaño;
        if (TextoEstatico){
            canvas.drawText(String.valueOf(redondear(Temp)),xText, height-(width/2), TextPaint);
            canvas.drawText(unidad,xText, height-(width/2)+TextoTamaño, TextPaint);
        }else{

            canvas.drawText(String.valueOf(redondear(Temp)),xText, Temperatura+(width/2), TextPaint);
            canvas.drawText(unidad,xText, Temperatura+(width/2)+TextoTamaño, TextPaint);
        }
    }


    private void ConfigLines(){
        BordeTermometro.setColor(Color.BLACK);
        BordeTermometro.setStyle(Paint.Style.FILL);
        FondoTermometro.setColor(Color.WHITE);
        FondoTermometro.setStyle(Paint.Style.FILL);
        RellenoTerm.setColor(color);
        RellenoTerm.setStyle(Paint.Style.FILL);
        LineasTermometro.setColor(Color.BLACK);
        LineasTermometro.setStyle(Paint.Style.STROKE);
        LineasTermometro.setStrokeWidth(3);

        TextPaint.setColor(colorText);
        TextPaint.setStyle(Paint.Style.FILL);
        TextPaint.setTextSize(TextoTamaño);
        TextPaint.setStrokeWidth(1);
        TextPaint.setShadowLayer(Constantes.desenfoque, Constantes.dx, Constantes.dy, Color.argb(Constantes.Alpha, 0, 0, 0));
        TextPaint.setTypeface(Typeface.create("Ari", Typeface.ITALIC));
    }

    public void setTemperature(float temperature) {
        this.Temp = temperature;
        invalidate();
    }

    public void setMax(float tempMax) {
        if (tempMax < 0){
            this.TempMax = 100;
        }else{
            this.TempMax = tempMax;
        }
    }

    private float redondear(float num){
        return (float) (Math.round(num * 100) / 100d);
    }

    public void setUnits(String units) {
        this.unidad = units;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setColorText(int colorText) {
        this.colorText = colorText;
    }

    public void setColorGradient_1(int colorGradient_1) {
        this.colorGradient_1 = colorGradient_1;
    }

    public void setColorGradient_2(int colorGradient_2) {
        this.colorGradient_2 = colorGradient_2;
    }

    public void gradientEnabled(boolean enabled) {
        this.Gradiente = enabled;
    }

    public void staticText(boolean staticText) {
        TextoEstatico = staticText;
    }

    public void setTextSize(int size) {
        TextoTamaño = size;
    }
}
