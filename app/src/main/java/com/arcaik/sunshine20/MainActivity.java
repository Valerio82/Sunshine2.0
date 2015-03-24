package com.arcaik.sunshine20;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    private final static String TAG_DT = "dt";
    private final static String TAG_TEMP_MAX = "max";
    private final static String TAG_TEMP_MIN = "min";
    private final static String TAG_MAIN = "main";
    private final static String TAG_ICON="icon";

    Bundle bundle;
    ArrayList<HashMap<String, String>> jsonArrayList = null;
    ListView forecastListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forecastListView = (ListView) findViewById(R.id.forecastListView);

    }

    public void onStart() {
        super.onStart();
        final IntentFilter broadcastIntentFilter = new IntentFilter(JSONService.NOTIFICATION);
        registerReceiver(receiver, broadcastIntentFilter);
    }

    public void onStop() {
        super.onStop();
        unregisterReceiver(receiver);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                String città = "Frosinone";
                createUrl(città);
                return true;
            case R.id.action_refresh:
                getAdapterForecast();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void createUrl(String citta) {
        final String FREEMUSICARCHIVE_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
        final String FORMATO_JSON = "json";
        final String TIPO_DI_UNITA_DI_MISURA = "metric";
        final String NUMERO_GIORNI = "7";
        final String API_KEY = "b4a73f1b4e9cafb494c82c3f8ce09690";
        final String QUERY_PARAM = "q";
        final String API_ID_PARAM = "APPID";
        final String FORMAT_MODE = "mode";
        final String UNIT_FORMAT = "units";
        final String DAYS_PARAM = "cnt";
        Uri builderUri = Uri.parse(FREEMUSICARCHIVE_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, citta)
                .appendQueryParameter(API_ID_PARAM, API_KEY)
                .appendQueryParameter(FORMAT_MODE, FORMATO_JSON)
                .appendQueryParameter(UNIT_FORMAT, TIPO_DI_UNITA_DI_MISURA)
                .appendQueryParameter(DAYS_PARAM, NUMERO_GIORNI).build();
        Log.v("WEEKFORECAST", "HTTP " + builderUri);
        Intent intent = new Intent(this, JSONService.class);
        intent.setData(builderUri);
        this.startService(intent);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("WEEKFORECAST", "onReceiver partito");
            bundle = intent.getExtras();
            parseJson();
        }
    };


    public void parseJson() {
        jsonArrayList = (ArrayList<HashMap<String, String>>) bundle.get("ForecastList");
        HashMap<String, String> hashMap;
        for (int i = 0; i < jsonArrayList.size(); i++) {
            hashMap = jsonArrayList.get(i);
            String giorno=conversioneData(hashMap.get(TAG_DT));
            hashMap.put(TAG_DT,giorno);
            String icon=getIcon(hashMap.get(TAG_ICON));
            hashMap.put(TAG_ICON,getIcon(icon));
            Log.v("WEEKFORECAST","GIorno "+hashMap.get(TAG_DT));
        }

    }

    private void getAdapterForecast() {
        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this, jsonArrayList,
                R.layout.forecast_layout, new String[]{TAG_DT, TAG_TEMP_MAX,
                TAG_TEMP_MIN, TAG_MAIN,TAG_ICON}, new int[]{R.id.textViewGiorno,
                R.id.textViewTemperaturaMax, R.id.textViewTemperaturaMin, R.id.textViewCondizioniMeteo,R.id.imageView});
        forecastListView.setAdapter(adapter);
    }



    private String conversioneData(String date) {
        java.util.Date time = new java.util.Date(Long.parseLong(date) * 1000);
        String temp=String.valueOf(time).substring(0,3);
        switch (temp){
            case "Mon":
                temp="Monday";
                break;
            case "Tue":
                temp="Tuesday";
                break;
            case "Wed":
                temp="Wednesday";
                break;
            case "Thu":
                temp="Thursday";
                break;
            case "Fri":
                temp="Friday";
                break;
            case "Sat":
                temp="Saturday";
                break;
            case "Sun":
                temp="Sunday";
                break;
            default:
                break;
        }
        return temp;
    }

    public String getIcon(String iconId){
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


}
