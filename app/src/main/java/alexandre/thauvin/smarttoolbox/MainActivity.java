package alexandre.thauvin.smarttoolbox;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {



    private int res = 0;
    private TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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


   /* public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        }
    }*/

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
           Intent intent = new Intent(this, ShutDownService.class);
           Bundle b = new Bundle();
           b.putInt("time", res); //Your id
           intent.putExtras(b); //Put your id to your next Intent
           startService(intent);
       }

   }
}
