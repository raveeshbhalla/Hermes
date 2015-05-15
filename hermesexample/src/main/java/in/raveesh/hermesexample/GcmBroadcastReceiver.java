package in.raveesh.hermesexample;

import android.content.Context;
import android.content.Intent;

import in.raveesh.hermes.receivers.HermesBroadcastReceiver;

public class GcmBroadcastReceiver extends HermesBroadcastReceiver {
    public GcmBroadcastReceiver() {
        setIntentService(GcmIntentService.class);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}
