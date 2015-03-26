
package com.cpiekarski.crashme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class CrashMeActivity extends Activity {
    public static final String TAG = "CrashMeActivity";

    public void crashActivityNow(View view) {
        Log.v(TAG, "Throwing Activity Exception now");
        throw new RuntimeException("CrashMe Activity Has Crashed");
    }
    
    public void crashServiceNow(View view) {
        Log.v(TAG, "Throwing Service Exception Now");
        Intent intent = new Intent(this, CrashMeService.class);
        intent.putExtra("crashNow", true);
        startService(intent);
    }
    
    public void startServiceCrashTimer(View view) {
        Log.v(TAG, "Starting Service Exception Timer Now");
        Intent intent = new Intent(this, CrashMeService.class);
        intent.putExtra("crashTimer", true);
        startService(intent);
    }
    
    public void startService() {
        Intent intent = new Intent(this, CrashMeService.class);
        startService(intent);
    }
    
    public void startANRCrash(View view) {
        int i = 0;
        while(true) {
            String tmp = new String("ANR String Allocation Attempt: "+i);
            ++i;
        }
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "Activity On Create Called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
