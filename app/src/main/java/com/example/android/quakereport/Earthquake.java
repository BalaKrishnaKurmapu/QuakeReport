package com.example.android.quakereport;

import static com.example.android.quakereport.R.id.date;

/**
 * Created by Krish on 8/19/2017.
 */

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private Long mTimeInMs;
    private String mUrl;

    public Earthquake(double magnitude, String location, Long timeInMs, String url){

        mMagnitude=magnitude;
        mLocation=location;
        mTimeInMs=timeInMs;
        mUrl=url;
    }

    public double getMagnitude(){
        return mMagnitude;
    }

    public String getLocation(){
        return mLocation;
    }

    public Long getTimeInMs (){
        return mTimeInMs;
    }
    public String getUrl(){
        return mUrl;
    }


}
