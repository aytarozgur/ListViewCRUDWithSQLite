package com.example.canbay.bim494_homework2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Model.Customer;

/**
 * Created by canbay on 22.03.2017.
 */

public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Customer> customerList;


    public ListViewAdapter(Activity activity,List<Customer> customerList){
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.customerList = customerList;
    }



    @Override
    public int getCount() {
        return customerList.size();
    }

    @Override
    public Object getItem(int position) {
        return customerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = inflater.inflate(R.layout.row_layout,null);
        TextView count = (TextView) rowView.findViewById(R.id.count);
        TextView namesurname = (TextView) rowView.findViewById(R.id.namesurname);
        TextView credit = (TextView) rowView.findViewById(R.id.credit);
        TextView birthdate = (TextView) rowView.findViewById(R.id.birthdate);


        Customer customer = customerList.get(position);
        count.setText(String.valueOf(customer.getId()));
        namesurname.setText(customer.getNamesurname());
        birthdate.setText(customer.getBirthdate().toString());
        credit.setText((customer.getCredit()));
        return rowView;
    }
}
