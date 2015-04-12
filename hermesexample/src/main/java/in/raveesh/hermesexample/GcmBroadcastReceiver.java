package in.raveesh.hermesexample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import in.raveesh.hermes.backend.registration.Registration;
import in.raveesh.hermes.receivers.HermesBroadcastReceiver;

public class GcmBroadcastReceiver extends HermesBroadcastReceiver {
    public GcmBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i("HermesExample", "Inside GcmBroadcastReceiver");
        if (gotRegistrationId) {
            /**
             * Device has just been registered.
             */
            Log.d("HermesExample", "Device Registered, ID is " + registrationID);
            Registration.Builder builder = new Registration.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://axiomatic-set-91221.appspot.com/_ah/api/");

            Registration regService = builder.build();
            try {
                regService.register(registrationID);
                Toast.makeText(context, "Registered on Server", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!error) {
            // Explicitly specify that GcmIntentService will handle the intent.
            ComponentName comp = new ComponentName(context.getPackageName(),
                    GcmIntentService.class.getName());
            // Start the service, keeping the device awake while it is launching.
            startWakefulService(context, (intent.setComponent(comp)));
            setResultCode(Activity.RESULT_OK);
        }
        else{
            Log.i("HermesExample", "Error");
        }
    }
}
