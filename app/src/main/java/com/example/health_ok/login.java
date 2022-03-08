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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {
    private TextView button,button2;
    private FirebaseAuth mAuth;

    private Button loginid;
    private EditText edittext1;
    private EditText edittext2;
    private FirebaseUser muser;
    private FirebaseFirestore db;
    @Override
    public void onStart() {
        super.onStart();
        mAuth=FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            muser=mAuth.getCurrentUser();
            DocumentReference df = db.collection("user").document(muser.getUid());
            df.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.getResult().getData()!=null)
                            {
                                Toast.makeText(login.this, "Successfully login", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this,home.class));
                                finish();

                            } else if (task.getResult().getData()==null)
                            {
                                Toast.makeText(login.this, "You are a doctor", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this,activity_doctormain.class));
                                finish();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth= FirebaseAuth.getInstance();
        loginid=findViewById(R.id.loginid);
        edittext1=findViewById(R.id.edittext1);
        edittext2=findViewById(R.id.edittext2);
        button=findViewById(R.id.register);
        button2=findViewById(R.id.F1);
        db = FirebaseFirestore.getInstance();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,reset_password.class));
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,linkbetween.class));

            }
        });

        loginid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkinfo()) {
                    String email = edittext1.getText().toString().trim();
                    String password = edittext2.getText().toString().trim();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        muser = mAuth.getCurrentUser();
                                        DocumentReference df = db.collection("user").document(muser.getUid());
                                        df.get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        if (task.getResult().getData() != null) {
                                                            Toast.makeText(login.this, "Successfully login", Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(login.this, home.class));
                                                            finish();

                                                        } else if (task.getResult().getData() == null) {
                                                            Toast.makeText(login.this, "You are a doctor", Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(login.this, activity_doctormain.class));
                                                        }
                                                    }
                                                });

                                    } else {
                                        Toast.makeText(login.this, "login fail", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }
    private boolean checkinfo()
    {
        String email=edittext1.getText().toString();
        String pass=edittext2.getText().toString();
        if (email=="" || email.isEmpty())
        {
            edittext1.setError("Field Empty");
            return false;
        }
        if(pass== "" || pass.isEmpty() || pass.length()<6)
        {
            edittext2.setError("Field Empty");
            return false;
        }        return true;
    }
}