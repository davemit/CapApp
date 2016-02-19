package com.example.capapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends Activity {
    
    private EditText username,password;
    private Button login;
    //private static String user,pass,val,result="";

@Override
protected void onCreate(Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login_page);
    username=(EditText) findViewById(R.id.edit_cuid);
    password=(EditText) findViewById(R.id.edit_password);
    login=(Button) findViewById(R.id.LogIn);
    
    login.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            new sendPostData().execute();

        }
    });
}

private class sendPostData extends AsyncTask<String, Void, String>
{
        @Override
    protected String doInBackground(String... params) {  

 try{

	 MainActivity.user = username.getText().toString(); 
	 MainActivity.pass = password.getText().toString();
             String link="http://198.21.204.2/capapp/samp.php";
                String data  = URLEncoder.encode("username", "UTF-8") 
                + "=" + URLEncoder.encode(MainActivity.user, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") 
                + "=" + URLEncoder.encode(MainActivity.pass, "UTF-8");
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
           if(result.equals("wrong"))
           {
        	   
               Toast toast=Toast.makeText(LoginPage.this,"incorrect password", Toast.LENGTH_LONG);
               toast.show(); 
           }
           else
           {
        	   MainActivity.points = result;
        	   Log.d("yp", result);
               //Intent intent=new Intent(LoginPage.this,OccuPage.class);
        	   Intent intent=new Intent(LoginPage.this,OccuPage.class);
               startActivity(intent);
           }




        }
 }
}
