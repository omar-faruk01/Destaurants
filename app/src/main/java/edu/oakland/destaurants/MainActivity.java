package edu.oakland.destaurants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.HubEvent;
import com.amplifyframework.hub.HubSubscriber;
import com.amplifyframework.hub.SubscriptionToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    private static final String TAG = "AmplifyApp";
    private AWSMobileClient mobileClient;
    private SubscriptionToken subscriptionToken;



    private HubSubscriber hubSubscriber = new HubSubscriber() {
        @Override
        public void onEvent(@NonNull HubEvent<?> hubEvent) {
            Log.i(TAG, "MainActivity, in hub subscriber, hub event name: " + hubEvent.getName());
            switch (hubEvent.getName()) {
                case "SIGNED_IN":
                    Log.i(TAG, "MainActivity, signed in");

                    Amplify.Auth.fetchAuthSession(onSuccess -> {
                        AuthUser user = Amplify.Auth.getCurrentUser();
                        if (user != null) {
                            Log.i(TAG, "MainActivity, signed in, current user: " + user.getUsername());
                        } else {
                            Log.i(TAG, "MainActivity, signed in, user name is null");
                        }
                    }, onError -> {
                        Log.i(TAG, "MainActivity, signed in, error getting user session: " + onError.toString());
                    });
                    break;
                case "SIGNED_OUT":
                    Log.i(TAG, "MainActivity, signed out");
                    Amplify.DataStore.clear(
                            () -> Log.i("MyAmplifyApp", "DataStore is cleared."),
                            failure -> Log.e("MyAmplifyApp", "Failed to clear DataStore.")
                    );
                    break;
                case "SESSION_EXPIRED":
                    Log.i(TAG, "MainActivity, SESSION_EXPIRED " + hubEvent.toString());
                    break;
                default:
                    Log.w(TAG, "MainActivity, Unhandled Auth Event: " + hubEvent.getName());
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // session token of user
        Amplify.Auth.fetchAuthSession(
                result -> Log.i(TAG, result.toString()),
                error -> Log.e(TAG, error.toString())
        );

        AWSMobileClient mobileClient = (AWSMobileClient) Amplify.Auth.getPlugin("awsCognitoAuthPlugin").getEscapeHatch();
        Log.i(TAG, "in MainActivity, OnCreate() created new mobileClient and sent event");
        EventBus.getDefault().postSticky(new GreenRobotEvents.MobileClientEvent(mobileClient));

    }



   @Override
    protected void onResume() {
       super.onResume();
       Log.i("AmplifyApp", "Main Activity onResume()");
       subscriptionToken = Amplify.Hub.subscribe(HubChannel.AUTH, hubSubscriber);

       AuthUser user = Amplify.Auth.getCurrentUser();

       if (user != null){
           Log.i("AmplifyApp", "Logged in,  onResume()");
           intent = new Intent(getApplicationContext(), AppActivity.class);

       } else {
           Log.i("AmplifyApp", "Not logged in,  onResume()");
           intent = new Intent(getApplicationContext(), LoginActivity.class);
       }

       // Start authz activity
       startActivity(intent);
       finish();

   }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "MainActivity, onDestroy()");
        Amplify.Hub.unsubscribe(subscriptionToken);
        super.onDestroy();
    }
}