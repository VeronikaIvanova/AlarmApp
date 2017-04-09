package com.example.acer_pc.alarmapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.acer_pc.alarmapp.helpers.AlarmListAdapter;
import com.example.acer_pc.alarmapp.models.Alarm;
import com.example.acer_pc.alarmapp.models.Melody;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

   Intent intent;
   private ListView listView;
   private ArrayList<Alarm> alarmList;
   private AlarmListAdapter alarmAdapter;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       setContentView(R.layout.main);
       listView = (ListView) findViewById(R.id.listView);
       TextView textView= (TextView) findViewById(R.id.textViewWelcome);
       alarmList = (ArrayList<Alarm>) getIntent().getSerializableExtra("alarmList");
       Button buttonSave=(Button) findViewById(R.id.buttonSave);
       if (alarmList==null){
           alarmList =new ArrayList<>();
           textView.setVisibility(View.VISIBLE);
           buttonSave.setVisibility(View.INVISIBLE);

       }else {
           textView.setVisibility(View.INVISIBLE);
           buttonSave.setVisibility(View.VISIBLE);
       }
       alarmAdapter = new AlarmListAdapter(this, R.layout.list_item, alarmList);
       listView.setAdapter(alarmAdapter);
   }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    public void removeAlarmOnClickHandler(View v) {
        Alarm itemToRemove = (Alarm) v.getTag();
        alarmAdapter.remove(itemToRemove);
    }

    public ArrayList<Alarm> getAllTurnedOn(){
        ArrayList<Alarm>alarms=new ArrayList<>();
        for(Alarm alarm : alarmList){
            if (alarm.getTurnedOn())
                alarms.add(alarm);
        }
        return alarms;
    }
    public void onClickSave(View v) {
        List<Alarm> alarms=getAllTurnedOn();
        Toast.makeText(getBaseContext(), "Alarms were sent", Toast.LENGTH_LONG);

    }

    public void changeToEditActivity(View v) {
        intent = new Intent(this, SettingsActivity.class);
        Alarm alarm= (Alarm)v.getTag();
        intent.putExtra("alarm",alarm);
        intent.putExtra("alarmList", alarmList);
        startActivity(intent);

    }

   private void initList() {
       alarmList.add(new Alarm(10,10,true, Melody.IMPERIAL));
       alarmList.add(new Alarm(10,20,true, Melody.SILENCE));
       alarmList.add(new Alarm(20,43,true, Melody.SILENCE));
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnAdd: {
                intent = new Intent(this, AdditionActivity.class);
                intent.putExtra("alarmList", alarmList);
                startActivity(intent);
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}



