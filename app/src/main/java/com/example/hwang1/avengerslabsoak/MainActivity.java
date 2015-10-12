package com.example.hwang1.avengerslabsoak;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class MainActivity extends Activity {

    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        // Wake up device

            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock fullWakeLock = powerManager.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "Loneworker - FULL WAKE LOCK");
            //PowerManager.WakeLock partialWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Loneworker - PARTIAL WAKE LOCK");
            fullWakeLock.acquire();

            KeyguardManager keyguardManager = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("TAG");
            keyguardLock.disableKeyguard();

        //
        // Define the pressable buttons

            Button one = (Button)findViewById(R.id.button1);
            Button two = (Button)findViewById(R.id.button2);
            Button three = (Button)findViewById(R.id.button3);
            Button four = (Button)findViewById(R.id.button4);
            Button five = (Button)findViewById(R.id.button5);

        //
        // Retrieve data to know if currently running reboot soak or allinone soak

            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
            String rebootStat = prefs.getString("reboot_stat", "0");
            String allinoneStat = prefs.getString("allinone_stat", "0");

        //
        // Check if should run allinone soak or reboot soak

            if(allinoneStat.equals("1"))
            {
                try {
                    final Handler myHandler = new Handler();
                    myHandler.postDelayed(all_in_one, 30000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(rebootStat.equals("1"))
            {
                try {
                    final Handler myHandler = new Handler();
                    myHandler.postDelayed(reboot, 120000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        //
        // VideoCore test

            one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    try {
                        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("allinone_stat", "0");
                        editor.putString("reboot_stat", "0");
                        editor.putString("allinone_br_stat", "0");
                        editor.commit();

                        Intent mVideoSoak = new Intent(MainActivity.this, VideoCore.class);
                        MainActivity.this.startActivity(mVideoSoak);
                        //finish();
                    } catch (Exception e) {
                        //Log.e(TAG, e.getMessage());
                    }
                }
            });

        //
        // Browser Soak

            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    try {
                        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("allinone_stat", "0");
                        editor.putString("reboot_stat", "0");
                        editor.commit();

                        Intent mBrowserSoak = new Intent(MainActivity.this, BrowserSoak.class);
                        MainActivity.this.startActivity(mBrowserSoak);
                        //finish();
                    } catch(Exception e){
                        //Log.e(TAG, e.getMessage());
                    }
                }
            });

        //
        // Reboot Soak Test

            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    try {
                        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("allinone_stat", "0");
                        editor.putString("reboot_stat", "1");
                        editor.commit();

                        Runtime.getRuntime().exec("reboot");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        //
        // Graphical Apps Soak

            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    try {
                        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("allinone_stat", "0");
                        editor.putString("reboot_stat", "0");
                        editor.commit();

                        Intent mGraphicalApps = new Intent(MainActivity.this, GraphicalApps.class);
                        MainActivity.this.startActivity(mGraphicalApps);
                        //finish();
                    } catch(Exception e){
                        //Log.e(TAG, e.getMessage());
                    }
                }
            });

        //
        // All-in-one

            five.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    try {
                        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("allinone_stat", "1");
                        editor.putString("reboot_stat", "0");
                        editor.commit();

                        Intent mAllInOneSoak = new Intent(MainActivity.this, AllInOne.class);
                        MainActivity.this.startActivity(mAllInOneSoak);
                        //finish();
                    } catch(Exception e){
                        //Log.e(TAG, e.getMessage());
                    }
                }
            });
    }


    //
    // Tool to check x-y coordinates on touch

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        float x = e.getX();
        float y = e.getY();
        String x1 = String.valueOf(x);
        String y2 = String.valueOf(y);
        Log.d("ONTOUCH_X", x1);
        Log.d("ONTOUCH_Y", y2);
        return true;
    }

    //
    // Reboot device - delayed with handler

    private Runnable reboot = new Runnable()
    {
        @Override
        public void run()
        {
            try {
                Runtime.getRuntime().exec("reboot");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    //
    // Allinone - delayed with handler

    private Runnable all_in_one = new Runnable()
    {
        @Override
        public void run()
        {
            Intent mAllInOneSoak = new Intent(MainActivity.this, AllInOne.class);
            MainActivity.this.startActivity(mAllInOneSoak);

        }
    };


    //
    // Default stuff

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    //
    // Not used, may be useful?

    /*
    private Runnable TestRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            try {



                //restartPackage ("com.hwang1.Spinning_Cube");
                //killBackgroundProcesses ("com.hwang1.Spinning_Cube");

                //ActivityInfo info = getPackageManager().getActivityInfo("com.hwang1.Spinning_Cube");
                //info.this.finish();

            }
            catch (Exception e){
                //e.printStackTrace();
            }
        }
    };
*/














    //
    // Below is connect wifi, not working


    private static final String INT_PRIVATE_KEY = "";
    private static final String INT_PHASE2 = "None";
    private static final String INT_PASSWORD = "@C1utools";
    private static final String INT_IDENTITY = "yowloadbuild";
    private static final String INT_EAP = "PEAP";
    private static final String INT_CLIENT_CERT = "";
    private static final String INT_CA_CERT = "";
    private static final String INT_ANONYMOUS_IDENTITY = "";
    final String INT_ENTERPRISEFIELD_NAME = "android.net.wifi.WifiConfiguration$RIMNET";





    void connect_wifi()
    {
        /********************************Configuration Strings****************************************************/
        final String ENTERPRISE_EAP = "PEAP";

        /*Optional Params- My wireless Doesn't use these*/
        final String ENTERPRISE_PHASE2 = "None";
        final String ENTERPRISE_ANON_IDENT = "";
        final String ENTERPRISE_CA_CERT = "";
        /********************************Configuration Strings****************************************************/

        /*Create a WifiConfig*/
        WifiConfiguration selectedConfig = new WifiConfiguration();

        /*AP Name*/
        selectedConfig.SSID = "devel05";

        /*Priority*/
        selectedConfig.priority = 40;

        /*Enable Hidden SSID*/
        selectedConfig.hiddenSSID = false;

        /*Key Mgmnt*/
        selectedConfig.allowedKeyManagement.clear();
        selectedConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.IEEE8021X);
        selectedConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);

        /*Group Ciphers*/
        selectedConfig.allowedGroupCiphers.clear();
        selectedConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        selectedConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        selectedConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
        selectedConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);

        /*Pairwise ciphers*/
        selectedConfig.allowedPairwiseCiphers.clear();
        selectedConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        selectedConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);

        /*Protocols*/
        selectedConfig.allowedProtocols.clear();
        selectedConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        selectedConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);

        // Enterprise Settings
        // Reflection magic here too, need access to non-public APIs
        try {
            // Let the magic start
            Class[] wcClasses = WifiConfiguration.class.getClasses();
            // null for overzealous java compiler
            Class wcEnterpriseField = null;

            for (Class wcClass : wcClasses)
                if (wcClass.getName().equals(INT_ENTERPRISEFIELD_NAME))
                {
                    wcEnterpriseField = wcClass;
                    break;
                }
            boolean noEnterpriseFieldType = false;
            if(wcEnterpriseField == null)
                noEnterpriseFieldType = true; // Cupcake/Donut access enterprise settings directly

            Field wcefAnonymousId = null, wcefCaCert = null, wcefClientCert = null, wcefEap = null, wcefIdentity = null, wcefPassword = null, wcefPhase2 = null, wcefPrivateKey = null;
            Field[] wcefFields = WifiConfiguration.class.getFields();
            // Dispatching Field vars
            for (Field wcefField : wcefFields)
            {
                if (wcefField.getName().equals(INT_ANONYMOUS_IDENTITY))
                    wcefAnonymousId = wcefField;
                else if (wcefField.getName().equals(INT_CA_CERT))
                    wcefCaCert = wcefField;
                else if (wcefField.getName().equals(INT_CLIENT_CERT))
                    wcefClientCert = wcefField;
                else if (wcefField.getName().equals(INT_EAP))
                    wcefEap = wcefField;
                else if (wcefField.getName().equals(INT_IDENTITY))
                    wcefIdentity = wcefField;
                else if (wcefField.getName().equals(INT_PASSWORD))
                    wcefPassword = wcefField;
                else if (wcefField.getName().equals(INT_PHASE2))
                    wcefPhase2 = wcefField;
                else if (wcefField.getName().equals(INT_PRIVATE_KEY))
                    wcefPrivateKey = wcefField;
            }


            Method wcefSetValue = null;
            if(!noEnterpriseFieldType){
                for(Method m: wcEnterpriseField.getMethods())
                    //System.out.println(m.getName());
                    if(m.getName().trim().equals("setValue"))
                        wcefSetValue = m;
            }


            /*EAP Method*/
            if(!noEnterpriseFieldType){
                wcefSetValue.invoke(wcefEap.get(selectedConfig), ENTERPRISE_EAP);
            }
            /*EAP Phase 2 Authentication*/
            if(!noEnterpriseFieldType){
                wcefSetValue.invoke(wcefPhase2.get(selectedConfig), ENTERPRISE_PHASE2);
            }
            /*EAP Anonymous Identity*/
            if(!noEnterpriseFieldType){
                wcefSetValue.invoke(wcefAnonymousId.get(selectedConfig), ENTERPRISE_ANON_IDENT);
            }
            /*EAP CA Certificate*/
            if(!noEnterpriseFieldType){
                wcefSetValue.invoke(wcefCaCert.get(selectedConfig), ENTERPRISE_CA_CERT);
            }


            /*EAP Identity*/
            if(!noEnterpriseFieldType){
                wcefSetValue.invoke(wcefIdentity.get(selectedConfig), "test user name");
            }
            /*EAP Password*/
            if(!noEnterpriseFieldType){
                wcefSetValue.invoke(wcefPassword.get(selectedConfig), "test password");
            }


            try{

            } catch (Exception e)
            {
                e.printStackTrace();
            }

        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        WifiManager wifiManag = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        boolean res1 = wifiManag.setWifiEnabled(true);
        int res = wifiManag.addNetwork(selectedConfig);
        Log.d("WifiPreference", "add Network returned " + res );
    //        boolean b = wifiManag.enableNetwork(selectedConfig.networkId, false);
    //        Log.d("WifiPreference", "enableNetwork returned " + b );
    //        boolean c = wifiManag.saveConfiguration();
    //        Log.d("WifiPreference", "Save configuration returned " + c );
    //        boolean d = wifiManag.enableNetwork(res, true);
    //        Log.d("WifiPreference", "enableNetwork returned " + d );


    }


}

