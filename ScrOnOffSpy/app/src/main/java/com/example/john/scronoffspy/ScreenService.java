package com.example.john.scronoffspy;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenService extends Service
{
    int ScrON, ScrOFF;
    IntentFilter filter1 = new IntentFilter();

    public ScreenService() {
    }



    //Create broadcast object
    BroadcastReceiver mybroadcast = new BroadcastReceiver()
    {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            SharedPreferences userpref = getSharedPreferences("tmpdata", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = userpref.edit();

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String now = sdf.format(c.getTime());


            Log.i("[BroadcastReceiver]", "MyReceiver");

            if(intent.getAction().equals(Intent.ACTION_SCREEN_ON))
            {
                Log.i("[BroadcastReceiver]", "Screen ON");

                ScrON++;
                editor.putString("on"+ScrON, now);
                editor.apply();
                if(ScrON>=3){ScrON=0;}
            }
            else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
            {
                Log.i("[BroadcastReceiver]", "Screen OFF");
                ScrOFF++;
                editor.putString("off"+ScrOFF, now);
                editor.apply();
                if(ScrOFF>=3){ScrOFF=0;}
            }

        }
    };








    @Override
    public int onStartCommand (Intent intent, int flags, int startId)
    {
        ScrON=0;
        ScrOFF=0;

        filter1.addAction(Intent.ACTION_SCREEN_ON);
        filter1.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mybroadcast, filter1);
        Toast.makeText(this, "ON - OFF Service Started", Toast.LENGTH_LONG).show();

        return START_STICKY;
    }



    public void onDestroy ()
    {
        Toast.makeText(this, "Stopping ON-OFF Service...", Toast.LENGTH_SHORT).show();
        unregisterReceiver(mybroadcast);
    }


    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

}
