package in.raveesh.hermes.receivers;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import in.raveesh.hermes.Hermes;

/**
 * Handles the intent which contains the GCM message.
 * It holds the wake lock started by {@code HermesBroadcastReceiver} while
 * doing the computation and finally releases it by calling
 * {@code completeWakefulIntent()}
 *
 * This class is abstract and methods
 * {@code messageReceived}, {@code messageDeleted}, {@code messageSendError},
 * {@code messageSendEvent} should be overridden in the subclass.
 *
 */
public abstract class HermesIntentService extends IntentService{
    protected boolean gotRegistrationId = false;
    protected boolean error = false;
    protected String registrationID;

    public HermesIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Hermes", "Received intent in HermesIntentService with action - "+intent.getAction());

        if(intent.getAction().equals("com.google.android.c2dm.intent.REGISTRATION")) {
            registrationID = intent.getStringExtra("registration_id");
            if (registrationID != null) {
                gotRegistrationId = true;
                if (Hermes.CALLBACK != null) {
                    Hermes.CALLBACK.registrationComplete(registrationID);
                }
            } else if (intent.getExtras() != null && intent.getExtras().get("error") != null) {
                error = true;
                if (Hermes.CALLBACK != null){
                    Hermes.CALLBACK.registrationFailed(intent.getExtras().get("error").toString());
                }

                // Registration message was received but was error.
                ExponentialBackoffReceiver.attemptRegistration(this, Hermes.getDelay(), Hermes.getSenderId());
            }
        } else if(intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
            String messageType = gcm.getMessageType(intent);

            // Logging and calling the appropriate abstract method.
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                Log.d("Hermes", "Message Received");
                messageReceived();
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                Log.d("Hermes", "Message Deleted");
                messageDeleted();
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                Log.d("Hermes", "Message Send Error");
                messageSendError();
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_EVENT.equals(messageType)) {
                Log.d("Hermes", "Message Send Event");
                messageSendEvent();
            }
        }

        // Release the wake lock provided by the WakefulBroadcastReceiver.
        HermesBroadcastReceiver.completeWakefulIntent(intent);
    }

    public abstract void messageReceived();
    public abstract void messageDeleted();
    public abstract void messageSendEvent();
    public abstract void messageSendError();
}
