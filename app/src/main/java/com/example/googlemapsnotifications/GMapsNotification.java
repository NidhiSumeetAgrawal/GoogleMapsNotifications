package com.example.googlemapsnotifications;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;

/*
This class extracts all needed information from received notification data bundle.
 It also gives getters to use the data fields info.
 */
public class GMapsNotification {

    private final String NEXT_DIRECTION = "android.text";
    private final String APP_INFO = "android.appInfo";
    private final String DIRECTION_IMAGE = "android.largeIcon";
    private final String TIME_DISTANCE_TO_REACH_DEST = "android.subText";
    private final String DISTANCE_LEFT_FOR_NEXT_TURN = "android.title" ;
    private Bundle bundle;

    public GMapsNotification(Bundle bundle) {
        this.bundle = bundle;
    }

    public String getTimeToReachDest() {
        if (bundle != null && bundle.get(TIME_DISTANCE_TO_REACH_DEST)!=null) {
            String data = bundle.get(TIME_DISTANCE_TO_REACH_DEST).toString();
            return data.split("·")[0];
        }
        return null;
    }

    public String getAppInfo() {
        if (bundle != null && bundle.get(APP_INFO)!=null) {
            return bundle.get(APP_INFO).toString();
        }
        return null;
    }
    public String getDistToReachDest() {
        if (bundle != null && bundle.get(TIME_DISTANCE_TO_REACH_DEST)!=null) {
            String distanceData = bundle.get(TIME_DISTANCE_TO_REACH_DEST).toString();
            String[] dataArray =distanceData.split("·");
            if(dataArray.length > 1)
            {
            return dataArray[1];
            }
        }
        return null;
    }

    /*
    We receive "Re-routing" and next direction info in this field
     */
    public String getNextDirectionInfo() {
        if (bundle != null && bundle.get(NEXT_DIRECTION)!=null) {
            return bundle.get(NEXT_DIRECTION).toString();
        }
        return null;
    }

    /*
    When navigation starts, we get { "Starting navigation…" } string value in this field.
     */
    public String getDistanceLeftNextTurn() {
        if (bundle != null && bundle.get(DISTANCE_LEFT_FOR_NEXT_TURN)!=null) {
            return bundle.get(DISTANCE_LEFT_FOR_NEXT_TURN).toString();
        }
        return null;
    }

    public Bitmap getDirectionImage(Context context) {
        if (bundle != null && bundle.get(DIRECTION_IMAGE)!=null) {
            Icon directionImageIcon = (Icon) bundle.get(DIRECTION_IMAGE);
            Drawable largeIconBitmap = directionImageIcon.loadDrawable(context);
            return drawableToBitmap(largeIconBitmap);
        }
        return null;
    }
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888
        );
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}
