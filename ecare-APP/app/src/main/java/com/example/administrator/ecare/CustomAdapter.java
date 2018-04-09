package com.example.administrator.ecare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 4/4/2018.
 */

class CustomAdapter extends BaseAdapter {
    Context context;
    String[] nameList;
    String[] qualificationList;
    String[] feesList;
    LayoutInflater inflter;


    public CustomAdapter(Context applicationContext, ArrayList<String> nameArray, ArrayList<String> qualificationArray, ArrayList<String> feesArray) {
        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
        this.nameList = nameArray.toArray(new String[0]);
        this.qualificationList=qualificationArray.toArray(new String[0]);
        this.feesList = feesArray.toArray(new String[0]);

    }

    @Override
    public int getCount() {
        return nameList.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        view = inflter.inflate(R.layout.activity_listview, null);
        TextView name= (TextView)view.findViewById(R.id.tVName_ListView);
        TextView qualification= (TextView)view.findViewById(R.id.tVQualification_ListView);
        TextView fees= (TextView)view.findViewById(R.id.tVFees_ListView);
        TextView fees_value= (TextView)view.findViewById(R.id.tVFees_Value_ListView);
        ImageView profile= (ImageView)view.findViewById(R.id.IVProfile_ListView);

        name.setText(nameList[position]);
        qualification.setText(qualificationList[position]);
        fees_value.setText(feesList[position]);




        return view;
    }
}
