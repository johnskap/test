package com.example.john.scronoffspy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends Activity
{
    Boolean validapp=false;
    File f = new File("/data/data/com.example.john.scronoffspy/shared_prefs/tmpdata.xml");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(RunUntil("2015-08-24"))
        {
            validapp=true;  //apla to evala
            tmpdatawork();
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            startActivity(intent);
            finish();

        }
        else
        {
            Toast.makeText(this, "Please buy the App", Toast.LENGTH_LONG).show();
            finish();
        }


    }







    public boolean RunUntil(String date) {

        Boolean valid=false;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(c.getTime());
        try {
            Date date1 = sdf.parse(now);
            Date date2 = sdf.parse(date);
            if(date1.compareTo(date2)<0)
            {
                valid=true;
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return valid;
    }




    public void tmpdatawork(){

        if (!f.exists())
        {
            SharedPreferences userpref = getSharedPreferences("tmpdata", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = userpref.edit();
            editor.putString("on1","--");
            editor.putString("on2","--");
            editor.putString("on3","--");
            editor.putString("off1","--");
            editor.putString("off2","--");
            editor.putString("off3","--");
            editor.apply();
        }

    }




}
