package com.arcaik.sunshine20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        HashMap<String,String>hashMap= (HashMap<String, String>) bundle.get("DetailHashmap");
        ModificaDatiJson modificaDatiJson=new ModificaDatiJson();
        TextView textViewGiorno=(TextView)findViewById(R.id.textViewGiornoDetail);
        TextView textViewTempMax=(TextView)findViewById(R.id.textViewTemperaturaMaxDetail);
        TextView textViewTempMin=(TextView)findViewById(R.id.textViewTemperaturaMinDetail);
        TextView textViewCondizioniMeteo=(TextView)findViewById(R.id.textViewCondizioniMeteoDetail);
        ImageView imageViewIcon=(ImageView)findViewById(R.id.imageViewDetail);
        textViewGiorno.setText(hashMap.get(TAG_DT).toString());
        textViewTempMax.setText(hashMap.get(TAG_TEMP_MAX).toString());
        textViewTempMin.setText(hashMap.get(TAG_TEMP_MIN).toString());
        textViewCondizioniMeteo.setText(hashMap.get(TAG_MAIN.toString()));
        imageViewIcon.setImageResource(Integer.parseInt(modificaDatiJson.getIconIdMainView(hashMap.get(TAG_ICON))));

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
