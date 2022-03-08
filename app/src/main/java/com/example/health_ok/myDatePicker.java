package com.example.health_ok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class myDatePicker extends AppCompatActivity implements View.OnClickListener{
    TimePickerDialog picker;
    TextView eText,etext1,T1,T2,T3,T4;
    Button btnGet,btnGet1,additem;
    int count=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_date_picker);
        eText=(TextView) findViewById(R.id.datepeditText1);
        etext1=findViewById(R.id.datepeditText2);
        btnGet1=findViewById(R.id.datepbutton2);
        T1=findViewById(R.id.viewq1);
        T2=findViewById(R.id.viewq2);
        T3=findViewById(R.id.viewq3);
        T4=findViewById(R.id.viewq4);
        additem=findViewById(R.id.AddData34);
        eText.setInputType(InputType.TYPE_NULL);
        btnGet=(Button)findViewById(R.id.datepbutton1);
        eText.setOnClickListener(this);
        etext1.setOnClickListener(this);
        btnGet.setOnClickListener(this);
        btnGet1.setOnClickListener(this);
        additem.setOnClickListener(this);
    }
    private void settime(TextView eText)
    {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        picker = new TimePickerDialog(myDatePicker.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        eText.setText(sHour + ":" + sMinute);
                    }
                }, hour, minutes, true);
        picker.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.datepeditText1:
                settime(eText);
                break;
            case R.id.datepbutton1:
                eText.setText("Selected Time: "+ eText.getText());
                break;
            case R.id.datepeditText2:
                settime(etext1);
                break;
            case R.id.datepbutton2:
                etext1.setText("Selected Time: "+ etext1.getText());
                break;
            case R.id.AddData34:
                settimetext();
                break;
        }
    }
    private void settimetext()
    {
        String e1 =eText.getText().toString();
        String e2 =etext1.getText().toString();
        switch (count)
        {
            case 0:
                T1.setText(e1  + "-" +  e2);
                count++;
                break;
            case 1:
                T2.setText(e1 + "-" + e2);
                count++;
                break;
            case 2:
                T3.setText(e1  + "-" +  e2);
                count++;
                break;
            case 3:
                T4.setText(e1  + "-" +  e2);
                count++;
                break;
        }
    }
}