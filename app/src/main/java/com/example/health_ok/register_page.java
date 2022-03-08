package com.example.health_ok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class register_page extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText remail,rpassword,fullname,pnumber;
    private Button button;
    private TextView doctorregister;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        remail =findViewById(R.id.remail);
        rpassword=findViewById(R.id.rpassword);
        button=findViewById(R.id.rbutton);
        fullname=findViewById(R.id.name1);
        pnumber=findViewById(R.id.pnum1);
        doctorregister=findViewById(R.id.nextdoctor);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkinfo()) {
                    String email = remail.getText().toString().trim();
                    String password = rpassword.getText().toString().trim();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(register_page.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(register_page.this, "Successful", Toast.LENGTH_SHORT).show();
                                        String name = fullname.getText().toString().trim();
                                        String num = pnumber.getText().toString().trim();
                                        FirebaseUser cuser = mAuth.getCurrentUser();
                                        String uid = cuser.getUid();
                                        DocumentReference dbref = db.collection("user").document(uid);
                                        HashMap<String, Object> details = new HashMap<>();
                                        details.put("User name", name);
                                        details.put("Number", num);
                                        details.put("Email", email);
                                        dbref.set(details)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(register_page.this, "Successful", Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(register_page.this, home.class));
                                                        }
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(register_page.this, "UnSuccessful", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        doctorregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register_page.this,doctorRegister.class));
            }
        });
    }
    private boolean checkinfo()
    {
        String name = fullname.getText().toString();
        String email=remail.getText().toString();
        String pass=rpassword.getText().toString();
        String phonnen=pnumber.getText().toString();

        if (name==null  || name.isEmpty())
        {
            fullname.setError("Field Empty");
            return false;
        }
        if (email=="" || email.isEmpty())
        {
            remail.setError("Field Empty");
            return false;
        }
        if (phonnen=="" || phonnen.isEmpty())
        {
            pnumber.setError("Field Empty");
            return false;
        }
        if(pass== "" || pass.isEmpty() || pass.length()<6)
        {
            rpassword.setError("Field Empty");
            return false;
        }

        return true;
    }

}