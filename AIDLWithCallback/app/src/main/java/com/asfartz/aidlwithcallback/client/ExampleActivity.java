package com.asfartz.aidlwithcallback.client;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asfartz.aidlwithcallback.R;
import com.asfartz.aidlwithcallback.server.MessageService;

public class ExampleActivity extends AppCompatActivity {

    MyConnection mConnection = new MyConnection(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        Log.d("AIDL service", "ExampleActivity - onCreate");

        Intent intent = new Intent(ExampleActivity.this, MessageService.class);
        boolean ret = getApplicationContext().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Log.d("AIDL service", "ExampleActivity - After bind call " + ret);

    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d("AIDL service", "showMessage: mConnection.getRemoteService = " + mConnection.getRemoteService());
    }
}
