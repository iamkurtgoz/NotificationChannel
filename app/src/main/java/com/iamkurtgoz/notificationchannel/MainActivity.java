package com.iamkurtgoz.notificationchannel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Create notification for old and new android versions.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), ResultReceiver.class);
        intent.setAction(ResultReceiver.ACTION_CLICK);
        NotificationUtil.with(getApplicationContext()).showNotification(
                "Başlık",
                "Açıklama",
                R.mipmap.ic_launcher,
                intent
        );
    }
}
