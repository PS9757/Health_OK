package com.example.health_ok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class previousAppointmentdoc extends AppCompatActivity {
    private ListView patientView;
    private ArrayList<patientinfo> info3;
    private FirebaseAuth mauth;
    private FirebaseUser muser;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_appointmentdoc);
        patientView=findViewById(R.id.patientlist1);
        info3=new ArrayList<>();
        mauth=FirebaseAuth.getInstance();
        muser=mauth.getCurrentUser();
        db=FirebaseFirestore.getInstance();
        getpatientinfo();
    }
    private void getpatientinfo() {
        db.collection("Book Appointment")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot snapshot: task.getResult())
                        {
                            String name = snapshot.getString("Name");
                            String phoneno =snapshot.getString("Phone no");
                            patientinfo objinfo =new patientinfo(name,phoneno);
                            info3.add(objinfo);
                        }
                        myAddapterdoclist myadd =new myAddapterdoclist(previousAppointmentdoc.this,info3);
                        patientView.setAdapter(myadd);

                    }
                });

    }
}