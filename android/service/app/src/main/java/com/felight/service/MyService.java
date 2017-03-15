package com.felight.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Tejashree on 17-Feb-17.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
        try{
            Thread.sleep(5000);

        }
        catch (InterruptedException e){

        }
        Log.i("MyService","My Service Running");
    }

        }

}


