
package com.triton.johnson_tap_app.fcm;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.triton.johnson_tap_app.R;
import com.triton.johnson_tap_app.session.SessionManager;

/**
 * NOTE: There can only be one service in each app that receives FCM messages. If multiple
 * are declared in the Manifest then the first one will be chosen.
 *
 * In order to make this Java sample functional, you must remove the following from the Kotlin messaging
 * service in the AndroidManifest.xml:
 *
 * <intent-filter>
 *   <action android:name="com.google.firebase.MESSAGING_EVENT" />
 * </intent-filter>
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMessagingService";

    private String usertype;
    private String appintments;
    private String orders;
    Intent intent;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Log.w(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.w(TAG, "Message data payload: " + remoteMessage.getData());

            usertype = remoteMessage.getData().get("usertype");
            appintments = remoteMessage.getData().get("appintments");
            orders = remoteMessage.getData().get("orders");

           Log.w(TAG,"usertype : "+usertype);
           Log.w(TAG,"appintments : "+appintments);
           Log.w(TAG,"orders : "+orders);

          /*  if (*//* Check if data needs to be processed by long running job *//* true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }*/


        }

        SessionManager sessionManager = new SessionManager(this);
        boolean isloggedin = sessionManager.isLoggedIn();
        if(isloggedin){
            // Check if message contains a notification payload.
            if (remoteMessage.getNotification() != null) {
                String title = remoteMessage.getNotification().getTitle(); //get title
                String message = remoteMessage.getNotification().getBody();
                sendNotification(title,message);
                Log.w(TAG, "Message Notification Body: " + remoteMessage.getNotification().getTitle()+" "+remoteMessage.getNotification().getBody());
            }
        }



        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]


    // [START on_new_token]

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @SuppressLint("LongLogTag")
    @Override
    public void onNewToken(String token) {
        Log.w(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }
    // [END on_new_token]



    /**
     * Handle time allotted to BroadcastReceivers.
     */
    @SuppressLint("LongLogTag")
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param title
     * @param messageBody FCM message body received.
     */
    private void sendNotification1(String title, String messageBody) {
        SessionManager session = new SessionManager(getApplicationContext());
       // HashMap<String, String> user = session.getProfileDetails();
       // String type = user.get(SessionManager.KEY_TYPE);
       // assert type != null;
        /*if(type.equalsIgnoreCase("0")){
            Intent intent = new Intent(this, PatientDashboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 */
        /* Request code *//*, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            String channelId = getString(R.string.default_notification_channel_id);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle(title)
                            .setContentText(messageBody)
                            .setAutoCancel(true)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            // Since android Oreo notification channel is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT);
                assert notificationManager != null;
                notificationManager.createNotificationChannel(channel);
            }

            assert notificationManager != null;
            notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build());
        }
        else{
            Intent intent = new Intent(this, DoctorDashboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 *//* Request code *//*, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            String channelId = getString(R.string.default_notification_channel_id);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle(title)
                            .setContentText(messageBody)
                            .setAutoCancel(true)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            // Since android Oreo notification channel is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT);
                assert notificationManager != null;
                notificationManager.createNotificationChannel(channel);
            }

            assert notificationManager != null;
            notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build());

        }*/



    }


    @SuppressLint("LongLogTag")
    private void sendNotification(String title, String messageBody) {

       /* if(usertype != null){
            if(usertype.equalsIgnoreCase("1")){
                if(appintments != null && !appintments.isEmpty()){
                    intent = new Intent(this, PetMyappointmentsActivity.class);
                    intent.putExtra("appintments",appintments);
                    Log.w(TAG,"usertype 1 appintments : "+appintments);
                }else if(orders != null && !orders.isEmpty()){
                    intent = new Intent(this, PetMyOrdrersNewActivity.class);
                    intent.putExtra("orders",orders);
                }else{
                    intent = new Intent(this, PetLoverDashboardActivity.class);
                }


            }
            if(usertype.equalsIgnoreCase("2")){
                if(appintments != null && !appintments.isEmpty()){
                    intent = new Intent(this, ServiceProviderDashboardActivity.class);
                    intent.putExtra("appintments",appintments);
                }else if(orders != null && !orders.isEmpty()){
                    intent = new Intent(this, SPMyOrdrersActivity.class);
                    intent.putExtra("orders",orders);
                }else{
                    intent = new Intent(this, ServiceProviderDashboardActivity.class);
                }


            }
            if(usertype.equalsIgnoreCase("3")){
                if(orders != null && !orders.isEmpty()){
                    intent = new Intent(this, VendorDashboardActivity.class);
                    intent.putExtra("orders",orders);
                }else {
                    intent = new Intent(this, VendorDashboardActivity.class);
                }


            }
            if(usertype.equalsIgnoreCase("4")){
                if(appintments != null && !appintments.isEmpty()){
                    intent = new Intent(this, DoctorDashboardActivity.class);
                    intent.putExtra("appintments",appintments);
                }else if(orders != null && !orders.isEmpty()){
                    intent = new Intent(this, DoctorMyOrdrersActivity.class);
                    intent.putExtra("orders",orders);
                }else{
                    intent = new Intent(this, DoctorDashboardActivity.class);
                }


            }


        }*/



        if (intent != null) {
            for (String key : intent.getExtras().keySet()) {
                Log.w(TAG, "key : " + key);

            }
            String appintments = intent.getExtras().getString("appintments");
            Log.w(TAG, "key appintments : " + appintments);

        }

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)// Set the intent that will fire when the user taps the notification
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, mBuilder.build());

        createNotificationChannel();

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}