package com.example.studio;

import java.io.File;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainActivity extends ActionBarActivity {

	
	//dichiarazione frangment manager
	FragmentManager fragmentManager;
	FragmentTransaction fragmentTransaction;
	
	//dichiarazione handler per ricevere dati dai threads
	Handler mioHandler;
	
	//dichiarazione view
	Button frangment;
	TextView nome;
	TextView cognome;
	TextView id;
	TextView gender;
	
	//dichiarazione object mapper per machare uno Json con una classe java
	ObjectMapper objectMapper;
	
	//dichiarazione thread che si connette e invia la stringa con il Json al main
	MioThread thread;
	
	//dichiarazione del notificazion builder per creare una notifica
	NotificationCompat.Builder mBuilder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//istanzio il notificationCompat.builder settandogli l'icona, il titolo e il testo della 
		//mia notifica
		mBuilder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
			    .setContentTitle("My notification")
			    .setContentText("Hello World!");
		
		
		//dichiaro l'azione che avverrà al momento del tuch sulla notifica
		Intent resultIntent = new Intent(this, MainActivity.class);
		// Because clicking the notification opens a new ("special") activity, there's
		// no need to create an artificial back stack.
		PendingIntent resultPendingIntent =
		    PendingIntent.getActivity(
		    this,
		    0,
		    resultIntent,
		    PendingIntent.FLAG_UPDATE_CURRENT
		);
		
		//assegno l'intent all noticazion builder
		mBuilder.setContentIntent(resultPendingIntent);
		
		// Sets an ID for the notification
		int mNotificationId = 001;
		// Gets an instance of the NotificationManager service
		NotificationManager mNotifyMgr = 
		        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Builds the notification and issues it.
		mNotifyMgr.notify(mNotificationId, mBuilder.build());
		
		
		frangment = (Button) findViewById(R.id.bottone);
		
		ProvaFragment fr = new ProvaFragment();
		
		fragmentManager = getSupportFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.container, fr);
		
		
		nome = (TextView) findViewById(R.id.nome);
		cognome = (TextView) findViewById(R.id.cognome);
		id = (TextView) findViewById(R.id.id);
		gender = (TextView) findViewById(R.id.genere);
		
		
		
		mioHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
				Bundle pagina = msg.getData();
				String Json = pagina.getString("testo");
				
				try
				{
					objectMapper = new ObjectMapper();
					Persona persona = objectMapper.readValue(Json, Persona.class);
					
					nome.setText(persona.getfirst_name());
					cognome.setText(persona.getlast_name());
					id.setText(persona.getId()+"");
					gender.setText(persona.getGender());
				}
				catch(Exception e)
				{
					e.printStackTrace();
					Log.i("Json","coglione");
				}
				
				
				
			}
			
		};
		
		thread = new MioThread(mioHandler);
		
		Intent mServiceIntent = new Intent(this, ServizioNotifiche.class);
		//this.startService(mServiceIntent);
		
		
		frangment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//fragmentTransaction.commit();
				thread.start();
			}
		
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
