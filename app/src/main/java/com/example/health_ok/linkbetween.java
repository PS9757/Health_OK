package com.example.health_ok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class linkbetween extends AppCompatActivity {
private Button patientpage,doctorpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkbetween);
        patientpage=findViewById(R.id.patientpage);
        doctorpage=findViewById(R.id.doctorpage);
        patientpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(linkbetween.this,register_page.class));
            }
        });
        doctorpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(linkbetween.this,doctorRegister.class));
            }
        });
    }
}