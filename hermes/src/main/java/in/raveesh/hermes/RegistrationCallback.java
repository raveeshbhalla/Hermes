package in.raveesh.hermes;

/**
 * Created by Raveesh on 01/04/15.
 */
public interface RegistrationCallback {
    public void registrationComplete(String id);
    public void registrationProcessStarted();
    public void registrationFailed(String msg);
    public void setExponentialBackoff(int time);
    public void backoffComplete(int time);
}
