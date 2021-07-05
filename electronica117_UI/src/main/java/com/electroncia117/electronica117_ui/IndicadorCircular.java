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

import java.text.DecimalFormat;

public class IndicadorCircular extends View {

    private float TempMax;
    private float Temperatura = 0;
    private float Temp = 0;
    private Paint BordeTermometro = new Paint();
    private Paint FondoTermometro = new Paint();
    private Paint RellenoTerm = new Paint();
    private Paint TextPaint = new Paint();
    private Paint IndicatorPaint = new Paint();
    private String unidad = "°C";
    private int color = getResources().getColor(R.color.Electronica117);
    private int colorGradient_1 = getResources().getColor(R.color.Gradient_1);
    private int colorGradient_2 = getResources().getColor(R.color.Gradient_2);
    private int colorText = Color.BLACK;
    private Shader linearGradientShader;
    private boolean Gradiente = false;
    private int TextoTamaño;
    private int xText;
    private int sizeLine;
    private int sizeBorde = 10;
    private float radioIndicator;

    public IndicadorCircular(Context context) {
        super(context);
    }
    public IndicadorCircular(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public IndicadorCircular(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public IndicadorCircular(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
        int width = getWidth();
        int height = getHeight();
        TextoTamaño = width/6;
        ConfigLines();

        if(Gradiente){
            linearGradientShader = new LinearGradient(0, height/2, width, height/2, colorGradient_1, colorGradient_2, Shader.TileMode.MIRROR);
            RellenoTerm.setShader(linearGradientShader);
            IndicatorPaint.setShader(linearGradientShader);
        }
        Temp = Temp>TempMax?TempMax:Temp;
        Temperatura = (270/TempMax)*Temp;

        sizeLine = Math.round((width/7 + height/8)/2);
        radioIndicator = width/2-sizeBorde-sizeLine/2;

        canvas.drawArc(new RectF(sizeBorde+(sizeBorde/2), sizeBorde+(sizeBorde/2), width-sizeBorde-(sizeBorde/2), height-sizeBorde-(sizeBorde/2)), 135, Temperatura, true, RellenoTerm);
        canvas.drawCircle((float) (width/2+((radioIndicator)*Math.cos(GradToRad(Temperatura+135)))), (float) (height/2+((radioIndicator)*Math.sin(GradToRad(Temperatura+135)))), sizeLine/2, IndicatorPaint);

        canvas.drawArc(new RectF(sizeBorde, sizeBorde, width-sizeBorde, height-sizeBorde), 45,90, true, FondoTermometro);
        canvas.drawArc(new RectF(sizeBorde, sizeBorde, width-sizeBorde, height-sizeBorde), 135,270, true, BordeTermometro);
        canvas.drawArc(new RectF(sizeLine+sizeBorde, sizeLine+sizeBorde, width-sizeLine-sizeBorde, height-sizeLine-sizeBorde), 135,270, true, BordeTermometro);
        canvas.drawCircle(width/2, height/2, (width/2+(sizeBorde/2))-(2*sizeBorde)-(sizeLine), FondoTermometro);

        xText = width*3/8;

        canvas.drawText(String.valueOf(redondear(Temp)),xText, height/2, TextPaint);
        canvas.drawText(unidad,xText, height-(width/2)+TextoTamaño, TextPaint);
    }

    private float GradToRad(float grados){
        return (float) (grados*Math.PI/180.00);
    }

    private void ConfigLines(){
        BordeTermometro.setColor(Color.BLACK);
        BordeTermometro.setStyle(Paint.Style.STROKE);
        BordeTermometro.setStrokeWidth(sizeBorde);

        FondoTermometro.setColor(Color.WHITE);
        FondoTermometro.setStyle(Paint.Style.FILL);

        RellenoTerm.setColor(color);
        RellenoTerm.setStyle(Paint.Style.FILL);

        TextPaint.setColor(colorText);
        TextPaint.setStyle(Paint.Style.FILL);
        TextPaint.setTextSize(TextoTamaño);
        TextPaint.setShadowLayer(Constantes.desenfoque, Constantes.dx, Constantes.dy, Color.argb(Constantes.Alpha, 0, 0, 0));
        TextPaint.setTypeface(Typeface.create("Ari", Typeface.ITALIC));

        IndicatorPaint.setColor(color);
        IndicatorPaint.setStyle(Paint.Style.FILL);
        IndicatorPaint.setStrokeWidth(3);
    }

    private float redondear(float num){
        return (float) (Math.round(num * 100) / 100d);
    }

    public void setValue(float Value) {
        this.Temp = Value;
        invalidate();
    }

    public void setMax(float tempMax) {
        if (tempMax < 0){
            this.TempMax = 100;
        }else{
            this.TempMax = tempMax;
        }
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

}
