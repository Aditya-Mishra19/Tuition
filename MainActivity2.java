package com.example.tuitioncenter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    EditText dat,tim;
    Button conf,user,cityb,dateb;
    TextView res;
    MyDBHandler db;
    String da,ti;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dat = findViewById(R.id.dateBox);
        tim = findViewById(R.id.timeBox);
        conf = findViewById(R.id.confirmBtn);
        user = findViewById(R.id.usernameBtn);
        cityb = findViewById(R.id.cityBtn);
        dateb = findViewById(R.id.dateBtn);
        res = findViewById(R.id.result);
        db = new MyDBHandler(getApplicationContext(),"Tuitions",null,1);
        dat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int cYear = cal.get(Calendar.YEAR);
                int cMonth = cal.get(Calendar.MONTH);
                int cDate = cal.get(Calendar.DATE);
                DatePickerDialog dialog = new DatePickerDialog(MainActivity2.this, android.R.style.Theme_DeviceDefault_DialogWhenLarge, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        da = i2+"/"+(i1+1)+"/"+i;
                        dat.setText(da);
                    }
                },cYear,cMonth,cDate);
                dialog.show();
            }
        });
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal1 = Calendar.getInstance();
                int cHour = cal1.get(Calendar.HOUR);
                int cMin = cal1.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(MainActivity2.this, android.R.style.Theme_DeviceDefault_DialogWhenLarge, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        ti = i+":"+i1;
                        tim.setText(ti);
                    }
                },cHour,cMin,false);
                dialog.show();
            }
        });
        Intent it1= getIntent();
        String n = it1.getStringExtra("Name");
        String nu = it1.getStringExtra("Phone Number");
        String c = it1.getStringExtra("City");
        String su = it1.getStringExtra("Subjects");
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.insertRecord(n,nu,c,su,da,ti);
                res.setText("Booking Confirmed");
                Toast.makeText(MainActivity2.this, "Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userdata = db.displayUserRecord(n);
                res.setText(userdata);
            }
        });
        cityb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ct = db.displayCityRecord(c);
                res.setText("No. of appointments:"+ct);
            }
        });
        dateb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String datedata = db.displayDateRecord(da);
                res.setText(datedata);
            }
        });
    }
}
