package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Krish on 8/19/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakes){

        super(context,0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        // Check if the existing view is being reused, otherwise inflate the view
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }
        //get item position
        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        String magnitude = formatMag(currentEarthquake.getMagnitude());
        magnitudeTextView.setText(magnitude );

        String[] location  = getSplit(currentEarthquake.getLocation());
        TextView locationTextView1 = (TextView) listItemView.findViewById(R.id.location1);
        locationTextView1.setText(location[0]);
        TextView locationTextView2 = (TextView) listItemView.findViewById(R.id.location2);
        locationTextView2.setText(location[1]);
        //new date object
        Date dateObject = new Date(currentEarthquake.getTimeInMs());
        String formattedDate = formatDate(dateObject);
        String formattedTime = formatTime(dateObject);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(formattedDate);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        timeTextView.setText(formattedTime);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }

    public String formatTime(Date dateObject ){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    public String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    public String[] getSplit(String location){
        if(location.contains("of")){
            return location.split("(?<=of)");
        }else{
            return new String[]{"Near the",location};
        }
    }
    public String formatMag(Double magnitude){
        DecimalFormat formatMagnitude = new DecimalFormat("0.0");
        return formatMagnitude.format(magnitude);
    }
    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)Math.floor(magnitude);

        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId=R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId=R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId=R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId=R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId=R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId=R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId=R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId=R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId=R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId=R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
}
