package com.arcaik.sunshine20;

import android.util.Log;

/**
 * Created by arcaik on 25/03/2015.
 */
public class ModificaDatiJson {

    public String conversioneDataDaUnixTime(String date) {
        java.util.Date time = new java.util.Date(Long.parseLong(date) * 1000);
        Log.v("WEEKFORECAST","GIORNO"+time.toString());
        String temp=String.valueOf(time).substring(0,3);
        switch (temp){
            case "Mon":
                temp="Lunedì";
                break;
            case "Tue":
                temp="Martedì";
                break;
            case "Wed":
                temp="Mercoledì";
                break;
            case "Thu":
                temp="Giovedì";
                break;
            case "Fri":
                temp="Venerdi";
                break;
            case "Sat":
                temp="Sabato";
                break;
            case "Sun":
                temp="Domenica";
                break;
            default:
                break;
        }
        return temp;
    }

    public String getIconIdListView(String iconId){
        switch (iconId){
            case "01d":
                iconId=Integer.toString(R.drawable.ic_clear);
                break;
            case "01n":
                iconId=Integer.toString(R.drawable.ic_clear);
                break;
            case "02d":
                iconId=Integer.toString(R.drawable.ic_light_clouds);
                break;
            case "02n":
                iconId=Integer.toString(R.drawable.ic_light_clouds);
                break;
            case "03d":
                iconId=Integer.toString(R.drawable.ic_cloudy);
                break;
            case "03n":
                iconId=Integer.toString(R.drawable.ic_cloudy);
                break;
            case "04d":
                iconId=Integer.toString(R.drawable.ic_cloudy);
                break;
            case "04n":
                iconId=Integer.toString(R.drawable.ic_cloudy);
                break;
            case "09d":
                iconId=Integer.toString(R.drawable.ic_rain);
                break;
            case "09n":
                iconId=Integer.toString(R.drawable.ic_rain);
                break;
            case "10d":
                iconId=Integer.toString(R.drawable.ic_light_rain);
                break;
            case "10n":
                iconId=Integer.toString(R.drawable.ic_light_rain);
                break;
            case "11d":
                iconId=Integer.toString(R.drawable.ic_storm);
                break;
            case "11n":
                iconId=Integer.toString(R.drawable.ic_storm);
                break;
            case "13d":
                iconId=Integer.toString(R.drawable.ic_snow);
                break;
            case "13n":
                iconId=Integer.toString(R.drawable.ic_snow);
                break;
            case "50d":
                iconId=Integer.toString(R.drawable.ic_fog);
                break;
            case "50n":
                iconId=Integer.toString(R.drawable.ic_fog);
                break;
        }
        return iconId;
    }
    public String getIconIdMainView(String iconId){
        switch (iconId){
            case "01d":
                iconId=Integer.toString(R.drawable.art_clear);
                break;
            case "01n":
                iconId=Integer.toString(R.drawable.art_clear);
                break;
            case "02d":
                iconId=Integer.toString(R.drawable.art_light_clouds);
                break;
            case "02n":
                iconId=Integer.toString(R.drawable.art_light_clouds);
                break;
            case "03d":
                iconId=Integer.toString(R.drawable.art_clouds);
                break;
            case "03n":
                iconId=Integer.toString(R.drawable.art_clouds);
                break;
            case "04d":
                iconId=Integer.toString(R.drawable.art_clouds);
                break;
            case "04n":
                iconId=Integer.toString(R.drawable.art_clouds);
                break;
            case "09d":
                iconId=Integer.toString(R.drawable.art_rain);
                break;
            case "09n":
                iconId=Integer.toString(R.drawable.art_rain);
                break;
            case "10d":
                iconId=Integer.toString(R.drawable.art_light_rain);
                break;
            case "10n":
                iconId=Integer.toString(R.drawable.art_light_rain);
                break;
            case "11d":
                iconId=Integer.toString(R.drawable.art_storm);
                break;
            case "11n":
                iconId=Integer.toString(R.drawable.art_storm);
                break;
            case "13d":
                iconId=Integer.toString(R.drawable.art_snow);
                break;
            case "13n":
                iconId=Integer.toString(R.drawable.art_snow);
                break;
            case "50d":
                iconId=Integer.toString(R.drawable.art_fog);
                break;
            case "50n":
                iconId=Integer.toString(R.drawable.art_fog);
                break;
        }
        return iconId;
    }

}
