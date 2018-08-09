package alexandre.thauvin.smarttoolbox;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerService;
    private Spinner spinnerAction;
    private int hours;
    private int minutes;
    private TimePicker tp;
    private List<String> tasks;
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getApplicationContext().getSharedPreferences("tasks", MODE_PRIVATE);
        if (sharedPreferences.getStringSet("tasks", null) != null) {
            tasks = new ArrayList<>(sharedPreferences.getStringSet("tasks", null));
        }
        spinnerAction = findViewById(R.id.spinner_action);
        spinnerService = findViewById(R.id.spinner_service);

        ArrayAdapter<CharSequence> actionAdapter = ArrayAdapter.createFromResource(this,
                R.array.basic_action, android.R.layout.simple_spinner_item);
        actionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAction.setAdapter(actionAdapter);

        ArrayAdapter<CharSequence> serviceAdapter = ArrayAdapter.createFromResource(this,
                R.array.basic_service, android.R.layout.simple_spinner_item);
        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerService.setAdapter(serviceAdapter);


        tp = findViewById(R.id.timePicker);
        tp.setOnTimeChangedListener(timeChangedListener);
        tp.setVisibility(View.GONE);
        tp.setIs24HourView(true);

    }

    private TimePicker.OnTimeChangedListener timeChangedListener =
            new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    TextView textView = findViewById(R.id.hour_bluetooth);
                    StringBuilder formattedTime = new StringBuilder().append(hourOfDay)
                            .append(":")
                            .append(minute < 10 ? "0" + minute : minute);
                    textView.setText(formattedTime);
                    minutes = minute;
                    hours = hourOfDay;

                }
            };

    public void showTimePickerDialog(View v) {
        if (tp.getVisibility() == View.GONE)
            tp.setVisibility(View.VISIBLE);
        else
            tp.setVisibility(View.GONE);

    }

    public void checkActions(View view) {

        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        String service = spinnerService.getSelectedItem().toString();
        String action = spinnerAction.getSelectedItem().toString();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Tasker.class);
        intent.putExtra("service", service);
        intent.putExtra("action", action);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, spinnerService.getSelectedItemPosition(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        instance.set(Calendar.HOUR_OF_DAY, hours);
        instance.set(Calendar.MINUTE, minutes);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(),
                pendingIntent);
        updateListOfTasks(action + "/" + service);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void updateListOfTasks(String s){
        Set<String> set = new HashSet<>(tasks);
        sharedPreferences.edit().putStringSet("tasks", set).apply();
    }
}
