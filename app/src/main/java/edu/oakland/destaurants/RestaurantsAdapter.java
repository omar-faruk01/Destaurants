package edu.oakland.destaurants;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amplifyframework.datastore.generated.model.Restaurant;

import java.util.List;

public class RestaurantsAdapter extends ArrayAdapter<Restaurant> {

    public RestaurantsAdapter(@NonNull Context context, int resource, @NonNull List<Restaurant> objects) {
        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View rowView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        Restaurant restaurant = getItem(position);

        if(rowView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = layoutInflater.inflate(R.layout.list_item_restaurant, parent, false);
            viewHolder.name = rowView.findViewById(R.id.name);
            viewHolder.restriction = rowView.findViewById(R.id.restriction);
            viewHolder.rating = rowView.findViewById(R.id.rating);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        viewHolder.name.setText(restaurant.getName());
//        viewHolder.restriction.setText(restaurant.getRestrictions().toString());
        viewHolder.restriction.setText("None");
//        viewHolder.rating.setText(restaurant.getRating().toString());
        viewHolder.rating.setText("5/5");
        return rowView;
    }

    static class ViewHolder {
        TextView name;
        TextView restriction;
        TextView rating;
    }


}
