package com.example.maptest;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView vziname, latitude, longitude;

    public ViewHolder(Context context, View itemView) {
        super(itemView);

        vziname = itemView.findViewById(R.id.vziname);
        latitude = itemView.findViewById(R.id.latitude);
        longitude = itemView.findViewById(R.id.longitude);
    }
}
