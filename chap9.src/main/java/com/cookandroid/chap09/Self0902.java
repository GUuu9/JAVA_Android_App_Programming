package com.cookandroid.chap09;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

import java.util.ArrayList;

public class Self0902 extends AppCompatActivity {

    final static int LINE = 1, CIRCLE = 2, RECTANGLE = 3, ROUND_RECT = 4, ROUND_LINE = 5;
    static int curShape = LINE;
    static int curColor = Color.DKGRAY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("간단 그림판 (개선)");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "선 그리기");
        menu.add(0, 2, 0, "원 그리기");
        menu.add(0, 3, 0, "사각형 그리기");
        menu.add(0, 4, 0, "곡선그리기");
        menu.add(0, 5, 0, "둥근사각형 그리기");
        SubMenu sMenu = menu.addSubMenu("색상 변경 >>");
        sMenu.add(0, 6, 0, "빨강");
        sMenu.add(0, 7, 0, "초록");
        sMenu.add(0, 8, 0, "파랑");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case 1:
                curShape = LINE; // 선
                return true;
            case 2:
                curShape = CIRCLE; // 원
                return true;
            case 3:
                curShape = RECTANGLE; // 사각형
                return true;
            case 4:
                curShape = ROUND_LINE; // 곡선
                return true;
            case 5:
                curShape = ROUND_RECT; // 둥근사각형
                return true;
            case 6:
                curColor = Color.RED;
                return true;
            case 7:
                curColor = Color.GREEN;
                return true;
            case 8:
                curColor = Color.BLUE;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class MyGraphicView extends View {
        int startX = -1, startY = -1, stopX = -1, stopY = -1;
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
            }
            return true;
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(curColor);

            switch (curShape) {
                case LINE:
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;
                case CIRCLE:
                    paint.setStyle(Paint.Style.STROKE);
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)
                            + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                case RECTANGLE:
                    paint.setStyle(Paint.Style.STROKE);
                    Rect rect = new Rect(startX, startY, stopX, stopY);
                    canvas.drawRect(rect, paint);
                    break;
                case ROUND_RECT:
                    paint.setStyle(Paint.Style.FILL);
                    RectF rect1 = new RectF(startX, startY, stopX, stopY);
                    canvas.drawRoundRect(rect1,20,20, paint);
                    break;
                case ROUND_LINE:
                    Path path = new Path();
                    path.moveTo(startX, startY);
                    path.cubicTo(startX, startY,startX+stopX , startY+stopY, stopX, stopY);
                    canvas.drawPath(path, paint);
                    path.reset();
                    break;
            }
        }

    }
}