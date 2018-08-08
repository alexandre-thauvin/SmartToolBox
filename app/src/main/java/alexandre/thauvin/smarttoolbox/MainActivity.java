package alexandre.thauvin.smarttoolbox;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Tasker.class);
        intent.putExtra("service", spinnerService.getSelectedItem().toString());
        intent.putExtra("action", spinnerAction.getSelectedItem().toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, spinnerService.getSelectedItemPosition(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        instance.set(Calendar.HOUR_OF_DAY, hours);
        instance.set(Calendar.MINUTE, minutes);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, instance.getTimeInMillis(),
                pendingIntent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
