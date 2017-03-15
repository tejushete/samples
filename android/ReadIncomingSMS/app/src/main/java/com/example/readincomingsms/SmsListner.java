package com.example.readincomingsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Tejashree on 27-Feb-17.
 */

public class SmsListner extends BroadcastReceiver {
    SharedPreferences pref;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String format = bundle.getString("format");
            String msg_from;
            if (bundle != null){
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    pref =  context.getSharedPreferences("myPrefs",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i],format);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                        Log.d("TAG", msg_from+", body:"+ msgBody);
                        editor.putString("from",msg_from);
                        editor.putString("body",msgBody);
                        editor.commit();
                    }
                }catch(Exception e){
                            Log.d("TAG",e.getMessage());
                }
            }
        }

    }
}
