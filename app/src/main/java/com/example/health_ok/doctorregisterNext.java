package com.example.health_ok;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.HashMap;

public class doctorregisterNext extends AppCompatActivity {
private LinearLayout imagebutton;
private ImageView imageView;
private EditText docAddress,docpincode,docCity,docspecialist;
private Button docregbutton;
private FirebaseAuth mauth;
private FirebaseUser muser;
private FirebaseFirestore db;
String Array[];
private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorregister_next);
        Intent i = getIntent();
        Array=i.getStringArrayExtra("details");
        Toast.makeText(doctorregisterNext.this, Array[0], Toast.LENGTH_SHORT).show();
        imagebutton=findViewById(R.id.docimage);
        imageView=findViewById(R.id.imageid);
        docAddress=findViewById(R.id.docAddress);
        docpincode=findViewById(R.id.docpincode);
        docCity=findViewById(R.id.doccity);
        docspecialist=findViewById(R.id.docspecility);
        docregbutton=findViewById(R.id.docrergisterlast);
        mauth=FirebaseAuth.getInstance();
        muser=mauth.getCurrentUser();
        db=FirebaseFirestore.getInstance();
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });
        docregbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mauth.createUserWithEmailAndPassword(Array[1],Array[3])
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(doctorregisterNext.this, "register", Toast.LENGTH_SHORT).show();
                            setdatatoforebase();

                        }
                    });
            }
        });

    }
    void getImage(){
        Intent i= new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && data!=null && data.getData()!=null )
        {
            uri=data.getData();
            imageView.setImageURI(uri);
        }
    }
    void setdatatoforebase()
    {
        muser=mauth.getCurrentUser();
        DocumentReference df=db.collection("Doctors").document(muser.getUid());
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("Doctorname", Array[0]);
        hashMap.put("email",Array[1]);
        hashMap.put("Phone no",Array[2]);
        hashMap.put("Address",docAddress.getText().toString());
        hashMap.put("Pincode",docpincode.getText().toString());
        hashMap.put("City",docCity.getText().toString());
        hashMap.put("Uid",muser.getUid());
        hashMap.put("Specialist",docspecialist.getText().toString());
        if(checkinfo()) {
            df.set(hashMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {


                                    Toast.makeText(doctorregisterNext.this, "data send", Toast.LENGTH_SHORT).show();
                                    String filename = muser.getUid();
                                    StorageReference storage = FirebaseStorage.getInstance().getReference("Docimage/" + filename);
                                    storage.putFile(uri);
                                    startActivity(new Intent(doctorregisterNext.this,login.class));
                            }

                        }
                    });
        }
    }
    private boolean checkinfo()
    {
        String adddress = docAddress.getText().toString();
        String pincode =docpincode.getText().toString();
        String city = docCity.getText().toString();
        String specialist = docspecialist.getText().toString();
        if (adddress=="" || adddress.isEmpty())
        {
            docAddress.setError("Field Empty");
            return false;
        }
        if (pincode=="" || pincode.isEmpty())
        {
            docpincode.setError("Field Error");
            return false;
        }
        if (city=="" || city.isEmpty())
        {
            docCity.setError("Field Error");
            return false;
        }
        if (specialist=="" || specialist.isEmpty())
        {
            docspecialist.setError("Field Error");
            return  false;
        }
        if (imageView==null )
        {
            Toast.makeText(doctorregisterNext.this, "uploade image !", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}