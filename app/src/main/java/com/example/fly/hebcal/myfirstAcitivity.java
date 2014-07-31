package com.example.fly.hebcal;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.transform.Result;


public class myfirstAcitivity extends Activity {
    HebcalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        adapter =new HebcalAdapter(this,R.layout.date_line);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfirst_acitivity);
        FetchCal getcal = new FetchCal();
        getcal.execute("http://www.hebcal.com/hebcal/?v=1&cfg=json&nh=on&nx=on&year=now&month=x&ss=on&mf=on&c=on&zip=08701&m=72&s=on");
        ListView theList = (ListView)findViewById(R.id.theList);
        theList.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.myfirst_acitivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private class FetchCal extends  AsyncTask<String,Void,ArrayList>{
         ArrayList<Dates> dateList = new ArrayList<Dates>();

        @Override
        protected ArrayList doInBackground(String... params) {
            String result = "";
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                JSONObject json = new JSONObject(result);
                JSONArray items = json.getJSONArray("items");
                for(int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);

                    //format the date
                    String dateToFormat = item.getString("date");
                    SimpleDateFormat format;
                    if (dateToFormat.length()<15){
                         format = new SimpleDateFormat("yyyy-MM-dd");

                    }else {
                        format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                    }
                    Date dateGetter = format.parse(dateToFormat);
                    String theFancydate = dateGetter.getMonth()+1+"/"+dateGetter.getDate()+"/"+ (dateGetter.getYear()-100);

                    String theCategory = item.getString("category");

                    Dates date = new Dates(theFancydate,item.getString("title"),item.getString("category"));
                   //Log.v("JSON", item.getString("title"));
                    Log.v("JSON", date.getTitle()+date.getCategory()+date.getDate());
                   // Log.v("JSON", item.getString("date"));
                    Log.v("JSON", "------------------------------");
                    dateList.add(date);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dateList;
        }


        protected void onPostExecute(ArrayList result) {
            for (int i =0;i<result.size();i++){
                Log.v("postexecute",((Dates)result.get(i)).getTitle());

            }
            Log.v("postexecute",result.size()+"");
            adapter.addAll(result);
        }
    }

}
