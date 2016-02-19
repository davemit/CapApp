package com.example.capapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

public class Star extends ActionBarActivity {
	TextView points;
	Button earn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star);
		points =(TextView) findViewById(R.id.txt2);
		points.setText(MainActivity.points);
		
		earn= (Button) findViewById(R.id.earn);
		earn.setOnClickListener(new View.OnClickListener() {
	           public void onClick(View v) {
	        	   
	        	   
	        	   Intent intent=new Intent(Star.this,ParkServe.class);
	        	   //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK&Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
	        	   startActivity(intent);
	            }
	         });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.star, menu);
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
}
