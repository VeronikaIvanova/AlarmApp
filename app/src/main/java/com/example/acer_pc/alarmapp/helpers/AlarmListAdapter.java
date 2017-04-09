package com.example.acer_pc.alarmapp.helpers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer_pc.alarmapp.MainActivity;
import com.example.acer_pc.alarmapp.R;
import com.example.acer_pc.alarmapp.models.Alarm;

import java.util.List;

/**
 * Created by acer-pc on 09.04.2017.
 */

public class AlarmListAdapter extends ArrayAdapter<Alarm> {

    private List<Alarm> items;
    private int layoutResourceId;
    private Context context;


    public AlarmListAdapter(Context context, int layoutResourceId, List<Alarm> items) {
        super(context, layoutResourceId, items);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.items = items;
    }

    public static class AlarmHolder {
        Alarm alarm;
        TextView name;
        Button editButton;
        ImageButton removeButton;
        Switch switchOn;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AlarmHolder holder = null;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder = new AlarmHolder();
        holder.alarm = items.get(position);

        holder.editButton = (Button)row.findViewById(R.id.buttonEdit);
        holder.editButton.setTag(holder.alarm);

        holder.removeButton = (ImageButton)row.findViewById(R.id.buttonDel);
        holder.removeButton.setTag(holder.alarm);

        holder.name = (TextView)row.findViewById(R.id.textViewAlarm);

        holder.switchOn= (Switch) row.findViewById(R.id.switchOn);
        holder.switchOn.setTag(holder.alarm);

        holder.switchOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                   Alarm alarm= (Alarm) buttonView.getTag();
                    alarm.setTurnedOn(true);


                }
                else {
                    ((Alarm) buttonView.getTag()).setTurnedOn(false);
                }
            }
        });
        row.setTag(holder);
        setupItem(holder);

        return row;
    }

    private void setupItem(AlarmHolder holder) {
        holder.name.setText(holder.alarm.getHour()+":"+holder.alarm.getMinute());

    }



}

