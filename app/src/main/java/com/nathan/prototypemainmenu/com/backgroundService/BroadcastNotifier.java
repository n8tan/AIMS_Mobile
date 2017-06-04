package com.nathan.prototypemainmenu.com.backgroundService;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Nathan on 28/05/2017.
 */

public class BroadcastNotifier {

    private LocalBroadcastManager broadcastManager;

    public BroadcastNotifier(Context context) {
        broadcastManager = LocalBroadcastManager.getInstance(context);
    }

    public void broadcastIntentWithState(int status) {
        Intent localIntent = new Intent();

        //Sets Intents "intended" action
        localIntent.setAction(ServiceConstants.BROADCAST_ACTION);

        localIntent.putExtra(ServiceConstants.DATA_STATUS, status);
        localIntent.addCategory(Intent.CATEGORY_DEFAULT);

        //Broadcast
        broadcastManager.sendBroadcast(localIntent);
    }
}
