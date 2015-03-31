package in.raveesh.hermesexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import in.raveesh.hermes.GcmRegistrationException;
import in.raveesh.hermes.Hermes;
import in.raveesh.hermes.RegistrationCallback;


public class MainActivity extends ActionBarActivity implements RegistrationCallback{

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
        super.onResume();
        Hermes.register(this, "893452105076");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Hermes.pause(this);
    }

    @Override
    public void registrationComplete(String id) {
        textView.setText(id);
    }

    @Override
    public void registrationProcessStarted() {
        textView.setText("Started Registration Process");
    }

    @Override
    public void registrationFailed(String msg) {
        textView.setText("Registration failed: "+msg);
    }
}
