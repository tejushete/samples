package com.felight.wifienable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by Tejashree on 16-Feb-17.
 */

public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {


    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;

    public WiFiDirectBroadcastReceiver(WifiP2pManager mManager, WifiP2pManager.Channel mChannel, MainActivity mainActivity) {
    }
    //private MyWiFiActivity mActivity;

  //  public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
   //                                    MyWifiActivity activity) {
     //   super();
        //this.mManager = manager;
       // this.mChannel = channel;
        //this.mActivity = activity;
    //}

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
               // Wifi P2P is enabled;
            } else {
                //Wi-Fi P2P is not enabled;
            }
        }

    }


}


