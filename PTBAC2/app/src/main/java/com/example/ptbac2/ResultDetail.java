package com.example.ptbac2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultDetail extends AppCompatActivity {
    private TextView tv_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_detail);
        tv_result = findViewById(R.id.textResult);
        Intent intent = getIntent();
        tv_result.setText(intent.getStringExtra("result"));
    }
}