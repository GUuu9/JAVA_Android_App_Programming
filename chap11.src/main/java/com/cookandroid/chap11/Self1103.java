package com.cookandroid.chap11;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class Self1103 extends AppCompatActivity  {
    MyGraphicView graphicView;
    static Integer selectedPoster;

    static float scaleX = 1, scaleY = 1;
    static float angle = 0;
    static float skewX = 0, skewY = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self1103_main);
        setTitle("스피너 테스트");

        LinearLayout pictureLayout = (LinearLayout) findViewById(R.id.pictureLayout);
        graphicView = (MyGraphicView) new MyGraphicView(this);
        pictureLayout.addView(graphicView);

        registerForContextMenu(graphicView);

        final String[] movie = { "아바타", "힘을내요 미스터리", "포드VS페라리", "쥬만지", "대부", "국가대표", "토이스토리3", "마당을 나온 암탉", "죽은 시인의 사회", "서유기" };

        final Integer[] posterID = { R.drawable.mov21, R.drawable.mov22,
                R.drawable.mov23, R.drawable.mov24, R.drawable.mov25,
                R.drawable.mov26, R.drawable.mov27, R.drawable.mov28,
                R.drawable.mov29, R.drawable.mov30 };

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, movie);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                selectedPoster = posterID[arg2];
                graphicView.invalidate();
                Toast.makeText(getApplicationContext(), movie[arg2] + "(num : " + (arg2 + 1) + ")",
                        Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }

        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case 1:
                angle = angle + 20;
                graphicView.invalidate();
                return true;
            case 2:
                angle = angle - 20;
                graphicView.invalidate();
                return true;
            case 3:
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                graphicView.invalidate();
                return true;
            case 4:
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                graphicView.invalidate();
                return true;
            case 5:
                skewX = skewX + 0.1f;
                skewY = skewY + 0.1f;
                graphicView.invalidate();
                return true;
            case 6:
                skewX = skewX - 0.1f;
                skewY = skewY - 0.1f;
                graphicView.invalidate();
                return true;
        }

        return super.onContextItemSelected(item);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        if (v == graphicView) {
            menu.add(0, 1, 0, "우측회전");
            menu.add(0, 2, 0, "좌측회전");
            menu.add(0, 3, 0, "확대");
            menu.add(0, 4, 0, "축소");
            menu.add(0, 5, 0, "기울기 증가");
            menu.add(0, 6, 0, "기울기 감소");
        }

    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;

            canvas.skew(skewX, skewY);
            canvas.scale(scaleX, scaleY, cenX, cenY);
            canvas.rotate(angle, cenX, cenY);

            Bitmap picture = BitmapFactory.decodeResource(getResources(),
                    selectedPoster);
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture, picX, picY, null);

            picture.recycle();
        }
    }

}