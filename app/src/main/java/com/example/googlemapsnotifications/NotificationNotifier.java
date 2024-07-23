package com.example.googlemapsnotifications;

public interface NotificationNotifier {
    public void onNotificationReceived(GMapsNotification gMapsNotification, boolean isNotificationReceived);
}
