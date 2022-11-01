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

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private MyAdapter myadapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    ArrayList<SampleData> mDataList;

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
        listView = (ListView) findViewById(R.id.listView);

        list = new ArrayList<String>();
        settingList();
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        myadapter = new MyAdapter(this,mDataList);

        listView.setAdapter(myadapter);

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
                search(text);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
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


    }

    public void ListView(){


    }

    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.

      //  myadapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList(){

        mDataList = new ArrayList<SampleData>();

      //  list.add("aaa","37.563336407388648","126.97703469594828");

        mDataList.add(new SampleData("AAA","37.563336407388648","126.97703469594828"));
        mDataList.add(new SampleData("BBB","37.55226296701367","126.9731451048587"));
        mDataList.add(new SampleData("CCC", "37.48221757594223","126.89435412154552"));
        mDataList.add(new SampleData( "DDD","126.89435412154552","37.48221757594223"));
        mDataList.add(new SampleData("EEE","123","456"));

    }

}