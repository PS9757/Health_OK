package com.example.health_ok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

public class activity_doctormain  extends AppCompatActivity {
    private CardView patientcurrent,previousapp,DatePick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctormain);
        patientcurrent=findViewById(R.id.currentpatient);
        previousapp=findViewById(R.id.previousapp);
        DatePick=findViewById(R.id.infodate);
        patientcurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity_doctormain.this,PatientAppointment_activity.class);
                startActivity(intent);
            }

        });
        previousapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_doctormain.this,previousAppointmentdoc.class));
            }
        });
        DatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_doctormain.this,myDatePicker.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.profile){
            Intent intent=new Intent(activity_doctormain.this,doc_profile_Activity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(activity_doctormain.this,login.class);
            startActivity(intent);
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}