package com.example.capapp;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Test extends Activity {
	 
	  private RadioGroup radioGroup;
	  private RadioButton radioButton;
	  private Button btnDisplay;
	 
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
	 
		//addListenerOnButton();
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		btnDisplay = (Button) findViewById(R.id.btnDisplay);
	 
		btnDisplay.setOnClickListener(new View.OnClickListener() {
	 
			
			public void onClick(View v) {
	 
			        // get selected radio button from radioGroup
				int selectedId = radioGroup.getCheckedRadioButtonId();
	 
				// find the radiobutton by returned id
			        radioButton = (RadioButton) findViewById(selectedId);
			        if(selectedId ==R.id.radio3){
			        	
			        	int myNum = Integer.parseInt(MainActivity.points);
			        	myNum += 5;
			        	MainActivity.points =  String.valueOf(myNum);
			        	Intent intent=new Intent(Test.this,Star.class);
			        	startActivity(intent);
			        	
			        		Toast.makeText(Test.this,
			        		"Correct Answer", Toast.LENGTH_SHORT).show();
			        		}
			        else{
			        	Toast.makeText(Test.this,
				        		"Try Again", Toast.LENGTH_SHORT).show();
			        }
			        }
	 
			
		
	 
		});
	 
	  }
	 
	  public void addListenerOnButton() {
	 
	
	 
	  }
	}