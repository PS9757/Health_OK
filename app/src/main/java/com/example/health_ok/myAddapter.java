package com.example.health_ok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAddapter extends ArrayAdapter<doctorInfo>{
    ArrayList<doctorInfo> info1;
    Context context;
    public myAddapter(@NonNull Context context,ArrayList<doctorInfo> info1) {
        super(context, 0,info1);
        this.context=context;
        this.info1=info1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        doctorInfo doctorInfoobjt =info1.get(position);

        convertView= LayoutInflater.from(context).inflate(R.layout.custom_recycler,parent,false);
        TextView doctorname,location,specialist;
        doctorname=convertView.findViewById(R.id.doctorname);
        location=convertView.findViewById(R.id.doctorlocation);
        specialist=convertView.findViewById(R.id.docspec);
        doctorname.setText(doctorInfoobjt.getDoctorname());
        location.setText(doctorInfoobjt.getLocation());
        specialist.setText(doctorInfoobjt.getSpecialist());
        return convertView;
    }

}
