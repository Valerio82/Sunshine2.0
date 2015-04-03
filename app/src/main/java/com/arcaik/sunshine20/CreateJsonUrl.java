package com.arcaik.sunshine20;

import android.net.Uri;

/**
 * Created by arcaik on 03/04/2015.
 */
public class CreateJsonUrl {
    private String baseUrl;
    private String città;
    private final static String FORMAT_TYPE="json";
    private final static String QUERY_PARAM="q";
    private final String API_ID_PARAM = "APPID";
    private final String FORMAT_MODE = "mode";
    private final String UNIT_FORMAT = "units";
    private final String DAYS_PARAM = "cnt";
    private String apiKey;
    private String unitFormat;
    private String numberofDay;


    public CreateJsonUrl(String baseUrl,String città){
        this.baseUrl=baseUrl;
        this.città=città;
    }
    public void setUnitFormat(String unitFormat){
        this.unitFormat=unitFormat;
    }
    public void setdayNumber(String numberofDay){
        this.numberofDay=numberofDay;
    }
    private String getDayNumber(){
        return numberofDay;
    }
    public void setApiKey(String apiKey){
        this.apiKey=apiKey;
    }
    private String getApikey(){
        return apiKey;
    }
    public Uri getUri(){
        Uri builderUri=Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter(QUERY_PARAM,città)
                .appendQueryParameter(API_ID_PARAM,getApikey())
                .appendQueryParameter(FORMAT_MODE,FORMAT_TYPE)
                .appendQueryParameter(UNIT_FORMAT,unitFormat)
                .appendQueryParameter(DAYS_PARAM,getDayNumber()).build();
        return builderUri;
    }
}
