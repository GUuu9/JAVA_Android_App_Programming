package com.cookandroid.chap09;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class Self0901 extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            paint.setStrokeWidth(30);
            paint.setColor(Color.RED);
            canvas.drawLine(100, 30, 900, 900, paint);

            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setColor(Color.GREEN);
            canvas.drawLine(100, 900, 900, 30, paint);

            RectF rectF = new RectF();

            paint.setColor(Color.BLACK);
            RectF rect = new RectF();
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            rect.set(100, 950, 900, 1200);
            canvas.drawArc(rect, 0, 360, true, paint);

            rectF.set(450, 950, 600, 1100);
            canvas.drawArc(rectF, 45, 90, true, paint);

            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            rectF.set(50, 300, 300, 550);
            canvas.drawRect(rectF, paint);

            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth((float)0.5);
            rectF.set(700, 300, 950, 550);
            canvas.drawRoundRect(rectF,15,15, paint);

            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            rectF.set(450, 150, 550, 250);
            canvas.drawRect(rectF, paint);


            paint.setColor(Color.argb(0x88, 0xff, 0x00, 0x00));
            rectF.set(450, 750, 550, 850);
            canvas.drawRect(rectF, paint);

        }
    }

}