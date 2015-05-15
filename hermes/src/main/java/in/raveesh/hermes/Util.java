package in.raveesh.hermes;

import android.annotation.SuppressLint;
import android.util.Log;

/**
 * Created by sahil on 15/5/15.
 */
public class Util {
    public static final String LOG_TAG = "in.raveesh.hermesexample";

    @SuppressLint("LongLogTag")
    public static void log(String message) {
        Log.d(LOG_TAG, message);
    }
}
