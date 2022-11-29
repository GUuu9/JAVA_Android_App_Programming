package com.cookandroid.chap10;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        Button btnsel = (Button) findViewById(R.id.btnsel);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        btnsel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "버튼 눌렀습니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}