package com.asfartz.aidlwithcallback.server;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.asfartz.aidlwithcallback.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.startService);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MessageService.class));
            }
        });

        startActivityInNewTask();

    }

    private void startActivityInNewTask() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.asfartz.aidlwithcallback", "com.asfartz.aidlwithcallback.client.ExampleActivity");
        startActivity(intent);
    }


}
