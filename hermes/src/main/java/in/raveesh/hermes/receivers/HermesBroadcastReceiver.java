package in.raveesh.hermes.receivers;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * This {@code HermesBroadcastReceiver} takes care of creating and managing a
 * partial wake lock for your app. It passes off the work of processing the GCM
 * message to {@code HermesIntentService}.
 *
 * The wakelock is release when {@code HermesIntentService} calls
 * {@code completeWakefulIntent()}
 *
 */
public class HermesBroadcastReceiver extends WakefulBroadcastReceiver {

    private Class intentService;
    public HermesBroadcastReceiver() {
    }

    /**
     * This sets the class which should be called when the GCM message is
     * received.
     * This method should be called when creating the BroadcastReceiver, preferably in the
     * constructor. When {@code onReceive} is called, the @param intentService class would
     * be called to process the message
     * @param intentService the intent service class which should be called to process the gcm
     *                      message. Should extend {@code HermesIntentService}
     */
    public void setIntentService(Class intentService) {
        this.intentService = intentService;
        Log.d("Hermes", "Setting intentService class to - " + intentService);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intentService == null ) throw new UnsupportedOperationException(
                "Please use setIntentService in the " +
                        "constructor of your broadcastReceiver class.");

        ComponentName comp = new ComponentName(
                context.getPackageName(),
                intentService.getName());

        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
