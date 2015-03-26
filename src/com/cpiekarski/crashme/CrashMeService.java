package com.cpiekarski.crashme;

import java.lang.Override;

import android.app.Service;

import android.content.Intent;

import android.os.CountDownTimer;
import android.os.IBinder;

import android.util.Log;


/**
 * Service for crashing...
 */
public class CrashMeService extends Service {
    /**
     * LogCat TAG
     */
    public static final String TAG = "CrashMeService";
    public static boolean ON_CREATE = false;
    public static boolean ON_START = false;
    public static boolean ON_DESTROY = false;
    
    public void crashNow() {
        Log.v(TAG, "Throwing Service Exception Now");
        throw new RuntimeException("CrashMe Service Has Crashed");
    }
    
    public void crashTimer() {
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.v(TAG, "seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                crashNow();
            }
         }.start();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "On Create Called");
    }

    /**
     * Starts up the service. Currently only responds to start service intent.
     * 
     * @param intent Basic start intent (no extra keys)
     * @return int START_STICKY
     */
    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG, "CrashMeService - onStartCommand()");
        if(intent != null && intent.hasExtra("crashNow")) {
            crashNow(); //crashes prior to finishing onStartCommand
        }
        
        if(intent != null && intent.hasExtra("crashTimer")) {
            crashTimer(); //allows onStartCommand
        }
        
        return START_STICKY;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "CrashMeService onDestroy finished");
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}