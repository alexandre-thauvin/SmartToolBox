package alexandre.thauvin.smarttoolbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //Intent intent = new Intent(this, ShutDownService.class);
        //startService(intent);
        //bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //bluetoothAdapter.disable();
    }
}
