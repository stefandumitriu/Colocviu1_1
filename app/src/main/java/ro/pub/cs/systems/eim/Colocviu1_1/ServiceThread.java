package ro.pub.cs.systems.eim.Colocviu1_1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ServiceThread extends Thread {

    private Context context = null;
    private String commands;

    public ServiceThread(Context context, String commands) {
        this.context = context;
        this.commands = commands;
    }

    @Override
    public void run() {
        Log.d("[ServiceThread]", "ServiceThread has started");
        sleep();
        sendMessage();
        Log.d("[ServiceThread]", "ServiceThread has stopped");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("broadcastAction");
        intent.putExtra("broadcast",
                new Date(System.currentTimeMillis()) + " " + commands);
        context.sendBroadcast(intent);
        Log.d("[ServiceThread]", "Broadcast sent: "+ intent.getStringExtra("broadcast"));
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        this.stop();
    }
}