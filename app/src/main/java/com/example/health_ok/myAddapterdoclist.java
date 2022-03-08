package com.example.health_ok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class myAddapterdoclist extends ArrayAdapter<patientinfo> {
    ArrayList<patientinfo> info3;
    Context context;
    public myAddapterdoclist(Context context,ArrayList<patientinfo> info3)
    {
        super(context,0,info3);
        this.context=context;
        this.info3=info3;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        patientinfo obj =info3.get(position);
        convertView=LayoutInflater.from(context).inflate(R.layout.patient_listview,parent,false);
        TextView patientname,pnumber;
        patientname=convertView.findViewById(R.id.patientname);
        pnumber=convertView.findViewById(R.id.patientpnumber);
        patientname.setText(obj.getPatientname());
        pnumber.setText(obj.getPnumber());
        return convertView;
    }
}
