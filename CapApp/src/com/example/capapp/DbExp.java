package com.example.capapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
 
public class DbExp extends Activity {
 private String jsonResult;
 private String url = "http://198.21.204.2/capapp/myfile.php";
 //private ListView listView;
 private Button checkin;
 
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_parking_map);
  //listView = (ListView) findViewById(R.id.listView1);
  accessWebService();
  
  checkin=(Button) findViewById(R.id.checkin);
  checkin.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
         new sendPostData().execute();
         Intent intent=new Intent(DbExp.this,ParkNum.class);
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
              String link="http://198.21.204.2/capapp/checkin.php";
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
         	    Log.d("yp", "here");
         	    
             
            



         }
  }
 
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
  // Inflate the menu; this adds items to the action bar if it is present.
  getMenuInflater().inflate(R.menu.main, menu);
  return true;
 }
 
 // Async Task to access the web
 private class JsonReadTask extends AsyncTask<String, Void, String> {
  @Override
  protected String doInBackground(String... params) {
   HttpClient httpclient = new DefaultHttpClient();
   HttpPost httppost = new HttpPost(params[0]);
   try {
    HttpResponse response = httpclient.execute(httppost);
    jsonResult = inputStreamToString(
      response.getEntity().getContent()).toString();
   }
 
   catch (ClientProtocolException e) {
    e.printStackTrace();
   } catch (IOException e) {
    e.printStackTrace();
   }
   return null;
  }
 
  private StringBuilder inputStreamToString(InputStream is) {
   String rLine = "";
   StringBuilder answer = new StringBuilder();
   BufferedReader rd = new BufferedReader(new InputStreamReader(is));
 
   try {
    while ((rLine = rd.readLine()) != null) {
     answer.append(rLine);
    }
   }
 
   catch (IOException e) {
    // e.printStackTrace();
    Toast.makeText(getApplicationContext(),
      "Error..." + e.toString(), Toast.LENGTH_LONG).show();
   }
   return answer;
  }
 
  @Override
  protected void onPostExecute(String result) {
   ListDrwaer();
  }
 }// end async task
 
 public void accessWebService() {
  JsonReadTask task = new JsonReadTask();
  // passes values for the urls string array
  task.execute(new String[] { url });
 }
 
 // build hash set for list view
 public void ListDrwaer() {
  List<Map<String, String>> employeeList = new ArrayList<Map<String, String>>();
  GoogleMap map;
  double longitude;
  double latitude;
  longitude= -82.829083;
  latitude=34.672245;
	 
 
  try {
   JSONObject jsonResponse = new JSONObject(jsonResult);
   JSONArray jsonMainNode = jsonResponse.optJSONArray("checkout");
   //Map stuff
   LatLng pos = new LatLng(latitude, longitude);
   //setContentView(R.layout.activity_parking_map);

   map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
       .getMap();

   map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));
   map.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
   
   
   for (int i = 0; i < jsonMainNode.length(); i++) {
    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
    //latitude longitude taken from db
    String name = jsonChildNode.optString("latitude");
    String number = jsonChildNode.optString("longitude");
    String outPut = name + "-" + number;
    
    float lat = Float.parseFloat(name);
    float longi = Float.parseFloat(number);
    LatLng marker = new LatLng(lat, longi);
    map.addMarker(new MarkerOptions().position(marker)
            .title("location")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
    //employeeList.add(createEmployee("employees", outPut));
   }
  } catch (JSONException e) {
   Toast.makeText(getApplicationContext(), "Error" + e.toString(),
     Toast.LENGTH_SHORT).show();
  }
 /*
  SimpleAdapter simpleAdapter = new SimpleAdapter(this, employeeList,
    android.R.layout.simple_list_item_1,
    new String[] { "employees" }, new int[] { android.R.id.text1 });
  listView.setAdapter(simpleAdapter);*/
 }
 
 private HashMap<String, String> createEmployee(String name, String number) {
  HashMap<String, String> employeeNameNo = new HashMap<String, String>();
  employeeNameNo.put(name, number);
  return employeeNameNo;
 }
}