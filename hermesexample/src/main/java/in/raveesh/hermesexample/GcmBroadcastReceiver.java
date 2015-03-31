package in.raveesh.hermesexample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import in.raveesh.hermes.receivers.HermesBroadcastReceiver;

public class GcmBroadcastReceiver extends HermesBroadcastReceiver {
    public GcmBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (gotRegistrationId){
            /**
             * Device has just been registered.
             */
            Log.d("HermesExample", "Device Registered, ID is "+registrationID);
        }
        else if (!error){
            /**
             * GCM Message received, handle it as per your requirements
             */
        }
    }
}
