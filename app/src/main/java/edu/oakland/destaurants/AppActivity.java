package edu.oakland.destaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amazonaws.mobile.client.AWSMobileClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AppActivity extends AppCompatActivity {

    Intent accountIntent, viewRestaurantsIntent;
    Button accountActivityBtn, viewRestaurantsBtn;
    private static final String TAG = "AmplifyApp";
    private AWSMobileClient mobileClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        accountIntent = new Intent(AppActivity.this, AccountActivity.class);
        accountActivityBtn = findViewById(R.id.accountActivityBtn);

        viewRestaurantsIntent = new Intent(AppActivity.this, ViewRestaurantsActivity.class);
        viewRestaurantsBtn = findViewById(R.id.listItemsActivityBtn);


        accountActivityBtn.setOnClickListener(view -> startActivity(accountIntent));
        viewRestaurantsBtn.setOnClickListener(view -> startActivity(viewRestaurantsIntent));


    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "MainActivity, onStart()");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        Log.i(TAG, "MainActivity, onStop()");
        super.onStop();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(GreenRobotEvents.MobileClientEvent event) {
        Log.i(TAG, "AuthenticatedUsersActivity, got mobile client event....");
        mobileClient = event.awsMobileClient;
    }
}