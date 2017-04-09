package com.example.acer_pc.alarmapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TimePicker;

import com.example.acer_pc.alarmapp.models.Alarm;
import com.example.acer_pc.alarmapp.models.Melody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by acer-pc on 08.04.2017.
 */

public class AdditionActivity extends AppCompatActivity {

    ArrayList<Alarm> alarmList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addition);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TimePicker timePicker= (TimePicker)findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        alarmList = (ArrayList<Alarm>) getIntent().getSerializableExtra("alarmList");
        Calendar calendar =Calendar.getInstance();
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        RadioButton radioButtonImp=(RadioButton)findViewById(R.id.radiobtnImperial);
        radioButtonImp.setChecked(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public ArrayList<Alarm> getAllTurnedOn(){
        ArrayList<Alarm>alarms=new ArrayList<>();
        for(Alarm alarm : alarmList){
            if (alarm.getTurnedOn())
                alarms.add(alarm);
        }
        return alarms;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.btnOk: {
                Alarm newAlarm = new Alarm();
                TimePicker timePicker=(TimePicker)findViewById(R.id.timePicker);
                newAlarm.setHour(timePicker.getCurrentHour());
                newAlarm.setMinute(timePicker.getCurrentMinute());

                RadioButton radioButtonIm=(RadioButton)findViewById(R.id.radiobtnImperial);
                if (radioButtonIm.isChecked())
                    newAlarm.setMelody(Melody.IMPERIAL);
                else
                    newAlarm.setMelody(Melody.SILENCE);

                CheckBox checkBox =(CheckBox) findViewById(R.id.checkboxEveryday);
                newAlarm.setPeriod(checkBox.isChecked());

                Intent intent=new Intent(this, MainActivity.class);
                alarmList.add(newAlarm);
                intent.putExtra("alarmList", alarmList);


                startActivity(intent);

            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
