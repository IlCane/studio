package com.example.studio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MioThread extends Thread{

	Handler hand;
	Bundle b = new Bundle();
	Message m = new Message();
	
	ConnectivityManager cm;
	
	public MioThread(Handler h)
	{
		hand = h;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String line;
		
		try
		{
			
			URL url = new URL("http://graph.facebook.com/luca-IlCane-gradizzi");
			HttpURLConnection urlConnection =
			(HttpURLConnection)url.openConnection();
			Log.i("con","connesso");
			
			BufferedReader in = new BufferedReader(new
					InputStreamReader (urlConnection.getInputStream()));
			Log.i("con","stream");
					
					String testo = "";
					while ((line = in.readLine()) != null) {
						testo += line;
						Log.i("con","leggo");
					}
					Log.i("Json",testo);
					b.putString("testo",testo);
					m.setData(b);
					hand.sendMessage(m);
					
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
