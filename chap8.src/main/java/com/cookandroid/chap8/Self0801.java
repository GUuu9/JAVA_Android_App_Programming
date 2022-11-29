package com.cookandroid.chap8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Self0801 extends AppCompatActivity {

    DatePicker dp;
    EditText edtDiary;
    Button load;
    Button save;
    Button cancel;
    String fileName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self0801_main);
        setTitle("간단 일기장");

        dp = (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        load = (Button) findViewById(R.id.btnload);
        save = (Button) findViewById(R.id.btnsave);
        cancel = (Button) findViewById(R.id.btncancel);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        // 처음 실행시에 설정할 내용
        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth+1)
                + "_" + Integer.toString(cDay) + ".txt";
        String str = readDiary2(fileName);
        edtDiary.setText(str);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_"
                        + Integer.toString(monthOfYear + 1) + "_"
                        + Integer.toString(dayOfMonth) + ".txt";
                String str = readDiary2(fileName);
                edtDiary.setText(str);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName,
                            Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(),
                            fileName + " 이  저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                }
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String diaryStr = null;
                FileInputStream inFs;
                try {
                    inFs = openFileInput(fileName);
                    byte[] txt = new byte[500];
                    inFs.read(txt);
                    inFs.close();
                    save.setText("수정하기");
                    String a = (new String(txt)).trim();
                    edtDiary.setText(a);
                } catch (IOException e) {
                    edtDiary.setHint("일기가 없습니다.");
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String diaryStr = null;
                FileInputStream inFs;
                try {
                    inFs = openFileInput(fileName);
                    byte[] txt = new byte[500];
                    inFs.read(txt);
                    inFs.close();
                    String a = (new String(txt)).trim();
                    edtDiary.setText(null);
                    edtDiary.setHint("일기 있습니다");
                } catch (IOException e) {
                    edtDiary.setHint("일기가 없습니다");
                    save.setText("새로 저장");
                }
            }
        });
    }


    String readDiary2(String fName) {
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fName);
            inFs.close();
            edtDiary.setHint("일기가 있습니다");
        } catch (IOException e) {
            edtDiary.setHint("일기가 없습니다");
            save.setText("새로 저장");
        }
        save.setText("새로 저장");
        return diaryStr;
    }

}