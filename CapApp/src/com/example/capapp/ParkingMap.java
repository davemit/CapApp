package com.example.capapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ParkingMap extends Activity {
  

	
  private GoogleMap map;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
	
    double longitude;
	double latitude;
	
	Bundle extras = getIntent().getExtras();  
	latitude = extras.getDouble("latitude");
	longitude = extras.getDouble("longitude");
	 LatLng pos = new LatLng(latitude, longitude);
	
    
    Log.d("Map","Activity Open");
    setContentView(R.layout.activity_parking_map);
    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
        .getMap();
    Marker m = map.addMarker(new MarkerOptions().position(pos)
        .title("location"));
   

    // Move the camera instantly to location with a zoom of 15.
    map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));

    // Zoom in, animating the camera.
    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
  }

}