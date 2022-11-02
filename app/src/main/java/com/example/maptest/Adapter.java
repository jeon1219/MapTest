package com.example.maptest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import javax.sql.StatementEvent;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public ViewHolder holder;
    ArrayList<SampleData> getmDataList;
     Activity activity;




    public interface OnItemClickListner{
         void onItemClicked(int position,String data,String data2);
     }

     private OnItemClickListner itemClickListner;

     public void setOnItemClickListner(OnItemClickListner listner){
         itemClickListner = listner;
     }

    public Adapter(ArrayList<SampleData> getmDataList, Activity activity) {
        this.getmDataList = getmDataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "";
                String data2 = "";
                int position = viewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    data = viewHolder.getLatitude().getText().toString();
                    data2 = viewHolder.getLongitude().getText().toString();
                }
                itemClickListner.onItemClicked(position,data,data2);

            }
        });
        
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.vziname.setText(getmDataList.get(position).getVziname());
        holder.latitude.setText(getmDataList.get(position).getLatitude());
        holder.longitude.setText(getmDataList.get(position).getLongitude());

    }

    @Override
    public int getItemCount() {
        return getmDataList.size();
    }

    public void  filterList(ArrayList<SampleData> filteredList) {
        getmDataList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView vziname;
        TextView latitude;
        TextView longitude;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.vziname=itemView.findViewById(R.id.vziname);
            this.latitude=itemView.findViewById(R.id.latitude);
            this.longitude=itemView.findViewById(R.id.longitude);

        }

        public TextView getVziname(){
            return vziname;
        }

        public TextView getLatitude()
        {
            return latitude;
        }

        public TextView  getLongitude()
        {
            return longitude;
        }

    }

}
