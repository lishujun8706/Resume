package com.example.mainframework04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SMServer extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub		
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		new Thread(new Runnable() {
			public void run() {
				Log.d("sms", "oncreate");
				android.telephony.gsm.SmsManager sms=android.telephony.gsm.SmsManager.getDefault();
				sms.sendTextMessage("18914955682", null, "投递公司发来短信", null, null);
			}
		}).start();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d("sms", "oncreate");
		return super.onStartCommand(intent, flags, startId);
	}

	
}
