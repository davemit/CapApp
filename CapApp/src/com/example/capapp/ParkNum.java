package com.example.capapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ParkNum extends ActionBarActivity {

	Button button2;
	EditText pNum;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("yp", "here");
		
		setContentView(R.layout.activity_park_num);
		button2= (Button) findViewById(R.id.pbutton);
		pNum=(EditText) findViewById(R.id.parkNum);
		
	    button2.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	        	  new sendPostData().execute();
	       	   
	           }
	        });
	}
	
	public class sendPostData extends AsyncTask<String, Void, String>
	 {
	         @Override
	     protected String doInBackground(String... params) {  

	  try{

	 	
		  		  MainActivity.parkNumber =pNum.getText().toString();
	              String link="http://198.21.204.2/capapp/updateParkNum.php";
	                 String data  = URLEncoder.encode("latitude", "UTF-8") 
	                 + "=" + URLEncoder.encode(MainActivity.checkInLat, "UTF-8");
	                 data += "&" + URLEncoder.encode("longitude", "UTF-8") 
	                 + "=" + URLEncoder.encode(MainActivity.checkInLong, "UTF-8");
	                 data += "&" + URLEncoder.encode("parkNum", "UTF-8") 
	    	         + "=" + URLEncoder.encode(MainActivity.parkNumber, "UTF-8");
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
	 	       	   	Intent intent=new Intent(ParkNum.this,Checkout.class);
	 	       	   	startActivity(intent);
	         	    Log.d("yp", result);

	         }
	  }

	
}
