package com.example.admin.week4wedhw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    MyDynamicReceiver myDynamicReceiver = new MyDynamicReceiver();
   // BroadcastReceiver br = new MyBroadcastReceiver();
    Intent normalIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void notificationBarActivate() {
        Notification.Builder notif;
        NotificationManager nm;
        notif = new Notification.Builder(getApplicationContext());
        notif.setSmallIcon(R.drawable.harunaono);
        notif.setContentTitle("");
        Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notif.setSound(path);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent playReceive = new Intent();
        playReceive.setAction(AppConstant.YES_ACTION);
        PendingIntent playIntent = PendingIntent.getBroadcast(this, 12345, playReceive, PendingIntent.FLAG_CANCEL_CURRENT);
        notif.addAction(0, "►", playIntent);


        Intent pauseReceive = new Intent();
        pauseReceive.setAction(AppConstant.PAUSE_ACTION);
        PendingIntent pauseIntent = PendingIntent.getBroadcast(this, 12345, pauseReceive, PendingIntent.FLAG_CANCEL_CURRENT);
        notif.addAction(0, "Ⅱ", pauseIntent);

        Intent StopReceive = new Intent();
        StopReceive.setAction(AppConstant.STOP_ACTION);
        PendingIntent stopIntent = PendingIntent.getBroadcast(this, 12345, StopReceive, PendingIntent.FLAG_CANCEL_CURRENT);
        notif.addAction(0, "◼", stopIntent);


        nm.notify(10, notif.getNotification());

    }

    public void musicPlayerClicks(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                normalIntent = new Intent(this, MyService.class);
                normalIntent.putExtra("data", "play");
                startService(normalIntent);
                notificationBarActivate();
                break;
            case R.id.btnPause:
                normalIntent = new Intent(this, MyService.class);
                normalIntent.putExtra("data", "pause");
                startService(normalIntent);
                break;
            case R.id.btnStop:

                normalIntent = new Intent(this, MyService.class);
                normalIntent.putExtra("data", "stop");
                stopService(normalIntent);

                break;
            case R.id.btnUseCustomBroadcast:
                //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
/*
                IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
                filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
                this.registerReceiver(myDynamicReceiver, filter);

                Intent in = new Intent();
                in.setAction("com.example.admin.week4wedhw.mypermission");
                in.addCategory("android.intent.category.DEFAULT");
                startActivity(in);
*/
             //   public static final String CUSTOM_INTENT = "jason.wei.custom.intent.action.TEST";
                /*
                Intent i = new Intent();
                i.setAction("com.example.admin.week4wedhw.mypermission");
                sendBroadcast(i);
                */

                String requiredPermission= "com.example.admin.week4wedhw.mypermission";
                /*
Intent intent = new Intent(this, Main3Activity.class);
                intent.putExtra("com.example.admin.week4wedhw.mypermission",3);
                startService(intent);
*/
                Intent send = new Intent("com.example.admin.week4wedhw.mypermission");
                send.putExtra("data", "Hello");
                sendBroadcast(send, requiredPermission);

                /*
                Intent intent = new Intent();
                intent.setAction("myActionForOtherApp");
                intent.putExtra("data", "hello");
                sendBroadcast(intent);
*/

/*
                // Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent();
                intent1.setAction("com.example.admin.week4wedhw.mypermission");
                intent1.putExtra("data", "something");
                //LocalBroadcastManager.getInstance(this).sendBroadcast(intent1); // this is so u dont send outside this app
                sendBroadcast(intent1);
                //registerReceiver(myDynamicReceiver, intent1, "com.android.MaliciousApp.RECEIVE_BROADCAST", null);


                if (ContextCompat.checkSelfPermission(this, Manifest.permission.mypermission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat
                            .requestPermissions(MainActivity.this, new String[]{Manifest.permission.mypermission}, 0);
                }
*/
                break;

            case R.id.btnOrderedBroadcast:
                broadcastintent();
                break;
        }
    }
    public void broadcastintent() {
        Intent intent = new Intent();
        intent.setAction("com.example.ordered_broadcast.OrderedBroadcast");
        sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
            @SuppressLint("NewApi")
            @Override
            public void onReceive(Context context, Intent intent) {
                              /*
                               * to capture result after all broadreceivers are finished
                               * executing
                               */
            }
        }, null, Activity.RESULT_OK, null, null);

    }
}
