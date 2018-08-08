package alexandre.thauvin.smarttoolbox;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class TimeChecker extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getStringExtra("action");
        String service = intent.getStringExtra("service");
        switch (service) {
            case "Bluetooth":
                BluetoothAdapter bluetoothAdapter;
                bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (action.equals("enable")) {
                    bluetoothAdapter.enable();
                } else {
                    bluetoothAdapter.disable();
                }
                Toast.makeText(context, action + " bluetooth", Toast.LENGTH_SHORT).show();

                break;
            case "Wifi":
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if (action.equals("enable")) {
                    wifiManager.setWifiEnabled(true);
                } else {
                    wifiManager.setWifiEnabled(false);
                }
                Toast.makeText(context, action + " wifi", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}


