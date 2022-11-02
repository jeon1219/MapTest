package com.example.maptest;

import java.util.Collection;

public class SampleData {

         String vziname;
         String latitude;
         String longitude;

        public SampleData(String vziname, String latitude, String longitude){
                this.vziname = vziname;
                this.latitude = latitude;
                this.longitude = longitude;
        }


        public String getVziname() {
                return vziname;
        }

        public String setVziname(String vziname) {
              return this.vziname = vziname;
        }

        public String getLatitude()
        {
                return latitude;
        }

        public String setLatitude()
        {
                return latitude;
        }

        public String setLongitude()
        {
                return longitude;
        }

        public String getLongitude()
        {
                return longitude;
        }


        public Collection<Object> toLowerCase() {
                return null;
        }
}