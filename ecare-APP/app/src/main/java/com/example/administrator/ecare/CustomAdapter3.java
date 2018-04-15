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

class CustomAdapter3 extends BaseAdapter {

    Context context;
    String[] cidList;
  /*  String[] dnameList;
    String[] problemList;
    String[] medicine_infoList;
    String[] allergy_infoList;
    String[] suggetionList;
    String[] prescribe_medicineList; */

    LayoutInflater inflter;
    public CustomAdapter3(Context applicationContext, ArrayList<String> cidArray) {

        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
        this.cidList = cidArray.toArray(new String[0]);


    }

    @Override
    public int getCount() {
        return cidList.length;
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

        view = inflter.inflate(R.layout.activity_listview_consultation_status, null);
        TextView consultation_id=(TextView)view.findViewById(R.id.tVConsultation_ID_Listview_Consultation_Status);
        TextView pconsultation_id=(TextView)view.findViewById(R.id.tVPConsultation_ID_Listview_Consultation_Status);

      //  pconsultation_id.setText(cidList[position]);
        pconsultation_id.setText(cidList[position]);


        return view;
    }
}
