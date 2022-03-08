package com.example.health_ok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class myAddapterArtical extends ArrayAdapter<articalinfo> {
    ArrayList<articalinfo> info2;
    Context context;

    public myAddapterArtical(@NonNull Context context, ArrayList<articalinfo> info2) {
        super(context, 0,info2);
        this.context=context;
        this.info2=info2;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       articalinfo articalinfoobjt = info2.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.custom_artical,parent,false);
        ImageView articalview;
        articalview=convertView.findViewById(R.id.imageartical);
        articalview.setImageResource(articalinfoobjt.getArticalview());
        return convertView;
    }
}
