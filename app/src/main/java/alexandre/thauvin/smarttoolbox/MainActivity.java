package alexandre.thauvin.smarttoolbox;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerService;
    private Spinner spinnerAction;
    private int hours;
    private int minutes;
    private TimePicker tp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        AlarmManager alarmMgr;
        PendingIntent alarmIntent;

        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, TimeChecker.class);
        intent.putExtra("service", spinnerService.getSelectedItem().toString());
        intent.putExtra("action", spinnerAction.getSelectedItem().toString());
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);

        alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                alarmIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
