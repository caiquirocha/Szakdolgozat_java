package demeter.gabor.tracker.models;


import android.location.Location;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MyLocation{

    private Double longitude;
    private Double latitude;
    private String userId;

    public MyLocation() {

    }

    public MyLocation(Location loc, String userId) {
        this.latitude =loc.getLatitude();
        this.longitude = loc.getLongitude();
        this.userId =userId;


    }

    public MyLocation(Double latitude, Double longitude, String userId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.userId = userId;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }



    @Override
    public String toString() {
        return "longitude: " + this.getLongitude()+ " latitude"+ getLatitude();
    }
}