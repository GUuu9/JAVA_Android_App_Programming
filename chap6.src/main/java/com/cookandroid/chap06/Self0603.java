package com.cookandroid.chap06;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class Self0603 extends TabActivity {
    EditText inputData;
    LinearLayout linearView;
    Button btncircul1, btncircul2;
    TextView textResult;
    String num;
    Double result;

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self0603_main);



        TabHost tabHost = getTabHost();

        inputData = (EditText) findViewById(R.id.inputData);

        btncircul1 = (Button) findViewById(R.id.btncircul1);
        btncircul2 = (Button) findViewById(R.id.btncircul2);

        textResult = (TextView) findViewById(R.id.TextResult);

        TabSpec tabSpec1 = tabHost.newTabSpec("TAG1").setIndicator("강아지");
        tabSpec1.setContent(R.id.imageView1);
        tabHost.addTab(tabSpec1);

        TabSpec tabSpec2 = tabHost.newTabSpec("TAG2").setIndicator("고양이");
        tabSpec2.setContent(R.id.imageView2);
        tabHost.addTab(tabSpec2);

        TabSpec tabSpec3 = tabHost.newTabSpec("TAG3").setIndicator("토끼");
        tabSpec3.setContent(R.id.imageView3);
        tabHost.addTab(tabSpec3);

        TabSpec tabSpec4 = tabHost.newTabSpec("TAG4").setIndicator("말");
        tabSpec4.setContent(R.id.imageView4);
        tabHost.addTab(tabSpec4);

        TabSpec tabSpec5 = tabHost.newTabSpec("TAG5").setIndicator("면적계산");
        tabSpec5.setContent(R.id.linearView);
        tabHost.addTab(tabSpec5);

        tabHost.setCurrentTab(0);

        btncircul1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                num = inputData.getText().toString();

                // num 비어 있다면
                if (num.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "입력 값이 비었습니다", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    result = Double.parseDouble(num) * 3.305785;
                    textResult.setText( result.toString());
                }
            }
        });

        btncircul2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                num = inputData.getText().toString();

                if (num.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "입력 값이 비었습니다", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    result = Double.parseDouble(num) / 3.305785;
                    textResult.setText( result.toString());
                }
            }
        });

    }


}

