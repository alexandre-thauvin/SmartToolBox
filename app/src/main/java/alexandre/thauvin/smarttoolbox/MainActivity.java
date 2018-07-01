package alexandre.thauvin.smarttoolbox;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.support.v4.app.DialogFragment;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity {



    private int res = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Intent intent = new Intent(this, ShutDownService.class);
        startService(intent);*/


    }

    public void toTest(View view)
    {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    public static class TimePickerFragment extends DialogFragment
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
            TextView textView = getActivity().findViewById(R.id.hour_bluetooth);
            StringBuilder formattedTime = new StringBuilder().append(hourOfDay)
                    .append(":")
                    .append(minute < 10 ? "0" + minute : minute);
            textView.setText(formattedTime);

        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

   public void checkActions(View view)
   {

   }

    public void setRes(int res) {
        this.res = res;
    }
}
