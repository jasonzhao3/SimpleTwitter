package com.uber.yangz.twitter.models;


import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {
//    @Column(name = "screenName")
    String screenName;

//    @Column(name = "handle")
    String name;
    String profileImageUrl;
    String tagline;
    Integer followersCount;
    Integer followingsCount;
    Long id;

    public User() {
        // empty constructor needed by the Parceler library
    }

    public User(JSONObject object) {
        try {
            this.id = object.getLong("id");
            this.screenName = object.getString("screen_name");
            this.name = object.getString("name");
            this.profileImageUrl = object.getString("profile_image_url");
            this.tagline = object.getString("description");
            this.followersCount = object.getInt("followers_count");
            this.followingsCount = object.getInt("friends_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public String getDisplayName() {
        return name + " @" + screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public long getId() {
        return id;
    }

    public String getTagline() {
        return tagline;
    }

    public String getCountStat() {
        return String.format("%s Followers  %s Following", followersCount, followingsCount);
    }

}
