package com.parkpoint.ubexquiz;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class ConnectionClass {
    public static boolean isInternetConnection(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService (Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo (ConnectivityManager.TYPE_MOBILE).getState () == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo (ConnectivityManager.TYPE_WIFI).getState () == NetworkInfo.State.CONNECTED;
    }
}
