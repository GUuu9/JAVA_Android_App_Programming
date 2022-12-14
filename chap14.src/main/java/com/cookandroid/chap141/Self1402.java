package com.cookandroid.chap141;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Self1402 extends AppCompatActivity {

    ImageView ivBattery;
    EditText edtBattery;
    TextView interPrint;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self1402_main);

        Button interCheck = (Button) findViewById(R.id.interCheck);
        interPrint = (TextView) findViewById(R.id.interPrint);
        interCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();

                if(networkInfo == null) {
                    interPrint.setText("연결 안됨");
                }
                else if (networkInfo.getType()==ConnectivityManager.TYPE_MOBILE) {
                    interPrint.setText("3G/LTE 연결");
                }
                else if (networkInfo.getType()==ConnectivityManager.TYPE_WIFI) {
                    interPrint.setText("WIFI 연결");
                }
            }
        });


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.battery_icon);
        setTitle("배터리 상태 체크 (개선)");

        ivBattery = (ImageView) findViewById(R.id.ivBattery);
        edtBattery = (EditText) findViewById(R.id.edtBattery);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br, iFilter);
    }

    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                edtBattery.setText("현재 충전량 : " + remain + " %\n");

                if (remain >= 90)
                    ivBattery.setImageResource(R.drawable.battery_100);
                else if (remain >= 70)
                    ivBattery.setImageResource(R.drawable.battery_80);
                else if (remain >= 50)
                    ivBattery.setImageResource(R.drawable.battery_60);
                else if (remain >= 10)
                    ivBattery.setImageResource(R.drawable.battery_20);
                else
                    ivBattery.setImageResource(R.drawable.battery_0);

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                switch (plug) {
                    case 0:
                        edtBattery.append("전원 연결 : 안됨\n");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        edtBattery.append("전원 연결 : 어댑터 연결됨\n");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        edtBattery.append("전원 연결 : USB 연결됨\n");
                        break;
                }

                // 배터리 상태 출력
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
                switch (status) {
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        Toast.makeText(getApplicationContext(), "배터리 상태: 현재 충전중임",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        Toast.makeText(getApplicationContext(),
                                "배터리 상태: 현재 충전중 아님", Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        Toast.makeText(getApplicationContext(),
                                "배터리 상태: 충전 100% 완료", Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        Toast.makeText(getApplicationContext(), "배터리 상태: 방전됨", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),
                                "배터리 상태: 상태 알 수 없음", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };

}
