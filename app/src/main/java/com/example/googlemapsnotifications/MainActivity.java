package com.example.googlemapsnotifications;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


/*
Permissions rerquired :
1)android:permission="android.permission.BIND_NOTIFICATION_LISTENER_SERVICE" -this is already added in manifest
2)mobile notification access permission.
 */
public class MainActivity extends AppCompatActivity implements NotificationNotifier {
ImageView mDirectionalArrow;
TextView mAppInfo;
TextView mDistanceToReachDest;
TextView mTimeToReachDest;
TextView mNextDirection;

TextView mDistanceToNextTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationListener.setBundleReceiver(this);
        mDirectionalArrow = (ImageView)findViewById(R.id.directionImage);
        mDistanceToReachDest = (TextView) findViewById(R.id.distanceToReachDest);
        mTimeToReachDest = (TextView) findViewById(R.id.timeToReachDest);
        mNextDirection = (TextView) findViewById(R.id.nextDirection);
        mAppInfo = (TextView) findViewById(R.id.notificationAppInfo);
        mDistanceToNextTurn = (TextView) findViewById(R.id.distanceToNextTurn);

    }
    @Override
    public void onNotificationReceived(GMapsNotification gMapsNotification, boolean isNotificationReceived) {
        if (isNotificationReceived) {
            mDirectionalArrow.setImageBitmap(gMapsNotification.getDirectionImage(getApplicationContext()));
            mNextDirection.setText("Next direction is: " +gMapsNotification.getNextDirectionInfo());
            mTimeToReachDest.setText("Time to reach destination: " +gMapsNotification.getTimeToReachDest());
            mDistanceToReachDest.setText("Distance to reach destination: " +gMapsNotification.getDistToReachDest());
            mAppInfo.setText("App Info: " +gMapsNotification.getAppInfo());
            mDistanceToNextTurn.setText("Distance to next turn: " +gMapsNotification.getDistanceLeftNextTurn());
        }
    }
}