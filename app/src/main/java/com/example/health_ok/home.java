package com.example.health_ok;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class home extends AppCompatActivity implements View.OnClickListener {

private LinearLayout consult_doctor;
    private LinearLayout view_history,profileuser;
    private TextView signoutop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        consult_doctor=findViewById(R.id.csdoctor);
        view_history=findViewById(R.id.history);
         profileuser = findViewById(R.id.id6);
         signoutop=findViewById(R.id.signoutoption);
         signoutop.setOnClickListener(this);
        consult_doctor.setOnClickListener(this);
        view_history.setOnClickListener(this);
        profileuser.setOnClickListener(this);

    }

    @Override
    public  void onBackPressed() {
        new AlertDialog.Builder(home.this)
                .setTitle("EXIT")
                .setMessage("Do you want to exit")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("NO", null)
                .show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==consult_doctor.getId())
        {
            Toast.makeText(home.this, "consult_doctor_click", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(home.this,consultdoctor.class));
        }
        else if (view.getId()==view_history.getId())
        {
            Toast.makeText(home .this, "Artical", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(home.this,articalpage.class));
        }
        else if (view.getId()==profileuser.getId())
        {
            Toast.makeText(home .this, "pls wait for update", Toast.LENGTH_SHORT).show();

        }
        else if (view.getId()==signoutop.getId())
        {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(home.this, "Signout successfull", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(home.this,login.class));
            finish();
        }
    }
}