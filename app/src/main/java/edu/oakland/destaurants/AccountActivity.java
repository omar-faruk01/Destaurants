package edu.oakland.destaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Restaurant;
import com.amplifyframework.datastore.generated.model.Review;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


public class AccountActivity extends AppCompatActivity {

    EditText nameET, addressET, cityET, stateET, zipET, countryET;
    Button submitBtn, signOutBtn;
    TextView usernameTV;
    ArrayList<String> restrictionList = new ArrayList<>();

    private static final String TAG = "AmplifyApp";
    private AWSMobileClient mobileClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        nameET = findViewById(R.id.nameET);
        addressET = findViewById(R.id.addrET);
        cityET = findViewById(R.id.cityET);
        stateET = findViewById(R.id.stateET);
        zipET = findViewById(R.id.zipET);
        countryET = findViewById(R.id.countryET);
        submitBtn = findViewById(R.id.submitBtn);
        usernameTV = findViewById(R.id.usernameTV);
        signOutBtn = findViewById(R.id.signOutBtn);




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

    @Override
    protected void onResume() {
        super.onResume();
        String username = Amplify.Auth.getCurrentUser().getUsername();

        usernameTV.setText("Hello " + username);

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Amplify.Auth.signOut(
                        () -> {
                            Log.i(TAG, "Signed out this device successfully");
                            Intent loginIntent = new Intent(AccountActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                        },
                        error -> Log.i(TAG, error.toString())
                );
            }
        });

        restrictionList.add("Pescatarian");
        restrictionList.add("Halal");


        Restaurant restaurant = Restaurant.builder()
                .name("Bonefish Grill")
                .address("660 W Big Beaver Rd")
                .city("Troy")
                .state("MI")
                .country("USA")
                .category("Desi")
                .zipCode("48084")
                .username(Amplify.Auth.getCurrentUser().getUsername())
                .restrictions(restrictionList)
                .build();

//        Review review = Review.builder()
//                .restaurantId(restaurant.getId())
//                .rating(5.0)
//                .comment("Great food")
//                .build();

        submitBtn.setOnClickListener(view -> {

            Amplify.DataStore.save(
                    restaurant,
                    savedRestaurant -> {
                        Log.i("MyAmplifyApp", "restaurant saved.");
                    },
                    error -> Log.e("MyAmplifyApp",  "Error creating post", error)
            );
        });



    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(GreenRobotEvents.MobileClientEvent event) {
        Log.i(TAG, "AuthenticatedUsersActivity, got mobile client event....");
        mobileClient = event.awsMobileClient;
    }

}