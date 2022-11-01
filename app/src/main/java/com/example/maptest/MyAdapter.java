package com.example.maptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class  MyAdapter extends BaseAdapter {


    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SampleData> sample;

    public MyAdapter(Context context, ArrayList<SampleData> data) {
        context = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public SampleData getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.list_item, null);

        TextView latitude = (TextView)view.findViewById(R.id.latitude);
        TextView longitude = (TextView)view.findViewById(R.id.longitude);
        TextView vziname = (TextView)view.findViewById(R.id.vziname);

        vziname.setText(sample.get(position).getName());
        latitude.setText( sample.get(position).getLatitude());
        longitude.setText(sample.get(position).getLongitude());

        return view;
    }
}