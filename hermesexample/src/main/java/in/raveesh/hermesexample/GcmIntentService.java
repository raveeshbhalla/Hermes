package in.raveesh.hermesexample;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Raveesh on 12/04/15.
 */
public class GcmIntentService extends IntentService {

    public GcmIntentService(String str){
        super(str);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        if (extras != null && !extras.isEmpty()) {  // has effect of unparcelling Bundle
            // Since we're not using two way messaging, this is all we really to check for
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                Logger.getLogger("GCM_RECEIVED").log(Level.INFO, extras.toString());
                String title = "Notification received";
                String message = extras.getString("message");

                Log.i("HermesExample", "Message received: " + extras.getString("message"));
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                builder.setContentTitle(title);
                builder.setContentText(message);
                managerCompat.notify(1, builder.build());
            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}
