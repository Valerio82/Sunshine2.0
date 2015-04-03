package com.arcaik.sunshine20;

import android.net.Uri;


public class CreateJsonUrl {
    private String baseUrl;
    private String città;
    private final String QUERY_PARAM;
    private final String FORMAT_MODE;
    private final String UNIT_FORMAT;
    private final String DAYS_PARAM;
    private final String FORMAT_TYPE;
    private String apiKey;
    private String unitFormat;
    private String numberOfDays;

    public CreateJsonUrl(String baseUrl,String città){
        this.baseUrl=baseUrl;
        this.città=città;
        FORMAT_MODE = "mode";
        UNIT_FORMAT = "units";
        DAYS_PARAM = "cnt";
        QUERY_PARAM = "q";
        FORMAT_TYPE="json";
    }
    public void setUnitFormat(String unitFormat){
        this.unitFormat=unitFormat;
    }
    public void setNumberOfDays(String numberOfDays){
        this.numberOfDays=numberOfDays;
    }
    private String getNumberOfDays(){
        return numberOfDays;
    }
    public void setApiKey(String apiKey){
        this.apiKey=apiKey;
    }
    private String getApikey(){
        return apiKey;
    }

    public Uri getUri(){
        String API_ID_PARAM = "APPID";
        return Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter(QUERY_PARAM,città)
                .appendQueryParameter(API_ID_PARAM,getApikey())
                .appendQueryParameter(FORMAT_MODE,FORMAT_TYPE)
                .appendQueryParameter(UNIT_FORMAT,unitFormat)
                .appendQueryParameter(DAYS_PARAM,getNumberOfDays()).build();
    }



}
