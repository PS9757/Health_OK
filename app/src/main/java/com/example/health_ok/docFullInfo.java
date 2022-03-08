package com.example.health_ok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class docFullInfo extends AppCompatActivity {
private Button rechedule,bookappoinment;
private TextView shoeappoiment;
int yeaar,daay,moonth;
private TextView docname,docaddress,doccity,docphoneno,docemail;
String uid;
private FirebaseAuth mauth;
private FirebaseUser muser;

FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_full_info);
        rechedule =findViewById(R.id.rechedule);
        shoeappoiment =findViewById(R.id.showappoinment);
        docname=findViewById(R.id.fullinfodocname);
        docaddress=findViewById(R.id.fullinfodocaddress);
        doccity=findViewById(R.id.docfullinfocity);
        docphoneno=findViewById(R.id.docfullinfophoneno);
        docemail=findViewById(R.id.docfullinfoemail);
        bookappoinment=findViewById((R.id.docbook));
        db=FirebaseFirestore.getInstance();
        mauth=FirebaseAuth.getInstance();
        muser=mauth.getCurrentUser();
        rechedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar =Calendar.getInstance();
                yeaar=calendar.get(Calendar.YEAR);
                daay=calendar.get(Calendar.DAY_OF_MONTH);
                moonth=calendar.get(Calendar.MONTH);
                DatePickerDialog pick =new DatePickerDialog(docFullInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        shoeappoiment.setText(i+ "/" + i1 + "/" + i2);
                    }
                },yeaar,moonth,daay);
                pick.show();

            }
        });
        bookappoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               userdatagt();
            }
        });

    }
    private void userdatagt(){
        db.collection("user").document(muser.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot snapshot=task.getResult();
                        bookappoinmenty(snapshot);

                    }
                });

    }
    private void bookappoinmenty(DocumentSnapshot snapshot) {
        String pname =snapshot.getString("User name");
        String pphone=snapshot.getString("Number");

        DocumentReference df=db.collection("Book Appointment").document(muser.getUid());
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("Name",pname);
        hashMap.put("Phone no",pphone);
        hashMap.put("Uid",uid);
        hashMap.put("Date",shoeappoiment.getText().toString());

        df.set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(docFullInfo.this, "Booked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(docFullInfo.this,done_Successfull.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=getIntent();
        uid=intent.getStringExtra("Uid");
        db.collection("Doctors").document(uid)
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