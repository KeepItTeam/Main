package com.example.studentsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegistrationActivity extends AppCompatActivity {
    static Student student=new Student();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Student student=new Student();

    }
    public void goHello(View view) {
        getRegistrationData();
        Intent intent = new Intent(this, HelloActivity.class);
        startActivity(intent);
    }
    public void getRegistrationData(){//todo переделать на интент
        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        EditText et_name=findViewById(R.id.et_name);
        ed.putString("name", String.valueOf(et_name.getText()));

        EditText et_age=findViewById(R.id.et_age);
        ed.putInt("age", Integer.parseInt(String.valueOf(et_age.getText())));

        EditText et_city=findViewById(R.id.et_city);
        ed.putString("city", String.valueOf(et_city.getText()));

        EditText et_univer=findViewById(R.id.et_univer);
        ed.putString("univer", String.valueOf(et_univer));
        //todo ещё пол
    }
}