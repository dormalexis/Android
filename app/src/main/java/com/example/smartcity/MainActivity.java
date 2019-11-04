package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button connectionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionButton = findViewById(R.id.connectionButton);
        connectionButton.setOnClickListener(connectionButtonListener);
        connectionButton.setText(R.string.connectionButton);
    }

    //Listener
    private View.OnClickListener connectionButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,ConnectionActivity.class);
            startActivity(intent);
        }
    };

}
