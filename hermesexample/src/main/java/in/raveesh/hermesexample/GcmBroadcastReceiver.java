package in.raveesh.hermesexample;

import android.content.Context;
import android.content.Intent;

import in.raveesh.hermes.receivers.HermesBroadcastReceiver;

public class GcmBroadcastReceiver extends HermesBroadcastReceiver {
    public GcmBroadcastReceiver() {
        // IMPORTANT
        // Call this with the intentservice class you want to start
        // Refer javadoc for more info.
        setIntentService(GcmIntentService.class);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}
