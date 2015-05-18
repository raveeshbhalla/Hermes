package in.raveesh.hermesexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import in.raveesh.hermes.Hermes;
import in.raveesh.hermes.RegistrationCallback;


public class MainActivity extends AppCompatActivity implements RegistrationCallback{

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.gcmID);
        Hermes.register(this, "893452105076", this);
    }

    @Override
    protected void onResume(){
        Log.d("HermesExample", "onResume");
        super.onResume();
        Hermes.register(this, "893452105076");
    }

    @Override
    protected void onPause(){
        Log.d("HermesExample", "onPause");
        super.onPause();
        Hermes.pause(this);
    }

    /**
     * Run all the UI changes task on the UI thread as these calls are called from a
     * different thread from asynctask in {@code Hermes}
     */

    @Override
    public void registrationComplete(final String id) {
        Log.d("HermesExample", "registrationComplete");
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(id);
            }
        });
    }

    @Override
    public void registrationProcessStarted() {
        Log.d("HermesExample", "registrationProcessStarted");
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("Started Registration Process");
            }
        });
    }

    @Override
    public void registrationFailed(final String msg) {
        Log.d("HermesExample", "registrationFailed");
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("Registration failed: " + msg);
            }
        });
    }

    @Override
    public void setExponentialBackoff(final int time) {
        Log.d("HermesExample", "setExponentialBackoff");
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("Set exponential backoff for " + time);
            }
        });
    }

    @Override
    public void backoffComplete(final int time) {
        Log.d("HermesExample", "backoffComplete");
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("Exponential backoff complete after " + time);
            }
        });
    }
}
