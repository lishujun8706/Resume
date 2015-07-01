package com.example.mainframework04;

import android.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

public class BOOTBroadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		android.telephony.gsm.SmsManager sms=android.telephony.gsm.SmsManager.getDefault();
		sms.sendTextMessage("18914955682", null, "投递公司发来短信", null, null);
		Toast.makeText(arg0, "发送短信", 2000).show();
	}

}
