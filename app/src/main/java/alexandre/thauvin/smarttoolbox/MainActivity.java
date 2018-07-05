package alexandre.thauvin.smarttoolbox;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {



    private int res = 0;
    private TimePicker tp;
    private Spinner spinner;
    private  Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner_bluetooth);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.basic_action, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        tp = findViewById(R.id.timePicker);
        tp.setOnTimeChangedListener(timeChangedListener);
        tp.setVisibility(View.GONE);
        tp.setIs24HourView(true);
    }

    private TimePicker.OnTimeChangedListener timeChangedListener =
            new TimePicker.OnTimeChangedListener(){
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

   public void checkActions(View view)
   {
       CheckBox cb = findViewById(R.id.checkbox_bluetooth);


       if (cb.isChecked()) {
           serviceIntent = new Intent(this, ShutDownService.class);
           Bundle b = new Bundle();
           b.putInt("time", res); //Your id
           b.putString("mode",    spinner.getSelectedItem().toString());
           serviceIntent.putExtras(b); //Put your id to your next Intent
           startService(serviceIntent);
       }

   }

    @Override
    protected void onDestroy() {
        stopService(serviceIntent);
        super.onDestroy();

    }
}
