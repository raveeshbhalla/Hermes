package in.raveesh.hermesexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import in.raveesh.hermes.Hermes;
import in.raveesh.hermes.RegistrationCallback;
import in.raveesh.hermes.Util;


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
        Util.log("onResume");
        super.onResume();
        Hermes.register(this, "893452105076");
    }

    @Override
    protected void onPause(){
        Util.log("onPause");
        super.onPause();
        Hermes.pause(this);
    }

    @Override
    public void registrationComplete(final String id) {
        Util.log("registrationComplete");
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(id);
            }
        });
    }

    @Override
    public void registrationProcessStarted() {
        Util.log("registrationProcessStarted");
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("Started Registration Process");
            }
        });
    }

    @Override
    public void registrationFailed(final String msg) {
        Util.log("registrationFailed");
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("Registration failed: " + msg);
            }
        });
    }

    @Override
    public void setExponentialBackoff(final int time) {
        Util.log("setExponentialBackoff");
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("Set exponential backoff for " + time);
            }
        });
    }

    @Override
    public void backoffComplete(final int time) {
        Util.log("backoffComplete");
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("Exponential backoff complete after " + time);
            }
        });
    }
}
