package com.example.health_ok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class doc_profile_Activity extends AppCompatActivity {
String uid;
private FirebaseFirestore db;
private FirebaseAuth mauth;
private FirebaseUser muser;
private TextView docname,docaddress,doccity,docphoneno,docemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);
        mauth=FirebaseAuth.getInstance();
        muser=mauth.getCurrentUser();
        db=FirebaseFirestore.getInstance();
        docname=findViewById(R.id.fullinfodocname1);
        docaddress=findViewById(R.id.fullinfodocaddress1);
        doccity=findViewById(R.id.docfullinfocity1);
        docphoneno =findViewById(R.id.docfullinfophoneno1);
        docemail=findViewById(R.id.docfullinfoemail1);
    }
    protected void onStart() {
        super.onStart();
        db.collection("Doctors").document(muser.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot snapshot =task.getResult();
                        String docname1   =snapshot.getString("Doctorname");
                        String docaddress1=snapshot.getString("Address");
                        String doccity1   =snapshot.getString("City");
                        String docphoneno1=snapshot.getString("Phone no");
                        String docemail1  =snapshot.getString("email");

                        docname.setText(docname1);
                        docaddress.setText(docaddress1);
                        doccity.setText(doccity1);
                        docphoneno.setText(docphoneno1);
                        docemail.setText(docemail1);
                    }
                });
    }
}