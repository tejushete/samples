package com.felight.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Tejashree on 07-Feb-17.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("My Receiver","I am Teju");
        Util.toastlt(context,"Aeroplane mode");

    }
}
