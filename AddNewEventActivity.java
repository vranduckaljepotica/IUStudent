package ius.iustudent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import ius.iustudent.models.Event;

public class AddNewEventActivity extends AppCompatActivity {
    private Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Add new class");
        event = new Event();
        final EditText name = (EditText) findViewById(R.id.course);
        Spinner dayPicker = (Spinner) findViewById(R.id.day_picker);
        String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        dayPicker.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days));
        LinearLayout startPicker = (LinearLayout) findViewById(R.id.start_time_picker);
        LinearLayout endPicker = (LinearLayout) findViewById(R.id.end_time_picker);
        Button addBtn = (Button) findViewById(R.id.add_course_btn);
        final TextView start_time = (TextView) findViewById(R.id.start_time);
        final TextView end_time = (TextView) findViewById(R.id.end_time);
        dayPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                event.setDay(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        startPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                        .setForced24hFormat()
                        .setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                                event.setStartTime(hourOfDay);
                                start_time.setText(hourOfDay + ":" + minute);
                            }
                        });
                rtpd.show(getSupportFragmentManager(), "timepicker");
            }
        });
        endPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                        .setForced24hFormat()
                        .setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                                event.setEndTime(hourOfDay);
                                end_time.setText(hourOfDay + ":" + minute);
                            }
                        });
                rtpd.show(getSupportFragmentManager(), "timepicker");
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event.setName(name.getText().toString());
                SharedPreferences sharedPreferences = getSharedPreferences("data", 0);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    String eventJson = sharedPreferences.getString("events", "[]");
                    ArrayList<Event> events = mapper.readValue(eventJson, new TypeReference<ArrayList<Event>>(){});
                    events.add(event);
                    String newJson = mapper.writeValueAsString(events);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("events", newJson);
                    editor.apply();
                    setResult(0);
                    finish();
                } catch (IOException e) {
                    Toast.makeText(AddNewEventActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
