package com.uber.yangz.twitter.models;

import com.uber.yangz.twitter.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



/*
 * This is a temporary, sample model that demonstrates the basic structure
 * of a SQLite persisted Model object. Check out the ActiveAndroid wiki for more details:
 * https://github.com/pardom/ActiveAndroid/wiki/Creating-your-database-model
 * 
 */
public class Tweet {
    private Long id;
    private String createdAt;
    private User user;
    private String body;

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
