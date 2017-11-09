package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Krish on 8/22/2017.
 */


public class EarthquakeLoader extends AsyncTaskLoader <List<Earthquake>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String mUrl;

    public EarthquakeLoader(Context context, String url){
        super(context);
        mUrl=url;
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(LOG_TAG,"TEST: loadInBackground() called");
        if(mUrl==null){
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"TEST: onStartLoading() called");
        forceLoad();
    }
}
