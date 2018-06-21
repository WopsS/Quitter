package nl.saxion.act.i7.quitter.tasks.twitter;

import android.util.Log;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import org.json.JSONObject;

import nl.saxion.act.i7.quitter.managers.AuthorizationManager;
import nl.saxion.act.i7.quitter.tasks.AsyncTask;
import nl.saxion.act.i7.quitter.tasks.TaskResponse;

public class TwitterVerifyCredentialsTask extends AsyncTask<Void, Void, Boolean> {
    public TwitterVerifyCredentialsTask(TaskResponse<Boolean> delegate) {
        super(delegate);
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json");
        AuthorizationManager.getInstance().signRequest(oAuthRequest);

        Response response = AuthorizationManager.getInstance().execute(oAuthRequest);
        if (response.isSuccessful()) {
            try {
                AuthorizationManager.getInstance().handleUserJson(new JSONObject(response.getBody()));
                return true;
            } catch (Exception ex) {
                Log.e(this.getClass().getSimpleName(), ex.getLocalizedMessage(), ex);
            }
        }

        return false;
    }
}