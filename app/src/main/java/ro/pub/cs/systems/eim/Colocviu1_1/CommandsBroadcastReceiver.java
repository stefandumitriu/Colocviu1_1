package ro.pub.cs.systems.eim.Colocviu1_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CommandsBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.d("[BroadcastReceiver]", intent.getStringExtra("broadcast"));
    }
}