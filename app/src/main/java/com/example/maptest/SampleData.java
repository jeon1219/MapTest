package com.example.maptest;

public class SampleData {

        private String name;
        private String latitude;
        private String longitude;

        public SampleData(String name, String latitude, String longitude){
                this.name = name;
                this.latitude = latitude;
                this.longitude = longitude;
        }


        public String getName() {
                return name;
        }

        public String setName() {
                return name;
        }

        public String getLatitude()
        {
        return this.latitude;
        }

        public String setLatitude()
        {
                return this.latitude;
        }

        public String setLongitude()
        {
        return this.longitude;
        }

        public String getLongitude()
        {
                return this.longitude;
        }



}