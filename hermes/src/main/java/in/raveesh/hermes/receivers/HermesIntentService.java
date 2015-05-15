package in.raveesh.hermes.receivers;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import in.raveesh.hermes.Hermes;
import in.raveesh.hermes.Util;

/**
 * Created by sahil on 15/5/15.
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
        Util.log("HermesIntentService intent - " + intent);

        if(intent.getAction().equals("com.google.android.c2dm.intent.REGISTRATION")) {
            Util.log("HermesIntentService action - REGISTRATION");

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

                ExponentialBackoffReceiver.attemptRegistration(this, Hermes.getDelay(), Hermes.getSenderId());
            }
        } else if(intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
            Util.log("HermesIntentService action - RECEIVE");

            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
            String messageType = gcm.getMessageType(intent);
            Util.log("HermesIntentService messageType - " + messageType);

            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                Util.log("Message Received");
                messageReceived();
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                Util.log("Message Deleted");
                messageDeleted();
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                Util.log("Message Send Error");
                messageSendError();
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_EVENT.equals(messageType)) {
                Util.log("Message Send Event");
                messageSendEvent();
            }
        }
    }

    public abstract void messageReceived();
    public abstract void messageDeleted();
    public abstract void messageSendEvent();
    public abstract void messageSendError();
}
