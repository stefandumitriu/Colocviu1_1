package ro.pub.cs.systems.eim.Colocviu1_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Colocviu1_1Service extends Service {
    private ServiceThread serviceThread = null;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String commands = intent.getStringExtra("broadcast");
        serviceThread = new ServiceThread(this, commands);
        serviceThread.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        serviceThread.stopThread();
        stopSelf();
        stopForeground(true);
    }
}