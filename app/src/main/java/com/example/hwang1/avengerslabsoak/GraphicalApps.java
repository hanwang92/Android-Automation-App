package com.example.hwang1.avengerslabsoak;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;

import java.util.Random;

public class GraphicalApps extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        final String allinone_stat = prefs.getString("allinone_stat", "0");
        Instrumentation inst = new Instrumentation();

        if (allinone_stat.equals("2")) {
            Log.d("AllInOne", "GraphicalApps");
            Log.d("BR_STAT", allinone_stat);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int count = 0; count < 3; count++) {

                            try {

                            Intent GraphicalApp1 = getPackageManager().getLaunchIntentForPackage("com.hwang1.Spinning_Cube");
                            Intent GraphicalApp2 = getPackageManager().getLaunchIntentForPackage("com.hwang1.shining");
                            Instrumentation inst = new Instrumentation();

                            startActivity(GraphicalApp1);
                            Thread.sleep(12000);
                            finish();
                            startActivity(GraphicalApp2);
                            for (int count2 = 0; count2 < 5; count2++){
                                rand_drag(inst);
                            }

                            Thread.sleep(6000);
                            if (count != 2) {
                                finish();
                            }
                                Log.d("WHAT HAPPENS HERE", "");

                                Intent intent = new Intent(getApplicationContext(), BrowserSoak.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                //finish();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }).start();

        } else {
            Log.d("Loop", "GraphicalApps");
            Log.d("BR_STAT", allinone_stat);
            try {
                Intent GraphicalApp1 = getPackageManager().getLaunchIntentForPackage("com.hwang1.Spinning_Cube");
                Intent GraphicalApp2 = getPackageManager().getLaunchIntentForPackage("com.hwang1.shining");

                while (true) {
                    startActivity(GraphicalApp1);
                    Thread.sleep(12000);
                    finish();
                    startActivity(GraphicalApp2);
                    for (int count2 = 0; count2 < 5; count2++){
                        rand_drag(inst);
                    }

                    Thread.sleep(6000);
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //
        // Can be useful

            /*
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();*/
    }

    void rand_drag(Instrumentation inst) {
        try {
            Display mdisp = getWindowManager().getDefaultDisplay();
            Point mdispSize = new Point();
            mdisp.getSize(mdispSize);
            int maxX = mdispSize.x;
            int maxY = mdispSize.y;

            Random rand = new Random();
            int random_direction = rand.nextInt(5);

            switch(random_direction) {
                case 0:
                    // Up
                    try {

                        int drag_distance = rand.nextInt(1500) + 500;
                        int start_pos_Y = rand.nextInt(maxY - drag_distance) + drag_distance;
                        int start_pos_X = rand.nextInt(maxX);

                        inst.sendPointerSync(MotionEvent.obtain(
                                android.os.SystemClock.uptimeMillis(),
                                android.os.SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_DOWN, start_pos_X, start_pos_Y, 0));

                        while (drag_distance > 0)
                        {
                            inst.sendPointerSync(MotionEvent.obtain(
                            android.os.SystemClock.uptimeMillis(),
                            android.os.SystemClock.uptimeMillis(),
                            MotionEvent.ACTION_MOVE,start_pos_X, start_pos_Y, 0));

                            //Thread.sleep(500);
                            start_pos_Y -= 10;
                            drag_distance -= 10;
                        }

                        inst.sendPointerSync(MotionEvent.obtain(
                        android.os.SystemClock.uptimeMillis(),
                        android.os.SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_UP,start_pos_X, start_pos_Y, 0));

                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }
                case 1:
                    // Down
                    try {
                        int drag_distance = rand.nextInt(1500) + 500;
                        int start_pos_Y = rand.nextInt(maxY - drag_distance);
                        int start_pos_X = rand.nextInt(maxX);

                        inst.sendPointerSync(MotionEvent.obtain(
                                android.os.SystemClock.uptimeMillis(),
                                android.os.SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_DOWN,start_pos_X, start_pos_Y, 0));

                        while (drag_distance > 0)
                        {
                            inst.sendPointerSync(MotionEvent.obtain(
                                    android.os.SystemClock.uptimeMillis(),
                                    android.os.SystemClock.uptimeMillis(),
                                    MotionEvent.ACTION_MOVE,start_pos_X, start_pos_Y, 0));

                            //Thread.sleep(500);
                            start_pos_Y += 10;
                            drag_distance -= 10;
                        }

                        inst.sendPointerSync(MotionEvent.obtain(
                                android.os.SystemClock.uptimeMillis(),
                                android.os.SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_UP, start_pos_X, start_pos_Y, 0));

                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }

                    break;
                case 2:
                    // Left
                    try {
                        int drag_distance = rand.nextInt(1500) + 500;
                        int start_pos_Y = rand.nextInt(maxY);
                        int start_pos_X = rand.nextInt(maxX - drag_distance) + drag_distance;

                        inst.sendPointerSync(MotionEvent.obtain(
                                android.os.SystemClock.uptimeMillis(),
                                android.os.SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_DOWN,start_pos_X, start_pos_Y, 0));

                        while (drag_distance > 0)
                        {
                            inst.sendPointerSync(MotionEvent.obtain(
                                    android.os.SystemClock.uptimeMillis(),
                                    android.os.SystemClock.uptimeMillis(),
                                    MotionEvent.ACTION_MOVE,start_pos_X, start_pos_Y, 0));

                            //Thread.sleep(500);
                            start_pos_X -= 10;
                            drag_distance -= 10;
                        }

                        inst.sendPointerSync(MotionEvent.obtain(
                                android.os.SystemClock.uptimeMillis(),
                                android.os.SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_UP,start_pos_X, start_pos_Y, 0));

                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }
                    break;
                case 3:
                    // Right
                    try {
                        int drag_distance = rand.nextInt(1500) + 500;
                        int start_pos_Y = rand.nextInt(maxY);
                        int start_pos_X = rand.nextInt(maxX - drag_distance);

                        inst.sendPointerSync(MotionEvent.obtain(
                                android.os.SystemClock.uptimeMillis(),
                                android.os.SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_DOWN,start_pos_X, start_pos_Y, 0));

                        while (drag_distance > 0)
                        {
                            inst.sendPointerSync(MotionEvent.obtain(
                                    android.os.SystemClock.uptimeMillis(),
                                    android.os.SystemClock.uptimeMillis(),
                                    MotionEvent.ACTION_MOVE,start_pos_X, start_pos_Y, 0));

                            //Thread.sleep(500);
                            start_pos_X += 10;
                            drag_distance -= 10;
                        }

                        inst.sendPointerSync(MotionEvent.obtain(
                                android.os.SystemClock.uptimeMillis(),
                                android.os.SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_UP,start_pos_X, start_pos_Y, 0));

                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }
                    break;
                default:
                    // Up
                    try {

                        int drag_distance = rand.nextInt(1500) + 500;
                        int start_pos_Y = rand.nextInt(maxY - drag_distance) + drag_distance;
                        int start_pos_X = rand.nextInt(maxX);

                        inst.sendPointerSync(MotionEvent.obtain(
                                android.os.SystemClock.uptimeMillis(),
                                android.os.SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_DOWN, start_pos_X, start_pos_Y, 0));

                        while (drag_distance > 0)
                        {
                            inst.sendPointerSync(MotionEvent.obtain(
                                    android.os.SystemClock.uptimeMillis(),
                                    android.os.SystemClock.uptimeMillis(),
                                    MotionEvent.ACTION_MOVE,start_pos_X, start_pos_Y, 0));

                            //Thread.sleep(500);
                            start_pos_Y -= 10;
                            drag_distance -= 10;
                        }

                        inst.sendPointerSync(MotionEvent.obtain(
                                android.os.SystemClock.uptimeMillis(),
                                android.os.SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_UP,start_pos_X, start_pos_Y, 0));

                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }
            }

            Thread.sleep(500);

        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

    }
}
