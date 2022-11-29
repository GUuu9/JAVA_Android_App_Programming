package com.cookandroid.chap141;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Self1401 extends AppCompatActivity {

    Intent intent;
    Button btnStart, btnStop;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self1401_main);
        setTitle("서비스 테스트 예제(개선)");

        intent = new Intent(this, MusicService.class);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                android.util.Log.i("메시지", "btnStart Click()");
                startService(intent);
                Toast.makeText(getApplicationContext(), "startService()", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                android.util.Log.i("메시지", "btnStop Click()");
                stopService(intent);
                Toast.makeText(getApplicationContext(), "stopService()",  Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
