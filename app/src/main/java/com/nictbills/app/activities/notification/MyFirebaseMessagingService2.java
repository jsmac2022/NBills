package com.nictbills.app.activities.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nictbills.app.R;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService2 extends FirebaseMessagingService {
 
    private static final String TAG = "FirebaseMessageService";
    Bitmap bitmap_image;
    public static int count = 0;
    Map<String, String> stringMap = new HashMap<>();

    private final String  CHANNEL_ID = "FIREBASE_NOTIFICATION";
    private final String  CHANNEL_NAME = "FIREBASE_PUSH_NOTIFICATION";
    @Override
    public void onNewToken(String s) {
        Log.e("vinod onNewToken", s);
        super.onNewToken(s);
        Log.e("newToken", s);
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fb", s).apply();
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

     /*   stringMap.put(remoteMessage.getData().get("ivrs"),remoteMessage.getData());
*/
        String notify_title  = null, notify_body  = null, ivrs  = null, amount  = null, consumer_name  = null, jsonStrData = null;
        boolean foundflag  = false,  click_action  = true, direct_boot_ok = true;

        Log.e("vinod remoteMessage", remoteMessage.getData().get("json_str_data")+"");

       // String notify_title = null, notify_body = null, notify_data1 = null, notify_data2 = null, data_image_uri = null, click_action = null;

        if (remoteMessage.getData().size() > 0) {

        }
 
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        //    notify_title =  remoteMessage.getNotification().getTitle()+"";
        //    notify_body =  remoteMessage.getNotification().getBody()+"";
        }




        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            notify_title = remoteMessage.getData().get("title") + "";
            notify_body = remoteMessage.getData().get("body") + "";
            ivrs = remoteMessage.getData().get("ivrs") + "";
            amount = remoteMessage.getData().get("amount") + "";
            consumer_name = remoteMessage.getData().get("consumer_name") + "";
            foundflag = Boolean.parseBoolean(remoteMessage.getData().get("foundflag"));
            jsonStrData = remoteMessage.getData().get("json_str_data") + "";
            click_action = Boolean.parseBoolean(remoteMessage.getData().get("click_action"));
            direct_boot_ok = Boolean.parseBoolean(remoteMessage.getData().get("direct_boot_ok"));

            Log.e("notify_title", notify_title+"");
            Log.e("notify_body", notify_body+"");
            Log.e("ivrs", ivrs+"");
            Log.e("amount", amount+"");
            Log.e("consumer_name", consumer_name+"");
            Log.e("foundflag", foundflag+"");
            Log.e("jsonStrData", jsonStrData+"");
            Log.e("click_action", click_action+"");
            Log.e("direct_boot_ok", direct_boot_ok+"");

            sendNotification(notify_title, notify_body, ivrs, amount, consumer_name, foundflag, jsonStrData);

        }



        //To get a Bitmap image from the URL received
        //bitmap_image = getBitmapfromUrl(data_image_uri);

       /* if(click_action) {
            sendNotification(notify_title, notify_body, ivrs, amount, consumer_name, foundflag, jsonStrData);
        }*/
    }
 
 
    /**
     * Create and show a simple notification containing the received FCM message.
     */

    private void sendNotification(String notify_title, String notify_body, String ivrs, String amount, String consumer_name, Boolean foundflag, String jsonStrData) {
        Intent intent = new Intent(this, NotificationDisplayActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Log.e("(notify_title)", notify_title);
        Log.e("(notify_body)", notify_body);
        Log.e("(ivrs)", ivrs);
        Log.e("(amount)", amount);
        Log.e("(consumer_name)", consumer_name);
        Log.e("(foundflag)", foundflag+"");
        Log.e("(jsonStrData)", jsonStrData);

        intent.putExtra("notify_title", notify_title);
        intent.putExtra("notify_body", notify_body);
        intent.putExtra("ivrs", ivrs);
        intent.putExtra("amount", amount);
        intent.putExtra("consumer_name", consumer_name);
        intent.putExtra("foundflag", foundflag);
        intent.putExtra("jsonStrData", jsonStrData);

       // intent.putExtra("AnotherActivity", TrueOrFalse);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, new Random().nextInt() /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //For Android Version Orio and greater than orio.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription(notify_body);
            mChannel.enableLights(true);
            //mChannel.setLightColor(Color.RED);
            mChannel.setLightColor(Color.parseColor("#FF7833"));
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{1000, 200, 1000, 100, 1000, 400, 3000, 200, 1000});

            mNotifyManager.createNotificationChannel(mChannel);
        }
//For Android Version lower than oreo.

     //   RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification);
       /* notificationLayout.setImageViewResource(R.id.app_logo, R.mipmap.ic_launcher);
        notificationLayout.setTextViewText(R.id.title, notify_title);
        notificationLayout.setTextViewText(R.id.message, "This is a custom layout");*/

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        mBuilder.setContentTitle(notify_title)
                .setContentText(notify_body)
                .setSmallIcon(R.drawable.nict_only_logo)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setColor(Color.parseColor("#FF7833"))
                .setContentIntent(pendingIntent)
                .setChannelId(CHANNEL_ID)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notify_title))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notify_body))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        //.setContent(getCustomDesign(notify_title, notify_body));;

        mNotifyManager.notify(count, mBuilder.build());
        count++;
    }

        /*NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(data_image_uri)*//*Notification icon image*//*
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(notify_title)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(data_image_uri))*//*Notification with Image*//*
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
 
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
 
        notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build());
    }*/
 
    /*
    *To get a Bitmap image from the URL received
    * */
    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
 
        }
    }

   /* private void RunNotification() {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(getApplicationContext(), "notify_001");

        contentView = new RemoteViews(getPackageName(), R.layout.my_notification_layout);
        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        Intent switchIntent = new Intent(this, BackgroundService.switchButtonListener.class);
        PendingIntent pendingSwitchIntent = PendingIntent.getBroadcast(this, 1020, switchIntent, 0);
        contentView.setOnClickPendingIntent(R.id.flashButton, pendingSwitchIntent);

        mBuilder.setSmallIcon(R.mipmap.newicon);
        mBuilder.setAutoCancel(false);
        mBuilder.setOngoing(true);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);
        mBuilder.setOnlyAlertOnce(true);
        mBuilder.build().flags = Notification.FLAG_NO_CLEAR | Notification.PRIORITY_HIGH;
        mBuilder.setContent(contentView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "channel name", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        notification = mBuilder.build();
        notificationManager.notify(NotificationID, notification);
    }
*/

}