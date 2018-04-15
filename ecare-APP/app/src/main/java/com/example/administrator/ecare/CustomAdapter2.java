package com.example.administrator.ecare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 4/12/2018.
 */

class CustomAdapter2 extends BaseAdapter {
    Context context;
    String[] pidList;
    String[] dateList;
    String[] timesloteList;
    LayoutInflater inflter;

    public CustomAdapter2(Context applicationContext, ArrayList<String> pidArray, ArrayList<String> dateArray, ArrayList<String> timesloteArray) {

        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
        this.pidList = pidArray.toArray(new String[0]);
        this.dateList=dateArray.toArray(new String[0]);
        this.timesloteList=timesloteArray.toArray(new String[0]);
    }

    @Override
    public int getCount() {
        return pidList.length;
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
        view = inflter.inflate(R.layout.activity_listview_appointment, null);
        TextView patient_id= (TextView)view.findViewById(R.id.tVPatient_ID_Appointment_Listview);
        TextView Ppatient_id = (TextView)view.findViewById(R.id.tVPPatient_ID_Appointment_Listview);
        TextView date=(TextView)view.findViewById(R.id.tVDate_Appointment_Listview);
        TextView Pdate=(TextView)view.findViewById(R.id.tVPDate_Appointment_Listview);
        TextView timeslote=(TextView)view.findViewById(R.id.tVTimeslote_Appointment_Listview);
        TextView Ptimeslote=(TextView)view.findViewById(R.id.tVPTimeslote_Appointment_Listview);

        Ppatient_id.setText(pidList[position]);
        Pdate.setText(dateList[position]);
        Ptimeslote.setText(timesloteList[position]);

        return view;
    }
}
