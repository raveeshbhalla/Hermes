package in.raveesh.hermes.receivers;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import in.raveesh.hermes.Util;

public class HermesBroadcastReceiver extends WakefulBroadcastReceiver {

    private Class intentService;
    public HermesBroadcastReceiver() {
    }

    public void setIntentService(Class intentService) {
        this.intentService = intentService;
        Util.log("Setting intentService class to - "+intentService);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intentService == null ) throw new UnsupportedOperationException(
                "Please use setIntentService in the " +
                        "constructor of your broadcastReceiver class.");

        ComponentName comp = new ComponentName(
                context.getPackageName(),
                intentService.getName());

        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
