package com.cookandroid.chap10;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        final RadioButton rdoidol = (RadioButton) findViewById(R.id.rdoSecond);

        Button btnselect = (Button) findViewById(R.id.btnselect);

        final RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroupidol);
        Button b = (Button)findViewById(R.id.rdoidol1);
        final TextView tv = (TextView)findViewById(R.id.textViewselect);

        btnselect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(id);
                tv.setText("결과: " + rb.getText().toString());
            }
        });

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

}