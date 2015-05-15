package in.raveesh.hermesexample;

import android.content.Intent;

import in.raveesh.hermes.Util;
import in.raveesh.hermes.receivers.HermesIntentService;

/**
 * Created by sahil on 15/5/15.
 */
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
        Util.log("Message Received in overridden method");
    }

    @Override
    public void messageDeleted() {}

    @Override
    public void messageSendEvent() {}

    @Override
    public void messageSendError() {}
}
