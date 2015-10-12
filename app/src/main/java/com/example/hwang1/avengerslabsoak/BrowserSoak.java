package com.example.hwang1.avengerslabsoak;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;

import java.util.Random;

public class BrowserSoak extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String[] addresses = new String[] {
                "https://www.google.com/",
                "https://www.youtube.com/",
                "https://www.facebook.com/",
                "https://ca.yahoo.com/",
                "https://en.wikipedia.org/",
                "http://www.amazon.ca/",
                "https://twitter.com/",
                "http://world.taobao.com/",
                "http://www.qq.com/",
                "http://www.baidu.com" };

    new Thread() {
        @Override
        public void run() {
            try {
                Instrumentation inst = new Instrumentation();
                //Thread.sleep(6000);

                int count = 0;
                while (count < 3) {
                    Random rand = new Random();
                    int randomNum = rand.nextInt(10);
                    Uri address = Uri.parse(addresses[randomNum]);
                    Intent browser = new Intent(Intent.ACTION_VIEW, address);
                    startActivity(browser);
                    Thread.sleep(5000);

                    for (int count1 = 0; count1 < (rand_count()+5); count1++) {
                        for (int count2 = 0; count2 < (rand_count()+5); count2++) {
                            rand_touch_screen(inst);

                            rand_sleep();
                        }

                        //rand_animal(inst);
                        //rand_sleep();

                        /*
                        for (int count2 = 0; count2 < (rand_count()+20); count2++) {
                            rand_touch_screen(inst);
                            rand_sleep();
                        }
*/
                        for (int count2 = 0; count2 < rand_count(); count2++) {
                            rand_drag(inst);
                            rand_sleep();
                        }

                        //rand_animal(inst);
                        //rand_sleep();
                    }
                    count++;
                }
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("allinone_stat", "1");
                editor.putString("allinone_br_stat", "0");
                editor.commit();

                Runtime.getRuntime().exec("reboot");
            } catch (Exception e) {
                Log.e("Error", e.toString());
            }

        }}.start();

                    /*
                    Process process = Runtime.getRuntime().exec("su");
                    DataOutputStream os = new DataOutputStream(process.getOutputStream());
                    String cmd = "/system/bin/input tap 400 100\n";
                    os.writeBytes(cmd);
                    os.writeBytes("exit\n");
                    os.flush();
                    os.close();
                    process.waitFor();
                    Log.d("DONE","DONE");
*/


    //myHandler.postDelayed(TestRunnable, 6000);
    //myHandler.postDelayed(MyRunnable_up, 6000);
}

    int rand_count ()
    {
        Random rand = new Random();
        int randomNum = rand.nextInt(5);
        return randomNum;
    }

    void rand_sleep ()
    {
        try {
            Random rand = new Random();
            int random_sleep = rand.nextInt(3)+1;
            Thread.sleep(random_sleep * 1000);
        }catch (Exception e) {
        Log.e("Error", e.toString());
    }

    }

    void rand_touch_screen (Instrumentation inst)
    {
        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        int maxX = mdispSize.x;
        int maxY = mdispSize.y;

        Random rand = new Random();
        int randomX = rand.nextInt(maxX);
        int randomY = rand.nextInt(maxY - 400) + 400;

        try{
            inst.sendPointerSync(MotionEvent.obtain(
                    android.os.SystemClock.uptimeMillis(),
                    android.os.SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_DOWN, randomX, randomY, 0));

            Thread.sleep(300);

            inst.sendPointerSync(MotionEvent.obtain(
                    android.os.SystemClock.uptimeMillis(),
                    android.os.SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_UP, randomX, randomY, 0));

            Thread.sleep(300);

        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    void rand_animal(Instrumentation inst)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((6 - 1) + 1) + 1;

        switch(randomNum) {
            case 1:
            // Dog
                try{
                    inst.sendKeyDownUpSync(32);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(43);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(35);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

                break;
            case 2:
            // Cat
                try{
                    inst.sendKeyDownUpSync(31);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(29);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(48);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
                break;
            case 3:
            // Horse
                try{
                    inst.sendKeyDownUpSync(36);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(43);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(46);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(47);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(33);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
                break;
            case 4:
            // Rabbit
                try{
                    inst.sendKeyDownUpSync(46);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(29);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(30);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(30);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(37);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(48);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
                break;
            case 5:
            // Deer
                try{
                    inst.sendKeyDownUpSync(32);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(33);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(33);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(46);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
                break;
            case 6:
            // Fox
                try{
                    inst.sendKeyDownUpSync(34);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(43);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(52);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
                break;
            default:
            // Rabbit
                try{
                    inst.sendKeyDownUpSync(46);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(29);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(30);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(30);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(37);
                    Thread.sleep(500);
                    inst.sendKeyDownUpSync(48);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
        }
    }

    void rand_drag(Instrumentation inst)
    {
        try
        {
            Display mdisp = getWindowManager().getDefaultDisplay();
            Point mdispSize = new Point();
            mdisp.getSize(mdispSize);
            int maxX = mdispSize.x;
            int maxY = mdispSize.y;

            Random rand = new Random();
            int random_direction = rand.nextInt(4);


            try {
                int drag_distance = rand.nextInt(1500) + 500;
                int start_pos_Y = rand.nextInt(maxY - drag_distance) + drag_distance;
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

    /*
            switch(random_direction) {
                case 0:
                    // Up
                    try {

                        int drag_distance = rand.nextInt(1000);
                        int start_pos_Y = rand.nextInt(maxY - drag_distance) + drag_distance;
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
                        int drag_distance = rand.nextInt(1000);
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
                        int drag_distance = rand.nextInt(1000);
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
                        int drag_distance = rand.nextInt(1000);
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
                    // Down
                    try{
                        int drag_distance = rand.nextInt(1000);
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
                                MotionEvent.ACTION_UP,start_pos_X, start_pos_Y, 0));

                    } catch (Exception e) {
                        Log.e("Error", e.toString());
                    }
            }
*/
            Thread.sleep(3000);

        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

    }
}
