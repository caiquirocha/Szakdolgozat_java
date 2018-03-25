package demeter.gabor.tracker.adapters;

import android.app.Application;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import demeter.gabor.tracker.R;
import demeter.gabor.tracker.models.MyLocation;
import demeter.gabor.tracker.models.User;

/**
 * Created by demet on 2018. 03. 24..
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUserName;
        public TextView tvUserEmail;
        public TextView tvLongitude;
        public TextView tvLangitude;
        public TextView tvAddress;
        public ImageView userProfileImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            tvUserEmail = (TextView) itemView.findViewById(R.id.tvUserEmail);
            tvLongitude = (TextView) itemView.findViewById(R.id.tvLongitude);
            tvLangitude = (TextView) itemView.findViewById(R.id.tvLangitude);
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);
            userProfileImage = (ImageView) itemView.findViewById(R.id.userProfileImage);
        }
    }

    private Context context;
    private List<User> userList;
    private Map<String, User> userMap;
    private int lastPosition = -1;

    public UserAdapter(Context context) {
        this.context = context;
        this.userList = new ArrayList<>();
        this.userMap = new HashMap<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        User tempUser = userList.get(position);
        viewHolder.tvUserName.setText(tempUser.getUsername());
        viewHolder.tvUserEmail.setText(tempUser.getEmail());

        if(tempUser.getLastLocation() == null){
            viewHolder.tvLongitude.setText("Nem Ismert");
            viewHolder.tvLangitude.setText("Nem ismert");
            viewHolder.tvAddress.setText("Nem ismert");
        }else{
            Geocoder gc = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = null;

            try {
                addresses = gc.getFromLocation(tempUser.getLastLocation().getLatitude(),tempUser.getLastLocation().getLongitude(), 10);
            } catch (IOException e) {
                e.printStackTrace();
            }

            viewHolder.tvLongitude.setText(String.valueOf(tempUser.getLastLocation().getLongitude()));
            viewHolder.tvLangitude.setText(String.valueOf(tempUser.getLastLocation().getLatitude()));
           // String apperingText = addresses.get(0).getCountryName()+ " " +addresses.get(0).getPostalCode()+ " " + addresses.get(0).getAddressLine(0);
            viewHolder.tvAddress.setText("TODO");

        }

/*
      if (!TextUtils.isEmpty(tempUser.getProfileImageURL())) {
            Glide.with(context).load(tempUser.getProfileImageURL()).into(viewHolder.userProfileImage);
            viewHolder.userProfileImage.setVisibility(View.VISIBLE);
        } else {
            viewHolder.userProfileImage.setVisibility(View.GONE);
        }

        setAnimation(viewHolder.itemView, position);
*/
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void addUser(User user, String key) {
        userList.add(user);
        userMap.put(key,user);
        notifyDataSetChanged();
    }
    public void updateLastLocation(MyLocation loc) {
        Log.d("updateLocationUI",loc.toString());
       // userMap.get(setLastLocations(loc);
        notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context,
                    android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}