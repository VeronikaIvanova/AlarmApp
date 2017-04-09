package com.example.acer_pc.alarmapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TimePicker;

import com.example.acer_pc.alarmapp.models.Alarm;
import com.example.acer_pc.alarmapp.models.Melody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer-pc on 08.04.2017.
 */

public class SettingsActivity extends AppCompatActivity {
    Alarm alarm;
    ArrayList<Alarm> alarmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addition);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        alarm =(Alarm) getIntent().getSerializableExtra("alarm");
        alarmList = (ArrayList<Alarm>) getIntent().getSerializableExtra("alarmList");

        TimePicker timePicker= (TimePicker)findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(alarm.getHour());
        timePicker.setCurrentMinute(alarm.getMinute());

        CheckBox checkBox=(CheckBox) findViewById(R.id.checkboxEveryday);
        checkBox.setChecked(alarm.getPeriod());


        RadioButton radioButtonImp= (RadioButton)findViewById(R.id.radiobtnImperial);
        RadioButton radioButtonSil= (RadioButton)findViewById(R.id.radiobtnSilence);
        radioButtonImp.setChecked(alarm.getMelody()== Melody.IMPERIAL);
        radioButtonSil.setChecked(alarm.getMelody()== Melody.SILENCE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            }
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
                if (alarmList.contains(alarm))
                    alarmList.remove(alarm);
                alarmList.add(newAlarm);

                Intent intent=new Intent(this, MainActivity.class);
                intent.putExtra("alarmList", alarmList);


                startActivity(intent);
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
