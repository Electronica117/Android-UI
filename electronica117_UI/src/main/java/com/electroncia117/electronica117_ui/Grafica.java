package com.electroncia117.electronica117_ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 Edgar Antonio Domínguez Ramírez
 Curso Udemy ESP32
 2020
 */

public class Grafica extends View {

    private int width;
    private int height;
    private int MaxValueY = 100;
    private int MaxValueX = 500;
    private float y=0;
    private float x=0;
    private int t=0;
    private boolean center = false;
    private Paint LinePaint = new Paint();
    private Paint BorderPaint = new Paint();
    private Paint DotPaint = new Paint();
    private Paint TexPaint = new Paint();
    private Paint FondoPaint = new Paint();
    private Paint CenterLinePaint = new Paint();
    private Path myPath = new Path();
    private int LineColor = Color.WHITE;
    private String text = "°C";
    private boolean staticText = false;
    private boolean enabledValue = true;
    private int textSize = 50;
    private float value;
    private boolean setPath = true;
    private boolean enabledBorder = true;
    private int borderColor = getResources().getColor(R.color.Negro);
    private int borderSize = 20;
    private int colorGradient_1 = getResources().getColor(R.color.Gradient_1);
    private int colorGradient_2 = getResources().getColor(R.color.Gradient_2);
    private Shader linearGradientShader;
    private boolean Gradiente = true;
    private int fondoColor = getResources().getColor(R.color.Electronica117);
    private int lineSize = 5;

    public Grafica(Context context) {
        super(context);
    }
    public Grafica(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public Grafica(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public Grafica(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = getWidth();
        height = getHeight();
        InitPaints();
        if (setPath){
            myPath.moveTo(-100, 0);
            myPath.moveTo(-100, height/2);
            setPath = false;
        }

        if(Gradiente){
            linearGradientShader = new LinearGradient(width/2, 0, width/2, height, colorGradient_2, colorGradient_1, Shader.TileMode.MIRROR);
            FondoPaint.setShader(linearGradientShader);
        }
        canvas.drawRect(0,0,width,height,FondoPaint);

        if(t<=MaxValueX){
            if (center){
                canvas.drawLine(0, height/2, width, height/2, CenterLinePaint);
                myPath.lineTo(x,height/2-y);
                canvas.drawCircle(x, height/2-y, lineSize+1, DotPaint);
                if (enabledValue){
                    if (staticText){
                        canvas.drawText(redondear(value) + " " +text, 5, textSize+5,TexPaint);
                    }else{
                        canvas.drawText(redondear(value) + " " +text, x+15, height/2-y+20,TexPaint);
                    }
                }

            }else{
                myPath.lineTo(x,height-y);
                canvas.drawCircle(x, height-y, lineSize+1, DotPaint);
                if (enabledValue){
                    if (staticText){
                        canvas.drawText(redondear(value) + " " +text, 5, textSize+5,TexPaint);
                    }else{
                        canvas.drawText(redondear(value) + " " +text, x+15, height-y+20,TexPaint);
                    }
                }
            }
        }else{
            myPath.reset();
            if (center){
                myPath.moveTo(-100, height/2);
            }else{
                myPath.moveTo(-100, height);
            }
            t = 0;
        }
        canvas.drawPath(myPath, LinePaint);

        if (enabledBorder){
            canvas.drawRect(0,0,width,height,BorderPaint);
        }
    }

    public void setValue(float y){
        this.y = RedimY(y);
        this.value = y;
        this.x = RedimX(t);
        t++;
        invalidate();
    }

    public void setLineColor(int lineColor) {
        LineColor = lineColor;
    }

    private float redondear(float num){
        return (float) (Math.round(num * 10) / 10d);
    }

    private void InitPaints(){
        LinePaint.setColor(LineColor);
        LinePaint.setStyle(Paint.Style.STROKE);
        LinePaint.setStrokeWidth(lineSize);
        LinePaint.setShadowLayer(Constantes.desenfoque, Constantes.dx, Constantes.dy, Color.argb(Constantes.Alpha, 0, 0, 0));

        CenterLinePaint.setColor(LineColor);
        CenterLinePaint.setStyle(Paint.Style.STROKE);
        CenterLinePaint.setStrokeWidth(2);

        TexPaint.setColor(LineColor);
        TexPaint.setStyle(Paint.Style.FILL);
        TexPaint.setTextSize(textSize);
        TexPaint.setShadowLayer(Constantes.desenfoque, Constantes.dx, Constantes.dy, Color.argb(Constantes.Alpha, 0, 0, 0));

        DotPaint.setColor(LineColor);
        DotPaint.setStyle(Paint.Style.FILL);
        DotPaint.setShadowLayer(Constantes.desenfoque, Constantes.dx, Constantes.dy, Color.argb(Constantes.Alpha, 0, 0, 0));

        BorderPaint.setColor(borderColor);
        BorderPaint.setStyle(Paint.Style.STROKE);
        BorderPaint.setStrokeWidth(borderSize);

        FondoPaint.setColor(fondoColor);
        FondoPaint.setStyle(Paint.Style.FILL);
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public void borderEnabled(boolean borderEnabled) {
        this.enabledBorder = borderEnabled;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public void Center(boolean center) {
        this.center = center;
    }

    private float RedimY(float valor){
        return ((height*valor)/MaxValueY);
    }

    private float RedimX(float valor){
        return ((width*valor)/MaxValueX);
    }

    public void setMaxAmplitude(int Amplitude) {
        MaxValueY = Amplitude;
        myPath.reset();
    }

    public void setMaxX(int maxValueX) {
        MaxValueX = maxValueX;
        myPath.reset();
    }

    public void setUnits(String units) {
        this.text = units;
    }

    public void valueEnabled(boolean valueEnabled) {
        this.enabledValue = valueEnabled;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void staticText(boolean staticText) {
        this.staticText = staticText;
    }

    public void gradientEnabled(boolean gradiente) {
        Gradiente = gradiente;
    }

    public void setColorGradient_1(int colorGradient_1) {
        this.colorGradient_1 = colorGradient_1;
    }

    public void setColorGradient_2(int colorGradient_2) {
        this.colorGradient_2 = colorGradient_2;
    }

    public void setColor(int Color) {
        this.fondoColor = Color;
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
    }
}