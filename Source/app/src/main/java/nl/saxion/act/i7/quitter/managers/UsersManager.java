package nl.saxion.act.i7.quitter.managers;

import android.util.Log;

import org.json.JSONObject;

import java.util.HashMap;

import nl.saxion.act.i7.quitter.models.UserModel;

public class UsersManager {
    private UserModel currentUser;

    private HashMap<Long, UserModel> cachedUsers = new HashMap<>();

    public UsersManager() {
    }

    public void add(UserModel user) {
        this.cachedUsers.put(user.getId(), user);
    }

    public UserModel add(JSONObject jsonObject) {
        UserModel user = new UserModel(jsonObject);
        this.cachedUsers.put(user.getId(), user);

        return user;
    }

    public UserModel get(JSONObject jsonObject) {
        try {
            return this.cachedUsers.get(jsonObject.getLong("id"));
        } catch (Exception ex) {
            Log.e(this.getClass().getName(), ex.getLocalizedMessage(), ex);
        }

        return null;
    }

    public void remove(long id) {
        this.cachedUsers.remove(id);
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }
}