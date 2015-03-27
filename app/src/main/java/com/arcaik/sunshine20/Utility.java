package com.arcaik.sunshine20;


import android.util.Log;
import java.util.Date;

/**
 * Created by arcaik on 25/03/2015.
 */
public class Utility {



    public String getGiorno(String date) {
        Date time = new Date(Long.parseLong(date) * 1000);
        Log.v("WEEKFORECAST","GIORNO "+time.toString());
        String giorno=String.valueOf(time).substring(0,3);
        switch (giorno){
            case "Mon":
                giorno="Lunedì";
                break;
            case "Tue":
                giorno="Martedì";
                break;
            case "Wed":
                giorno="Mercoledì";
                break;
            case "Thu":
                giorno="Giovedì";
                break;
            case "Fri":
                giorno="Venerdi";
                break;
            case "Sat":
                giorno="Sabato";
                break;
            case "Sun":
                giorno="Domenica";
                break;
            default:
                break;
        }
        return giorno;
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

    public String getMese(String date){
        Date time = new Date(Long.parseLong(date) * 1000);
        String mese=String.valueOf(time).substring(4,7);
        switch (mese){
            case "Jan":
                mese="Gennaio";
                break;
            case "Feb":
                mese="Febbraio";
                break;
            case "Mar":
                mese ="Marzo";
                break;
            case "Apr":
                mese="Aprile";
                break;
            case "May":
                mese="Maggio";
                break;
            case "Jun":
                mese="Giugno";
                break;
            case "Jul":
                mese="Luglio";
                break;
            case "Aug":
                mese="Agosto";
                break;
            case "Sep":
                mese="Settembre";
                break;
            case "Oct":
                mese="Ottobre";
                break;
            case "Nov":
                mese="Novembre";
                break;
            case "Dec":
                mese="Dicembre";
                break;
        }
        return mese;
    }
    public String getNumeroGiorno(String date){
        Date time = new Date(Long.parseLong(date) * 1000);
        String numeroGiorno=String.valueOf(time).substring(8,10);
        return numeroGiorno;

    }

}
