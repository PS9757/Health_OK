package com.example.health_ok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class consultdoctor extends AppCompatActivity {
    private ListView doclist;
    private ArrayList<doctorInfo> info1;
    private FirebaseUser muser;
    private FirebaseAuth mauth;
    private FirebaseFirestore db;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultdoctor);
        doclist=findViewById(R.id.consultlist);
        info1=new ArrayList<>();
        mauth =FirebaseAuth.getInstance();
        muser=mauth.getCurrentUser();
        db= FirebaseFirestore.getInstance();
        getdocinfo();
        doclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Object obj =info1.get(position);
                uid=((doctorInfo) obj).getUid();
                Intent intent=new Intent(consultdoctor.this,docFullInfo.class);
                intent.putExtra("Uid",uid);
                startActivity(intent);
            }
        });

    }

    private void getdocinfo() {
        db.collection("Doctors").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    String name;
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for(QueryDocumentSnapshot snapshot: task.getResult())
                        {
                            name = snapshot.getString("Doctorname");
                            String  city = snapshot.getString("City");
                            String specialist =snapshot.getString("Specialist");
                            String uid =snapshot.getString("Uid");
                            doctorInfo obj1= new doctorInfo(name,city,uid,specialist);
                            info1.add(obj1);

                        }
                        myAddapter myadd = new myAddapter(consultdoctor.this,info1);
                        doclist.setAdapter(myadd);


                    }
                });
    }
}