package com.cookandroid.chap12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Self1201 extends AppCompatActivity {
    Button btn1;
    EditText editText;
    myDB myDbHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self1201_main);
        setTitle("가수 그룹 관리 DB (수정)");

        btn1 = (Button)findViewById(R.id.btn);
        editText = (EditText)findViewById(R.id.edit);
        myDbHelper = new myDB(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDbHelper.getReadableDatabase();
                Cursor cursor = sqlDB.rawQuery("SELECT * FROM product;", null);

                String edittext = "제품이름"+"\t"+"가격"+"\t"+"제조 일자"+"\t"+"제조 회사"+"\t"+"남은 수량"+"\n";

                while(cursor.moveToNext()){
                    edittext += cursor.getString(0) + "\t"
                            + cursor.getString(1) + "\t"
                            + cursor.getString(2) + "\t"
                            + cursor.getString(3) + "\t"
                            + cursor.getString(4) + "\n";
                }

                editText.setText(edittext);
                cursor.close();
                sqlDB.close();
            }
        });

    }

    public class myDB extends SQLiteOpenHelper{

        myDB(Context context){
            super(context, "myDB", null, 1);
        }
        //위에서 super클래스를 통해 데이터베이스의 파일 명을 생성한다.

        @Override
        public void onCreate(SQLiteDatabase db) {
            //데이터 삽입
            db.execSQL("CREATE TABLE product ( name char(20) PRIMARY KEY, price INTEGER, date CHAR(20), company CHAR(20), remain INTEGER);");
            db.execSQL("INSERT INTO product(pname, price, date, com, count) VALUES ('TV', 100,'2017.7.22', 'Samsung', 55);");
            db.execSQL("INSERT INTO product(pname, price, date, com, count) VALUES ('Computer', 150, '2019.5.5', 'LG', 7);");
            db.execSQL("INSERT INTO product(pname, price, date, com, count) VALUES ('Monitor', 50, '2021.9.9', 'Daewoo', 33);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS product");
            onCreate(db);
        }
    }
}