package com.arcaik.sunshine20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;


public class DetailActivity extends Activity {
    private final static String TAG_DT = "dt";
    private final static String TAG_TEMP_MAX = "max";
    private final static String TAG_TEMP_MIN = "min";
    private final static String TAG_MAIN = "main";
    private final static String TAG_ICON="icon";
    private final static String TAG_HUMIDITY="humidity";
    private final static String TAG_PRESSURE="pressure";
    private final static String TAG_WIND_SPEED="speed";
    private final static String TAG_DATA="data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();

        Bundle bundle=intent.getExtras();
        HashMap<String,String>hashMap= (HashMap<String, String>) bundle.get("DetailHashmap");
        Utility utility=new Utility();
        TextView textViewGiorno=(TextView)findViewById(R.id.textViewGiornoDetail);
        TextView textViewDettagliData=(TextView)findViewById(R.id.textViewDataDetail);
        TextView textViewTempMax=(TextView)findViewById(R.id.textViewTemperaturaMaxDetail);
        TextView textViewTempMin=(TextView)findViewById(R.id.textViewTemperaturaMinDetail);
        TextView textViewCondizioniMeteo=(TextView)findViewById(R.id.textViewCondizioniMeteoDetail);
        TextView textViewHumidity=(TextView)findViewById(R.id.textViewUmidità);
        TextView textViewPressure=(TextView)findViewById(R.id.textViewPressione);
        TextView textViewWind=(TextView)findViewById(R.id.textViewVento);
        ImageView imageViewIcon=(ImageView)findViewById(R.id.imageViewDetail);
        textViewGiorno.setText(hashMap.get(TAG_DATA));
        textViewDettagliData.setText(utility.getMese(hashMap.get(TAG_DT))+" "+utility.getNumeroGiorno(hashMap.get(TAG_DT)));
        textViewTempMax.setText(hashMap.get(TAG_TEMP_MAX));
        textViewTempMin.setText(hashMap.get(TAG_TEMP_MIN));
        textViewCondizioniMeteo.setText(hashMap.get(TAG_MAIN));
        textViewHumidity.setText("Umidità: "+hashMap.get(TAG_HUMIDITY)+"%");
        textViewPressure.setText("Pressione: "+hashMap.get(TAG_PRESSURE)+"hPa");
        textViewWind.setText("Vento: "+hashMap.get(TAG_WIND_SPEED)+"Km/h");
        imageViewIcon.setImageResource(Integer.parseInt(utility.getIconIdMainView(hashMap.get(TAG_ICON))));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
