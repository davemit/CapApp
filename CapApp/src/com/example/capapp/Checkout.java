package com.example.capapp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.example.capapp.DbExp.sendPostData;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;

public class Checkout extends Activity {
    Chronometer mChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkout);

        Button button = (Button) findViewById(R.id.start);

        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.setFormat("%s");
        mChronometer.start();
        
        button.setOnClickListener(new OnClickListener() {
        	@Override
            public void onClick(View v) {
                //mChronometer.start();
            	new sendPostData().execute();
            	Intent intent=new Intent(Checkout.this,OccuPage.class);
           	   	startActivity(intent);
            }
        });

        
    }

    
    public class sendPostData extends AsyncTask<String, Void, String>
    {
            @Override
        protected String doInBackground(String... params) {  

     try{
    	         
    	 			MainActivity.checkInLat = "34.6716"; 
    	 			MainActivity.checkInLong = "-82.8301";
                 String link="http://198.21.204.189/capapp/manageCheckout.php";
                    String data  = URLEncoder.encode("latitude", "UTF-8") 
                    + "=" + URLEncoder.encode(MainActivity.checkInLat, "UTF-8");
                    data += "&" + URLEncoder.encode("longitude", "UTF-8") 
                    + "=" + URLEncoder.encode(MainActivity.checkInLong, "UTF-8");
                    URL url = new URL(link);
                    URLConnection conn = url.openConnection(); 
                    conn.setDoOutput(true); 
                    OutputStreamWriter wr = new OutputStreamWriter
                    (conn.getOutputStream()); 
                    wr.write( data ); 
                    wr.flush(); 
                    BufferedReader reader = new BufferedReader
                    (new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                       sb.append(line);
                       break;
                    }
                   return sb.toString();
                 }catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                 }
        }
           @Override
        protected void onPostExecute(String result) {
            //View your result here.
              
           	    finish();
           	    Intent intent=new Intent(Checkout.this,OccuPage.class);
	       	   	startActivity(intent);
            	    Log.d("yp", "here");
            	    
 
            }
     }
    
  
}