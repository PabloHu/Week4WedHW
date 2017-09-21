package com.example.admin.week4wedhw;

/**
 * Created by Admin on 9/21/2017.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "MyReceiver1 provoked", Toast.LENGTH_LONG).show();
    }

}