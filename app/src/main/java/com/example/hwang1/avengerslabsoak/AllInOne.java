package com.example.hwang1.avengerslabsoak;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class AllInOne extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Thread.sleep(5000);
                    //inst.sendKeyDownUpSync(4);

                    //SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
                    //String allinoneStat = prefs.getString("allinone_stage_stat", "0");
                    //String allinonebrStat = prefs.getString("allinone_br_stat", "0");

                    try {
                        //int myNum = Integer.parseInt(allinoneStat);
                        allinone_stage();
                    } catch(NumberFormatException nfe) {

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void allinone_stage()
    {
        final String[] br_stage = new String[] {"00","11","22","33"};

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    Thread.sleep(5000);


                    SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    //editor.putString("allinone_stat", br_stage[0]);
                    //editor.commit();
                    String allinoneStat = prefs.getString("allinone_stat", "0");

                    if(allinoneStat.equals("1"))
                    {
                        Log.d("Starting", "Videocore");
                        editor.putString("allinone_stat", "2");
                        editor.commit();

                        Intent myIntent = new Intent(getApplicationContext(), VideoCore.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(myIntent);
                        Thread.sleep(30000);

                        editor.putString("allinone_br_stat", br_stage[0]);
                        editor.commit();

                        Log.d("Sending Broadcast", "LOLOLOL");
                        Intent Broadcast_intent = new Intent("finish_GraphicalApps");
                        sendBroadcast(Broadcast_intent);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                    }
        }).start();
    }
}