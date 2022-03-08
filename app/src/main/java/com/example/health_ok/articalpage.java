package com.example.health_ok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class articalpage extends AppCompatActivity {
    private ListView artlist;
    private ArrayList<articalinfo> info2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articalpage);
        artlist=findViewById(R.id.articalList);
        articalinfo obj =new articalinfo(R.drawable.arti);
        articalinfo obj1 =new articalinfo(R.drawable.arti);
        articalinfo obj2 =new articalinfo(R.drawable.arti);
        info2=new ArrayList<>();
        info2.add(obj);
        info2.add(obj1);
        info2.add(obj2);
        myAddapterArtical myArtical = new myAddapterArtical(articalpage.this,info2);
        artlist.setAdapter(myArtical);
    }
}