package com.example.tuitioncenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name,num;
    AutoCompleteTextView city;
    CheckBox java,pyth,js,ml,dsa,ds,mad;
    Button sub;
    String[] loc = {"Bangalore","Delhi","Bombay","Hydberabad","Kurnool","Vijayawada"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nameBox);
        num = findViewById(R.id.noBox);
        city = findViewById(R.id.cityBox);
        java = findViewById(R.id.javaBtn);
        pyth = findViewById(R.id.pythonBtn);
        js = findViewById(R.id.javascriptBtn);
        ml = findViewById(R.id.mlBtn);
        dsa = findViewById(R.id.dsaBtrn);
        ds = findViewById(R.id.dsBtn);
        mad = findViewById(R.id.madBtn);
        sub = findViewById(R.id.submitBtn);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.select_dialog_item,loc);
        city.setAdapter(aa);
        city.setThreshold(1);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String nu = num.getText().toString();
                String c = city.getText().toString();
                StringBuilder subj = new StringBuilder();
                if(java.isChecked())
                    subj.append("Java ");
                if(pyth.isChecked())
                    subj.append("Pyhton ");
                if(js.isChecked())
                    subj.append("JavaScript ");
                if(ml.isChecked())
                    subj.append("MachineLearning ");
                if(dsa.isChecked())
                    subj.append("DataStructure ");
                if(ds.isChecked())
                    subj.append("DataScience ");
                if(mad.isChecked())
                    subj.append("MobileApplicationDevelopment");
                Intent it= new Intent(MainActivity.this, MainActivity2.class);
                it.putExtra("Name",n);
                it.putExtra("Phone Number",nu);
                it.putExtra("City",c);
                it.putExtra("Subjects",subj.toString());
                startActivity(it);
            }
        });
    }
}
