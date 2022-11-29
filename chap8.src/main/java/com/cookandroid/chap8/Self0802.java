package com.cookandroid.chap8;

import java.io.File;

import android.graphics.Color;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Self0802 extends AppCompatActivity {

    Button btnPrev, btnNext;
    myPictureView myPicture;
    TextView tvNumber;
    int curNum=1;
    File[] imageFiles;
    String imageFname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self0802_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        setTitle("간단 이미지 뷰어 (변경)");
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        tvNumber = (TextView) findViewById(R.id.tvNumber);
        myPicture = (myPictureView) findViewById(R.id.myPictureView1);

        imageFiles = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/Pictures").listFiles();
        imageFname = imageFiles[0].toString();
        myPicture.imagePath = imageFname;

        tvNumber.setText("1" + "/" + (imageFiles.length-1));

        btnPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curNum <= 1) {
                    curNum = imageFiles.length - 1;
                } else {
                    curNum--;
                }
                imageFname = imageFiles[curNum-1].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
                tvNumber.setText((curNum) + "/" + (imageFiles.length-1));

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curNum >= imageFiles.length - 1) {
                    curNum = 1;
                } else {
                    curNum++;
                }
                imageFname = imageFiles[curNum-1].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
                tvNumber.setText((curNum) + "/" + (imageFiles.length-1));
            }
        });

        myPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String[] tstMsg = {"루느아르 1", "2 레스토랑 푸르네즈에서의 점심 식사", "3 아레느 장 단제로 앞의 초성", "루느아르 4", "5 부자들의 무도회",
                "루느아르 6", "루느아르 7", "루느아르 8","루느아르 9","루느아르 10"};
                if(curNum == 0) curNum++;
                Toast tMsg = Toast.makeText(getApplicationContext(),tstMsg[curNum-1], Toast.LENGTH_LONG);
                tMsg.show();
            }

        });
    }

}