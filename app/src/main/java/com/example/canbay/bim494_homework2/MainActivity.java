package com.example.canbay.bim494_homework2;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Customer;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    static List<Customer> customers;
    ListView listView;
    DBHelper database;
    ListViewAdapter adapter;
    EditText namesurname,birthdate,credit;
    Button btnUpdate,btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("Bank Account Info");
        ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_action));
        database = new DBHelper(this);
        customers = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);

        // getting data from cursor
        getAllCustomers();
        adapter = new ListViewAdapter(this,customers);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Customer currentCustomer = (Customer) adapter.getItem(position);
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Update Customer");
                namesurname = (EditText) dialog.findViewById(R.id.etNameSurname);
                birthdate = (EditText) dialog.findViewById(R.id.etBirthofDate);
                credit = (EditText) dialog.findViewById(R.id.etCredit);
                namesurname.setText(currentCustomer.getNamesurname());
                birthdate.setText(currentCustomer.getBirthdate());
                credit.setText(currentCustomer.getCredit());
                btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
                btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        database.updateCustomer(currentCustomer.getId(),namesurname.getText().toString(),(birthdate.getText().toString()),(credit.getText().toString()));
                        int idx=  customers.indexOf(currentCustomer);
                        customers.set(idx,new Customer(currentCustomer.getId(),namesurname.getText().toString(),(birthdate.getText().toString()),(credit.getText().toString())));
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Customer currentCustomer = (Customer) adapter.getItem(position);
                database.deleteCustomer(currentCustomer.getId());
                customers.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });


    }
   public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()) {
            case R.id.newIcon:
                Intent intent = new Intent(getApplicationContext(),AddCustomerActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
       return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        customers.clear();
        getAllCustomers();
        adapter.notifyDataSetChanged();
        super.onPause();
    }

    public void getAllCustomers(){
        Cursor cursor=database.getData();
        int cursorcount=0;
        cursorcount = database.getCursorCount();
        if(cursorcount == 0){
            Toast t = new Toast(getApplicationContext());
            t.makeText(getApplicationContext(),"No customers",Toast.LENGTH_LONG).show();
        }


            while(cursor.moveToNext()) {
                String idfromdb = cursor.getString(0);
                String namesurnamefromdb = cursor.getString(1);
                String birthdatefromdb = cursor.getString(2);
                String creditfromdb = cursor.getString(3);
                try {
                    customers.add(new Customer(Integer.parseInt(idfromdb), namesurnamefromdb, birthdatefromdb, (creditfromdb)));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                }
            }
        }


