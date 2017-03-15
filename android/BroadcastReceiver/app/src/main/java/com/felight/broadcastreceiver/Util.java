package com.felight.broadcastreceiver;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Tejashree on 07-Feb-17.
 */

public class Util {
    public static void toastlt(Context context,String msg){
        Toast toast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
