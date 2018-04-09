package com.example.administrator.ecare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class CustomAdapter1  extends BaseAdapter {

    Context context;
    String[] patientnameList;
    String[] problemList;
    LayoutInflater inflter;
    public CustomAdapter1(Context applicationContext, ArrayList<String> patientnameArray, ArrayList<String> problemArray) {
        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
        this.patientnameList = patientnameArray.toArray(new String[0]);
        this.problemList=problemArray.toArray(new String[0]);

    }
    @Override
    public int getCount() {
        return patientnameList.length;
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
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        view = inflter.inflate(R.layout.activity_listview_consultation, null);
        TextView patientname= (TextView)view.findViewById(R.id.tVPatient_Name_listview_consultation);
        TextView problem= (TextView)view.findViewById(R.id.tVProblem_listView_consultation);

        patientname.setText(patientnameList[position]);
        problem.setText(problemList[position]);


        return view;
    }

}
