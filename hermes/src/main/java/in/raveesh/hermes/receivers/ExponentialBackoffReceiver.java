package in.raveesh.hermes.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import in.raveesh.hermes.Hermes;

public class ExponentialBackoffReceiver extends BroadcastReceiver {
    private static String ACTION = "in.raveesh.hermes.receivers.EXPONENTIAL_BACKOFF";
    private static String EXTRA_GAP = "GAP";
    private static String EXTRA_SENDER_ID = "SENDER_ID";

    public static void attemptRegistration(Context context, int time, String sender_id){
        Intent intent = new Intent(context, ExponentialBackoffReceiver.class);
        intent.putExtra(EXTRA_GAP, time);
        intent.putExtra(EXTRA_SENDER_ID, sender_id);
        intent.setAction(ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, time, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time, pendingIntent);
        Log.d(Hermes.TAG, "Exponential Backoff Set for " + time);
        if (Hermes.CALLBACK != null){
            Hermes.CALLBACK.setExponentialBackoff(time);
        }
    }
    public ExponentialBackoffReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ACTION)){
            int backedOff = intent.getIntExtra(EXTRA_GAP, Hermes.DEFAULT_BACKOFF);
            if (Hermes.CALLBACK != null){
                Hermes.CALLBACK.backoffComplete(backedOff);
            }
            Log.d(Hermes.TAG, "Exponential backoff complete after "+ backedOff);
            Hermes.register(context, intent.getStringExtra(EXTRA_SENDER_ID));
            Hermes.setDelay(backedOff*2);
        }
    }
}
