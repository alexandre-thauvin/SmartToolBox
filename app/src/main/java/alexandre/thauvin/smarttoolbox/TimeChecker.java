package alexandre.thauvin.smarttoolbox;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;
import java.util.Calendar;

public class TimeChecker extends JobService {

    //Looper mServiceLooper;
    //private ServiceHandler mServiceHandler;
    private int time = 0;
    private String action;
    private String service;

    @Override
    public boolean onStartJob(final JobParameters params) {
        int currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + Calendar.getInstance().get(Calendar.MINUTE);
        while (currentTime != (10 + 47)) {
            currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + Calendar.getInstance().get(Calendar.MINUTE);
        }
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);
        return true;
    }

    @Override
    public boolean onStopJob(final JobParameters params) {
        /*if (mDownloadArtworkTask != null) {
            mDownloadArtworkTask.cancel(true);
        }*/
        return true;
    }

   /* private final class ServiceHandler extends Handler {
        private ServiceHandler(Looper looper) {
            super(looper);
        }


        @Override
        public void handleMessage(Message msg) {
            int currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + Calendar.getInstance().get(Calendar.MINUTE);
            while (currentTime != time) {
                currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + Calendar.getInstance().get(Calendar.MINUTE);

            }
            switch (service) {
                case "Bluetooth":
                    BluetoothAdapter bluetoothAdapter;
                    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (action.equals("enable")) {
                        bluetoothAdapter.enable();
                    } else {
                        bluetoothAdapter.disable();
                    }
                    break;
                case "Wifi":
                    WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    if (action.equals("enable")) {
                        wifiManager.setWifiEnabled(true);
                    } else {
                        wifiManager.setWifiEnabled(false);
                    }
                    break;

            }
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {

        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        time = intent.getIntExtra("time", -1);
        action = intent.getStringExtra("action");
        service = intent.getStringExtra("service");

        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
        Intent broadcastIntent = new Intent(".RestartShutDownService");
        sendBroadcast(broadcastIntent);
    }*/
}


