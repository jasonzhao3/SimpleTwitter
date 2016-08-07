package com.uber.yangz.twitter.models;

import com.uber.yangz.twitter.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Tweet {
    Long id;
    String createdAt;
    String body;
    User user;

    public Tweet() {
        // empty constructor needed by the Parceler library
    }

	// Parse model from JSON
	public Tweet(JSONObject object){
		try {
			this.body = object.getString("text");
            this.id = object.getLong("id");
            this.createdAt = object.getString("created_at");
            this.user = new User(object.getJSONObject("user"));
		} catch (JSONException e) {
			    e.printStackTrace();
		}
	}

    public Long getId() {
        return id;
    }

    public String getCreatedAt() {
        return Utils.getRelativeTimeAgo(createdAt);
    }

    public User getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

//    // Record Finders
//    public static Tweet byId(long id) {
//        return new Select().from(Tweet.class).where("id = ?", id).executeSingle();
//    }
//
//    public static List<Tweet> recentItems() {
//        return new Select().from(Tweet.class).orderBy("id DESC").limit("300").execute();
//    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweetsArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                tweetsArray.add(new Tweet(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return tweetsArray;
    }
}
