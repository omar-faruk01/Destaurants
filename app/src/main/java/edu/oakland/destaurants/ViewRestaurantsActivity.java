package edu.oakland.destaurants;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.DataStoreItemChange;
import com.amplifyframework.datastore.generated.model.Restaurant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ViewRestaurantsActivity extends AppCompatActivity {

    RestaurantsAdapter restaurantsAdapter;
    ListView restaurantsListView;
    EditText searchET;

    ArrayList<Restaurant> restaurantsList = new ArrayList<>();
    boolean[] selectedRestrictions;
    String[] restrictionChoiceArr = {"Halal","Pescatarian", "Vegan", "Vegetarian" };
    ArrayList<Integer> restrictionList = new ArrayList<Integer>();
    ArrayList<String> restrictionList2 = new ArrayList<>();


    TextView restrictionFilter, ratingFilter;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurants);

        // Set custom restaurants adapter
        restaurantsListView = findViewById(R.id.restaurantsList);
        restaurantsAdapter = new RestaurantsAdapter(getApplicationContext(),R.id.restaurantsList, restaurantsList);
        restaurantsListView.setAdapter(restaurantsAdapter);

        restrictionFilter = findViewById(R.id.restrictionFilterTV);
        searchBtn = findViewById(R.id.searchBtn);
        searchET = findViewById(R.id.searchET);

        selectedRestrictions = new boolean[restrictionChoiceArr.length];

        restrictionFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        ViewRestaurantsActivity.this

                );
                // title
                builder.setTitle("Selected Dietary Restriction");
                // set dialog non cancelable
                builder.setCancelable(false);
                builder.setMultiChoiceItems(restrictionChoiceArr, selectedRestrictions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        //check condition
                        if (b){
                            // box selected then add position to list
                            restrictionList.add(i);
                        } else {
                            restrictionList.remove(i);
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j=0; j < restrictionList.size(); j++) {
                            //concat array value
                            stringBuilder.append(restrictionChoiceArr[restrictionList.get(j)]);
                            restrictionList2.add(restrictionChoiceArr[restrictionList.get(j)]);
                            Toast.makeText(getApplicationContext(),restrictionChoiceArr[restrictionList.get(j)], Toast.LENGTH_SHORT ).show();

                            //check condition
                            if(j != restrictionList.size()-1){
                                stringBuilder.append(",");
                            }
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss
                        dialogInterface.dismiss();
                    }
                })
                .setNeutralButton("Clear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j=0; j<selectedRestrictions.length; j++) {
                            //Remove all selection
                            selectedRestrictions[j] = false;
                            //Clear day List
                            restrictionList.clear();

                            restrictionList2.clear();
                        }
                    }
                });
                builder.show();
            }
        });

        // Get restaurants
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRestaurantsContains();
            }
        });

        getRestaurants();

        //Listen to new restaurants
        Amplify.DataStore.observe(
                Restaurant.class,
                cancelable -> Log.i("Amplify-observer", "Observation began."),
                this::onNewRestaurant,
                failure -> Log.e("DestaurantsAmplifyApp", "Observation Failed: ", failure),
                () -> Log.i("Amplify-observer", "Observation complete.")

        );
    }



    private void onNewRestaurant(DataStoreItemChange<Restaurant> restaurantChanged) {
        if (restaurantChanged.type().equals(DataStoreItemChange.Type.CREATE)) {
            Restaurant restaurant = restaurantChanged.item();
            restaurantsList.add(restaurant);
            runOnUiThread(() -> restaurantsAdapter.notifyDataSetChanged());
        }
    }

    private void getRestaurantsContains() {
        Amplify.DataStore.query(
                Restaurant.class,
                Where.matches(Restaurant.RESTRICTIONS.contains(restrictionList2.get(0)).and( Restaurant.RESTRICTIONS.contains(restrictionList2.get(1)))),
                (restaurants) -> runOnUiThread(() -> {
                    restaurantsList.clear();
                    while (restaurants.hasNext()){
                        Restaurant restaurant = restaurants.next();
                        restaurantsList.add(restaurant);
                        restaurantsAdapter.notifyDataSetChanged();
                    }
                }),
                Throwable::printStackTrace
        );
    }

    private void getRestaurants() {
        Amplify.DataStore.query(
                Restaurant.class,
                Where.sorted(Restaurant.CITY.descending()),
                (restaurants) -> runOnUiThread(() -> {
                    restaurantsList.clear();
                    while (restaurants.hasNext()){
                        Restaurant restaurant = restaurants.next();
                        restaurantsList.add(restaurant);
                        restaurantsAdapter.notifyDataSetChanged();
                    }
                }),
                Throwable::printStackTrace
        );
    }
}