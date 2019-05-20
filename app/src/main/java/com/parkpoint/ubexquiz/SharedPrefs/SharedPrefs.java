package com.parkpoint.ubexquiz.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefs {
    private SharedPreferences prefs;

    public SharedPrefs(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences (context);
    }

    public void setToken(String location) {
        prefs.edit ().putString ("token", location).apply ();
    }

    public String getToken() {
        return prefs.getString ("token", "");
    }


}
