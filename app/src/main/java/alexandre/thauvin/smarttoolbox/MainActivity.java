package alexandre.thauvin.smarttoolbox;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, ShutDownService.class);
        startService(intent);
        //bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //bluetoothAdapter.disable();
    }
}
