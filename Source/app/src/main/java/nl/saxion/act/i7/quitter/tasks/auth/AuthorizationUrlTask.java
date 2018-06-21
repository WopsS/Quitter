package nl.saxion.act.i7.quitter.tasks.auth;

import android.util.Log;

import nl.saxion.act.i7.quitter.managers.AuthorizationManager;
import nl.saxion.act.i7.quitter.tasks.AsyncTask;
import nl.saxion.act.i7.quitter.tasks.TaskResponse;

public class AuthorizationUrlTask extends AsyncTask<Void, Void, String> {
    public AuthorizationUrlTask(TaskResponse<String> delegate) {
        super(delegate);
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            return AuthorizationManager.getInstance().getAuthorizationUrl();
        } catch (Exception ex) {
            Log.e(this.getClass().getSimpleName(), ex.getLocalizedMessage(), ex);
        }

        return null;
    }
}