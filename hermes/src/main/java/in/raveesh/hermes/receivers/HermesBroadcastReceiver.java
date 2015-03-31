package in.raveesh.hermes.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import in.raveesh.hermes.Hermes;

public class HermesBroadcastReceiver extends BroadcastReceiver {

    public HermesBroadcastReceiver() {
    }

    protected boolean gotRegistrationId = false;
    protected String registrationID;

    @Override
    public void onReceive(Context context, Intent intent) {
        registrationID = intent.getStringExtra("registration_id");
        if (registrationID != null) {
            gotRegistrationId = true;
            if (Hermes.CALLBACK != null) {
                Hermes.CALLBACK.registrationComplete(registrationID);
            }
        } else {
            Log.d(Hermes.TAG, "GCM Message Received");
        }
    }
}
