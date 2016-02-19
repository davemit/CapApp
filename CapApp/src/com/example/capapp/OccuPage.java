package com.example.capapp;


import java.util.Locale;




import android.net.Uri;
import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import android.os.Handler;
import android.widget.Button;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.View;



public class OccuPage extends Activity {
	
	 

	//  private static final int PROGRESS = 0x1;
	  	 //For event handler
	     private ProgressBar mProgress;
	     private int mProgressStatus = 0;
	     ImageView starView;
	     private Handler mHandler = new Handler();
	     //for button
	     
	     protected void onCreate(Bundle icicle) {
	         super.onCreate(icicle);
	         setContentView(R.layout.activity_occu_page);
		     Button button1= (Button) findViewById(R.id.button1);
		     Button button2= (Button) findViewById(R.id.button2);
		     starView=(ImageView) findViewById(R.id.imageView1);
		     
		    
		     
		   
		     
		     
		     
		     starView.setOnClickListener(new View.OnClickListener() {
		           public void onClick(View v) {
		        	   
		        	   
		        	   Intent intent=new Intent(OccuPage.this,Star.class);
		        	   startActivity(intent);
		            }
		         });
		     
		     button2.setOnClickListener(new View.OnClickListener() {
		           public void onClick(View v) {
		        	   
		        	   
		        	   Intent intent=new Intent(OccuPage.this,DbExp.class);
		        	   //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK&Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
		        	   startActivity(intent);
		            }
		         });
	         button1.setOnClickListener(new View.OnClickListener() {
	           public void onClick(View v) {
	        
	            	   String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", 
		        			   34.670926, -82.829550, "C1 Parking lot");
		        	   Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		            	   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK&Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
		            	   intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
		            	    startActivity(intent);

	            }
	         });
	         
	         
	         
	         mProgress = (ProgressBar) findViewById(R.id.progress_bar);
	         // Start lengthy operation in a background thread
	         new Thread(new Runnable() {
	             public void run() {
	                 while (mProgressStatus < 100) {
	                     mProgressStatus = 60;

	                     // Update the progress bar
	                     mHandler.post(new Runnable() {
	                         public void run() {
	                             mProgress.setProgress(mProgressStatus);
	                         }
	                     });
	                 }
	             }
	         }).start();
	     }
	   

	     
}
