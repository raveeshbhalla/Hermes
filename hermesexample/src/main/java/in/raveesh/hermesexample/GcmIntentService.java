package in.raveesh.hermesexample;

import android.content.Intent;
import android.util.Log;

import in.raveesh.hermes.receivers.HermesIntentService;

public class GcmIntentService extends HermesIntentService {

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        super.onHandleIntent(intent);
    }

    @Override
    public void messageReceived() {
        Log.d("HermesExample", "Message Received in overridden method");
    }

    @Override
    public void messageDeleted() {}

    @Override
    public void messageSendEvent() {}

    @Override
    public void messageSendError() {}
}
