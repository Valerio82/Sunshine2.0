package com.arcaik.sunshine20;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONService extends IntentService {


    Uri builderUri;
    ArrayList<HashMap<String,String>> forecastList;
    private final static String TAG_DT="dt";
    private final static String TAG_TEMP_MAX="max";
    private final static String TAG_TEMP_MIN="min";
    private final static String TAG_MAIN="main";
    private final static String TAG_ICON="icon";
    private final static String TAG_HUMIDITY="humidity";
    private final static String TAG_PRESSURE="pressure";
    private final static String TAG_WIND_SPEED="speed";
    public static final String NOTIFICATION="com.example.sunshine20";
    public static final String FORECAST_LIST="ForecastList";



    public JSONService() {
        super("JSONSERVICE");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        forecastList=new ArrayList<>();
        builderUri=intent.getData();
        try {
            connectUrl();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        inviaRisultato();

    }
    public void connectUrl() throws IOException, JSONException {
        HttpURLConnection urlConnection=null;
        BufferedReader reader = null;
        String openweathermapJSON;
        URL url=new URL(builderUri.toString());
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null)
                urlConnection.disconnect();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                urlConnection.disconnect();
            }
            openweathermapJSON = buffer.toString();
            letturaDatiJSON(openweathermapJSON);
        }catch (IOException exc){
        } finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
        if(reader!=null)
            try {
                reader.close();
            }catch (IOException exc){
            }
    }

    public void letturaDatiJSON(String urlJSON) throws JSONException {
        String dt;
        String temp_max;
        String temp_min;
        String main=null;
        String icon=null;
        String pressure;
        String humidity;
        String wind;
        JSONObject jsonObject=null;
        JSONArray jsonArray;
        try {
             jsonObject=new JSONObject(urlJSON);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonArray=jsonObject.getJSONArray("list");
        for(int i=0;i<jsonArray.length();i++){
            JSONObject list=jsonArray.getJSONObject(i);
            dt=(list.getString(TAG_DT));
            Log.v("JSON","DT "+dt);
            pressure=(list.getString(TAG_PRESSURE));
            humidity=(list.getString(TAG_HUMIDITY));
            wind=(list.getString(TAG_WIND_SPEED));
            JSONObject temp=list.getJSONObject("temp");
            temp_max=arrotondamentoGradiCelsius(temp.getString(TAG_TEMP_MAX));
            temp_min=arrotondamentoGradiCelsius(temp.getString(TAG_TEMP_MIN));
            Log.v("JSON","TEMP MIN MAX "+temp_max+" "+temp_min);
            JSONArray weatherArray=list.getJSONArray("weather");
            for(int a=0;a<weatherArray.length();a++) {
                JSONObject weather = weatherArray.getJSONObject(a);
                main=weather.getString(TAG_MAIN);
                icon=weather.getString(TAG_ICON);
                Log.v("JSON","Weather Main "+main+" icon "+icon);
            }

          HashMap<String, String> forecast = new HashMap<>();
            forecast.put(TAG_DT,dt);
            forecast.put(TAG_PRESSURE,pressure);
            forecast.put(TAG_HUMIDITY,humidity);
            forecast.put(TAG_WIND_SPEED,wind);
            forecast.put(TAG_TEMP_MAX,temp_max+"°");
            forecast.put(TAG_TEMP_MIN,temp_min+"°");
            forecast.put(TAG_MAIN,main);
            forecast.put(TAG_ICON,icon);
            forecastList.add(forecast);

        }
    }
    public void inviaRisultato(){
        Intent broadCasterIntent=new Intent(NOTIFICATION);
        broadCasterIntent.putExtra(FORECAST_LIST,forecastList);
        sendBroadcast(broadCasterIntent);
        stopSelf();
    }

    private String arrotondamentoGradiCelsius(String temperatura){
        int gradiCentigradi=(int) (Double.parseDouble(temperatura));
        return String.valueOf(gradiCentigradi);
    }

}