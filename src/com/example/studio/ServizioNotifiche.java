package com.example.studio;

import java.util.Calendar;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class ServizioNotifiche extends IntentService{

	//dichiarazione del notificazion builder per creare una notifica
	NotificationCompat.Builder mBuilder;
	
	Calendar c;
	int secondi;
		
	public ServizioNotifiche() {
		super("sistNotifiche");
		// TODO Auto-generated constructor stub
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
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
				    PendingIntent.FLAG_UPDATE_CURRENT);
				
				
		//assegno l'intent all noticazion builder
		mBuilder.setContentIntent(resultPendingIntent);
		
		
		for(int i = 0; i < 10; i++)
		{
				try{
				Thread.sleep(5000);
				}
				catch(Exception e)
				{
					
				}
				// Sets an ID for the notification
				int mNotificationId = i;
				// Gets an instance of the NotificationManager service
				NotificationManager mNotifyMgr = 
				        	(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				// Builds the notification and issues it.
				mNotifyMgr.notify(mNotificationId, mBuilder.build());
		}
		
	}
	

}
