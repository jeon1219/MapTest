package com.example.maptest;
;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    EditText editSearch;        // 검색어를 입력할 Input 창
    ArrayList<SampleData> getmDataList, filteredList;
    Adapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    double latitude=37.48221757594223;
    double longitude=126.89435412154552;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView();

    }

    public void MapView() {

        MapView mapView =new MapView(this);

        RelativeLayout mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Default Marker");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude)); //마커 위치
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양
        mapView.addPOIItem(marker);//마커 사용

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true); //위치

        editSearch = (EditText) findViewById(R.id.editSearch);
        recyclerView = (RecyclerView) findViewById(R.id.recyceler_view);

        filteredList = new ArrayList<>();
        getmDataList = new ArrayList<>();

        adapter = new Adapter(getmDataList,this);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        getmDataList.add(new SampleData("AAA","37.563336407388648","126.97703469594828"));
        getmDataList.add(new SampleData("BBB","37.55226296701367","126.9731451048587"));
        getmDataList.add(new SampleData("CCC", "37.48221757594223","126.89435412154552"));
        getmDataList.add(new SampleData( "DDD","126.89435412154552","37.48221757594223"));
        getmDataList.add(new SampleData("EEE","123","456"));
        adapter.notifyDataSetChanged();


        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                searchFilter(text);
            }
        });

        adapter.setOnItemClickListner(new Adapter.OnItemClickListner() {
            @Override
            public void onItemClicked(int position, String data, String data2) {
                Toast.makeText(getApplicationContext(),
                        data +  " " + data2,
                        Toast.LENGTH_SHORT).show();

                latitude = Double.parseDouble(data);
                longitude = Double.parseDouble(data2);

                MapPOIItem marker = new MapPOIItem();
                marker.setItemName("Default Marker");
                marker.setTag(0);
                marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude)); //마커 위치
                marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양
                marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양
                mapView.addPOIItem(marker);//마커 사용

                mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);//위치

            }
        });

/*
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myadapter.getItem(position).getLatitude() + " " + myadapter.getItem(position).getLongitude(),
                        Toast.LENGTH_SHORT).show();

                latitude = Double.parseDouble(myadapter.getItem(position).getLatitude());
                longitude = Double.parseDouble(myadapter.getItem(position).getLongitude());

                MapPOIItem marker = new MapPOIItem();
                marker.setItemName("Default Marker");
                marker.setTag(0);
                marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude)); //마커 위치
                marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양
                marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양
                mapView.addPOIItem(marker);//마커 사용

                mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);//위치
            }
        });
*/

    }


    //public void ListView(){}

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void searchFilter(String searchText) {
        filteredList.clear();

        for (int i = 0; i < getmDataList.size(); i++) {
            if (getmDataList.get(i).getVziname().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(getmDataList.get(i));
            }
        }

        adapter.filterList(filteredList);
    }


}