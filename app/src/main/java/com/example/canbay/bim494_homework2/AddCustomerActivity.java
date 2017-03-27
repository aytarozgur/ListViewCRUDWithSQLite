package com.example.canbay.bim494_homework2;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Model.Customer;

/**
 * Created by canbay on 22.03.2017.
 */

public class AddCustomerActivity extends AppCompatActivity {


    DBHelper database;
    Button btnAdd,btnReset;
    EditText namesurname,birthdate,credit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcustomeractivity);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Add Customer");
        ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_action));
        namesurname = (EditText) findViewById(R.id.namesurname);
        birthdate = (EditText) findViewById(R.id.datePicker);
        credit = (EditText) findViewById(R.id.credit);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnReset = (Button) findViewById(R.id.btnReset);
        database = new DBHelper(this);


       btnAdd.setOnClickListener(new View.OnClickListener() {


           @Override
            public void onClick(View v) {

               try {

                   database.insertCustomer(namesurname.getText().toString(),(birthdate.getText().toString()),(credit.getText().toString()));
               } catch (Exception e) {
                   e.printStackTrace();
               }
               finally{
                   finish();
               }
           }
        });

        birthdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog;
              Calendar currentTime = Calendar.getInstance();
              int year = currentTime.get(Calendar.YEAR);
              int month = currentTime.get(Calendar.MONTH) ;
              int day = currentTime.get(Calendar.DAY_OF_MONTH);
                 datePickerDialog = new DatePickerDialog(AddCustomerActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if(month<9){
                            birthdate.setText(dayOfMonth + "/" +"0" + (month+1) + "/" + year);
                        }
                        else{
                        birthdate.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                        }
                    }
                },year,month,day);
                datePickerDialog.setTitle("Tarih Seçiniz");
                datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePickerDialog);
                datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePickerDialog);

                datePickerDialog.show();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namesurname.setText("");
                birthdate.setText("");
                credit.setText("");
            }
        });
    }
}
