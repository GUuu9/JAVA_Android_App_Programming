package com.cookandroid.chap07;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.chap07.R;

public class Self0703 extends AppCompatActivity {

    TextView tvName, tvEmail, tvFruit;
    Button button1;
    EditText dlgEdtName, dlgEdtEmail;
    TextView toastText;
    View dialogView, toastView;
    final String[] versionArray = new String[] { "사과", "배", "귤", "수박" };
    final boolean[] checkArray = new boolean[] { false, false, false, false };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self0703_main);
        setTitle("사용자 정보 입력 (수정)");

        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvFruit = (TextView) findViewById(R.id.tvFruit);
        button1 = (Button) findViewById(R.id.button1);


        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogView = (View) View.inflate(Self0703.this,
                        R.layout.self0703_dialog, null);


                AlertDialog.Builder dlg = new AlertDialog.Builder(
                        Self0703.this);

                dlg.setMultiChoiceItems(versionArray, checkArray,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                tvFruit.setText("좋아하는 과일 : ");
                                for(int i = 0; i<checkArray.length; i++)
                                {
                                    if(checkArray[i])
                                    {
                                        tvFruit.append(versionArray[i] + ", ");
                                    }
                                }
                            }
                        });
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView(dialogView);
                dlgEdtName = (EditText) dialogView.findViewById(R.id.dlgEdt1);
                dlgEdtEmail = (EditText) dialogView.findViewById(R.id.dlgEdt2);
                dlgEdtName.setText(tvName.getText().toString());
                dlgEdtEmail.setText(tvEmail.getText().toString());
                dlg.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                tvName.setText("이름:" + dlgEdtName.getText().toString());
                                tvEmail.setText("이메일:" + dlgEdtEmail.getText()
                                        .toString());
                            }
                        });

                dlg.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast toast = new Toast(Self0703.this);

                                Display display = ((WindowManager) getSystemService(WINDOW_SERVICE))
                                        .getDefaultDisplay();
                                int xOffset = (int) (Math.random() * display.getWidth());
                                int yOffset = (int) (Math.random() * display.getHeight());

                                toast.setGravity(Gravity.TOP | Gravity.LEFT,
                                        xOffset, yOffset);

                                toastView = (View) View.inflate(
                                        Self0703.this, R.layout.self0703_toast, null);
                                toastText = (TextView) toastView
                                        .findViewById(R.id.toastText1);
                                toastText.setText("취소했습니다");
                                toast.setView(toastView);
                                toast.show();
                            }
                        });
                dlg.show();

            }
        });

    }

}