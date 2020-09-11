package com.electroncia117.electronica117_ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class LED extends View {
    private int R;
    private int G;
    private int B;
    private Paint paint = new Paint();
    private int myColor = 0XFF000000;
    private int sombra = getResources().getColor(com.electroncia117.electronica117_ui.R.color.Sombra);

    public LED(Context context) {
        super(context);
    }

    public LED(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LED(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LED(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int ancho = getWidth();
        int alto = getHeight();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(myColor);
        paint.setShadowLayer(4*Constantes.dx, 0, 0, sombra);
        canvas.drawCircle(ancho/2, alto/2, ancho/2-2*Constantes.dx-2*Constantes.dy, paint);
    }

    public void setR(int r) {
        R = r;
        UpDate();
    }

    public void setG(int g) {
        G = g;
        UpDate();
    }

    public void setB(int b) {
        B = b;
        UpDate();
    }

    public void setRGB(int R, int G, int B){
        this.R = R;
        this.G = G;
        this.B = B;
        UpDate();
    }

    private void UpDate(){
        myColor =   ((int) (1 * 255.0f) << 24) |
                    ((int) (R * 1.0f) << 16) |
                    ((int) (G * 1.0f) <<  8) |
                    (int) (B * 1.0f);
        invalidate();
    }
}
