package com.example.hwang1.avengerslabsoak;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class GraphicalAppsReceiver extends BroadcastReceiver{

    public static final String PREFS_NAME = "MyPrefsFile";


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        //Intent myIntent = new Intent(context, MainActivity.class);
        Log.d("In Received","BLAHBLAHBLAH");
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String allinone_br_stat = prefs.getString("allinone_br_stat", "0");
        if(allinone_br_stat.equals("00"))
        {
            Log.d("Starting","VideoCore");
            Intent myIntent = new Intent(context, VideoCore.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent);
        }
        else if(allinone_br_stat.equals("11"))
        {
            Log.d("Starting","GraphicalApps");
            Intent myIntent = new Intent(context, GraphicalApps.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent);
        }

    }
}
