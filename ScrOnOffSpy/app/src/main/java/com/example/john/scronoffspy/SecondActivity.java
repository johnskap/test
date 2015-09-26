package com.example.john.scronoffspy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SecondActivity extends Activity
{
    TextView tVon1, tVon2, tVon3;
    TextView tVoff1, tVoff2, tVoff3;
    Button butStart, butStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        butStart = (Button) findViewById(R.id.button1);
        butStart.setOnClickListener(butListener1);

        butStop = (Button) findViewById(R.id.button2);
        butStop.setOnClickListener(butListener1);
        butStop.setOnLongClickListener(butListener2);

        DispData();



    }





    View.OnLongClickListener butListener2 = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v)
        {
            Intent intent = new Intent(getApplicationContext(), ScreenService.class);
            stopService(intent);
            return false;
        }
    };






    View.OnClickListener butListener1 = new View.OnClickListener() {

        @Override
        public void onClick(View v)
        {
            if (v == butStart) {
                Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                startService(intent);
                finish();
            }
            if (v == butStop)
            {
                Toast.makeText(getApplicationContext(), "Fake Stop...", Toast.LENGTH_LONG).show();
                finish();
            }

        }

    };





    public void DispData()
    {
        tVon1 = (TextView) findViewById(R.id.tvon1);
        tVon2 = (TextView) findViewById(R.id.tvon2);
        tVon3 = (TextView) findViewById(R.id.tvon3);

        tVoff1 = (TextView) findViewById(R.id.tvoff1);
        tVoff2 = (TextView) findViewById(R.id.tvoff2);
        tVoff3 = (TextView) findViewById(R.id.tvoff3);

        SharedPreferences userpref = getSharedPreferences("tmpdata", Context.MODE_PRIVATE);

        String tmp1 = userpref.getString("on1", "");
        tVon1.setText(tmp1);
        String tmp2 = userpref.getString("on2", "");
        tVon2.setText(tmp2);
        String tmp3 = userpref.getString("on3", "");
        tVon3.setText(tmp3);

        tmp1 = userpref.getString("off1", "");
        tVoff1.setText(tmp1);
        tmp2 = userpref.getString("off2", "");
        tVoff2.setText(tmp2);
        tmp3 = userpref.getString("off3", "");
        tVoff3.setText(tmp3);

    }




}
