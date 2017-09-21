package com.example.admin.week4wedhw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import static android.R.attr.data;

public class MyDynamicReceiver extends BroadcastReceiver {
public static final String TAG = "TAG";

    MyDynamicReceiver myDynamicReceiver;
    Intent normalIntent;

    @Override
    public void onReceive(Context context, Intent intent) {

        String toastString = "";
        //Toast.makeText(context, "RECE "+toastString+"", Toast.LENGTH_SHORT).show();
        switch (intent.getAction()) {
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
                if (isAirplaneModeOn)
                    toastString = "Airplane on";
                    // Toast.makeText(context, "Airplane on", Toast.LENGTH_SHORT).show();

                else
                    toastString = "aurplane off";
                //  Toast.makeText(context, "Airplaine OFF", Toast.LENGTH_SHORT).show();
                break;

            case Intent.ACTION_POWER_CONNECTED:
                toastString = "POWER CONNECTED";
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                toastString = "POWER DISCONNECTED";
                break;
            case Intent.ACTION_HEADSET_PLUG:
                toastString = "HEADSET ACTION";
                break;
            case Intent.ACTION_SCREEN_ON:
                toastString = "SCREEN ON";

                break;
            case "com.example.admin.week4wedhw.mypermission":
                toastString= intent.getStringExtra("data");//, Toast.LENGTH_SHORT).show();
                PermissionReceiver receviver;
                break;

            case AppConstant.YES_ACTION:
                Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show();
                normalIntent = new Intent(context, MyService.class);
                normalIntent.putExtra("data", "play");
                context.startService(normalIntent);
                break;
            case AppConstant.PAUSE_ACTION:
                Toast.makeText(context, "PAUSE CALLED", Toast.LENGTH_SHORT).show();

                normalIntent = new Intent(context, MyService.class);
                normalIntent.putExtra("data", "pause");
                context.startService(normalIntent);

                break;

            case AppConstant.STOP_ACTION:
                Toast.makeText(context, "STOP CALLED", Toast.LENGTH_SHORT).show();
                try {
                    normalIntent = new Intent(context, MyService.class);
                    context.stopService(normalIntent);
                } catch (Exception e) {

                }
                break;
            case "myActionForOtherApp":
                toastString= intent.getStringExtra("data");//, Toast.LENGTH_SHORT).show();
                toastString = "d = "+ toastString;
                break;
/*
            IntentFilter filter = new this.IntentFilter();
            filter.addAction("SOME_ACTION");
            filter.addAction("SOME_OTHER_ACTION");


            normalIntent = new Intent(context, MyService.class);
            context.registerReceiver(myDynamicReceiver, filter, "com.android.MaliciousApp.RECEIVE_BROADCAST", null);
  */
 /*
        String action = intent.getAction();
        if (AppConstant.YES_ACTION.equals(action)) {
            Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show();
            normalIntent = new Intent(context , MyService.class);
            normalIntent.putExtra("data", "play");
            context.startService(normalIntent);
        }
        else  if (AppConstant.PAUSE_ACTION.equals(action)) {
            Toast.makeText(context, "PAUSE CALLED", Toast.LENGTH_SHORT).show();

                normalIntent = new Intent(context , MyService.class);
                normalIntent.putExtra("data", "pause");
                context.startService(normalIntent);

        }
        else  if (AppConstant.STOP_ACTION.equals(action)) {
            Toast.makeText(context, "STOP CALLED", Toast.LENGTH_SHORT).show();
           try {
               normalIntent = new Intent(context , MyService.class);
               context.stopService(normalIntent);
           }
           catch (Exception e)
           {

           }
        }
*/

        }
        Toast.makeText(context, toastString, Toast.LENGTH_SHORT).show();
    }

}
