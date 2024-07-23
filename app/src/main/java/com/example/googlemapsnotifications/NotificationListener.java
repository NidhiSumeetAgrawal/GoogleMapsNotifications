 package com.example.googlemapsnotifications;

import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/*
This class receives the notification and also informs when notification is removed from statusbar.
 */
 public class NotificationListener  extends NotificationListenerService
{
    private static NotificationNotifier notificationBundleReciever;
    private final int NAVIGATION_EXITS = 8;

    public static void setBundleReceiver(NotificationNotifier receiver) {
        notificationBundleReciever = receiver;
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn, RankingMap rankingMap, int reason) {
        super.onNotificationRemoved(sbn, rankingMap, reason);
        if(notificationBundleReciever!=null) {
            if(reason == NAVIGATION_EXITS) {
                notificationBundleReciever.onNotificationReceived(null, false);
            }
        }
    }
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        String packageName = sbn.getPackageName();
        if ("com.google.android.apps.maps".equals(packageName)) {
            Bundle extras = sbn.getNotification().extras;
            GMapsNotification gMapsNotification =new GMapsNotification(extras);
            if(notificationBundleReciever!=null) {
                notificationBundleReciever.onNotificationReceived(gMapsNotification, true);
            }
        }
    }
}
