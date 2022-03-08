package com.example.health_ok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class doctorRegister extends AppCompatActivity {
private Button registerbutton ;
private EditText docname,docpassword,docemail,docphoneno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);
        registerbutton=findViewById(R.id.finalregister);
        docname=findViewById(R.id.docregname);
        docemail=findViewById(R.id.docregemail);
        docpassword=findViewById(R.id.docregpassword);
        docphoneno=findViewById(R.id.docregphone);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (checkinfo()) {
                String Array[] = new String[4];
                Array[0] = docname.getText().toString();
                Array[1] = docemail.getText().toString();
                Array[2] = docphoneno.getText().toString();
                Array[3] = docpassword.getText().toString();

                Intent intent = new Intent(doctorRegister.this, doctorregisterNext.class);
                intent.putExtra("details", Array);
                startActivity(intent);
            }
            }
        });

    }
    private boolean checkinfo()
    {
        String name = docname.getText().toString();
        String email=docemail.getText().toString();
        String pass=docpassword.getText().toString();
        String phonnen=docphoneno.getText().toString();

        if (name==null  || name.isEmpty())
        {
            docname.setError("Field Empty");
            return false;
        }
        if (email=="" || email.isEmpty())
        {
            docemail.setError("Field Empty");
            return false;
        }
        if (phonnen=="" || phonnen.isEmpty())
        {
            docphoneno.setError("Field Empty");
            return false;
        }
        if(pass== "" || pass.isEmpty() || pass.length()<6)
        {
            docpassword.setError("Field Empty");
            return false;
        }

        return true;
    }
}