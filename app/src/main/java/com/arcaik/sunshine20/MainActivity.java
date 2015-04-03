package com.arcaik.sunshine20;
import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {

    private final static String TAG_DT = "dt";
    private final static String TAG_TEMP_MAX = "max";
    private final static String TAG_TEMP_MIN = "min";
    private final static String TAG_MAIN = "main";
    private final static String TAG_ICON="icon";
    private final static String TAG_ICON_ID_LISTVIEW="iconIdListView";
    private final static String TAG_DATA="data";
    private final static String LOG_NAME=MainActivity.class.getSimpleName();
    private Bundle bundle;
    private ArrayList<HashMap<String, String>> jsonArrayList = null;
    private ListView forecastListView;
    private ProgressBar spinner;
    private Intent jsonServiceIntent;
    private TextView textViewGiorno;
    private TextView textViewCondizioniMeteo;
    private TextView textViewTempMax;
    private TextView textViewTempMin;
    private ImageView imageView;
    private boolean serviceStart=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forecastListView = (ListView) findViewById(R.id.forecastListView);
        forecastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startDetailActivity(position);
            }
        });
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        textViewGiorno=(TextView)findViewById(R.id.textViewGiornoMain);
        textViewCondizioniMeteo=(TextView)findViewById(R.id.textViewCondizioniMeteoMain);
        textViewTempMax=(TextView)findViewById(R.id.textViewTemperaturaMaxMain);
        textViewTempMin=(TextView)findViewById(R.id.textViewTemperaturaMinMain);
        imageView=(ImageView)findViewById(R.id.imageViewMain);

    }
    public void onStart() {
        super.onStart();
        final IntentFilter broadcastIntentFilter = new IntentFilter(JSONService.NOTIFICATION);
        registerReceiver(receiver, broadcastIntentFilter);
    }

    public void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
        if(serviceStart)
        stopService(jsonServiceIntent);
    }

    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                actionEditText();
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




    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            spinner.setVisibility(View.INVISIBLE);
            Log.v(LOG_NAME, "onReceiver partito");
            bundle = intent.getExtras();
            parseJson();
            getAdapterForecast();
        }
    };


    public void parseJson() {
        jsonArrayList = (ArrayList<HashMap<String, String>>) bundle.get("ForecastList");
        String giorno;
        Utility utility=new Utility();
        HashMap<String, String> hashMap;
        if(jsonArrayList.size()!=0) {
            hashMap = jsonArrayList.get(0);
            textViewGiorno.setText(utility.getGiorno(hashMap.get(TAG_DT))+ "," + utility.getNumeroGiorno(hashMap.get(TAG_DT)) + " " + utility.getMese(hashMap.get(TAG_DT)));
            textViewTempMax.setText(hashMap.get(TAG_TEMP_MAX));
            textViewTempMin.setText(hashMap.get(TAG_TEMP_MIN));
            textViewCondizioniMeteo.setText(hashMap.get(TAG_MAIN));
            imageView.setImageResource(Integer.parseInt(utility.getIconIdMainView(hashMap.get(TAG_ICON))));
            jsonArrayList.remove(0);
            for (int i = 0; i < jsonArrayList.size(); i++) {
                hashMap = jsonArrayList.get(i);
                giorno = utility.getGiorno(hashMap.get(TAG_DT));
                hashMap.put(TAG_DATA, giorno);
                hashMap.put(TAG_ICON_ID_LISTVIEW, utility.getIconIdListView(hashMap.get(TAG_ICON)));
                Log.v(LOG_NAME, "GIorno " + hashMap.size());
            }
        }else
            startService(jsonServiceIntent);
    }
    private void getAdapterForecast() {
        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this, jsonArrayList,
                R.layout.forecast_layout, new String[]{TAG_DATA, TAG_TEMP_MAX,
                TAG_TEMP_MIN, TAG_MAIN,TAG_ICON_ID_LISTVIEW}, new int[]{R.id.textViewGiorno,
                R.id.textViewTemperaturaMax, R.id.textViewTemperaturaMin, R.id.textViewCondizioniMeteo,R.id.imageView});
        forecastListView.setAdapter(adapter);
    }
    public void actionEditText(){
        ActionBar actionBar=getActionBar();
        actionBar.setCustomView(R.layout.cercacitta);
        final EditText search=(EditText)actionBar.getCustomView().findViewById(R.id.searchCitta);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handle=false;
                if(actionId== EditorInfo.IME_ACTION_SEND) {
                    createUrl(search.getText().toString());
                    handle=true;
                    //Nasconde la tastiera
                    InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(),0);
                }
                return handle;
            }
        });
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);
    }

    private void startDetailActivity(int position){
        HashMap <String,String>hashMap=jsonArrayList.get(position);
        Intent startActivitDetail=new Intent(this,DetailActivity.class);
        startActivitDetail.putExtra("DetailHashmap",hashMap);
        startActivity(startActivitDetail);
    }

    public void createUrl(String città){
        spinner.setVisibility(View.VISIBLE);
        CreateJsonUrl url=new CreateJsonUrl("http://api.openweathermap.org/data/2.5/forecast/daily?",città);
        url.setApiKey("b4a73f1b4e9cafb494c82c3f8ce09690");
        url.setNumberOfDays("7");
        url.setUnitFormat("metric");
        Log.v(LOG_NAME,"Second url"+url.getUri());
        jsonServiceIntent = new Intent(this, JSONService.class);
        jsonServiceIntent.setData(url.getUri());
        this.startService(jsonServiceIntent);
        serviceStart=true;
    }
}
