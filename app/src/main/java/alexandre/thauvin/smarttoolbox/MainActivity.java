package alexandre.thauvin.smarttoolbox;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {


    private int res = 0;
    private TimePicker tp;
    private Spinner spinnerService;
    private Spinner spinnerAction;
    private Intent serviceIntent;


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
                    res = hourOfDay + minute;

                }
            };

    public void showTimePickerDialog(View v) {
        if (tp.getVisibility() == View.GONE)
            tp.setVisibility(View.VISIBLE);
        else
            tp.setVisibility(View.GONE);

    }

    public void checkActions(View view) {
        serviceIntent = new Intent(this, TimeChecker.class);
        serviceIntent.putExtra("time", res);
        serviceIntent.putExtra("service", spinnerService.getSelectedItem().toString());
        serviceIntent.putExtra("action", spinnerAction.getSelectedItem().toString());
        startService(serviceIntent);

    }

    @Override
    protected void onDestroy() {
        stopService(serviceIntent);
        super.onDestroy();

    }
}
