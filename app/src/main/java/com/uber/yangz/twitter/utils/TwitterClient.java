package com.uber.yangz.twitter.utils;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
	public static final String REST_URL = "https://api.twitter.com/1.1";
	public static final String REST_CONSUMER_KEY = "cpBH0lq37OxwbVDSpqIeia38f";
	public static final String REST_CONSUMER_SECRET = "m4ZnPhYqfoyAQFUeeegUQcFSq03ET9r6AMBZf9EDCPgM6aWKgk";
	public static final String REST_CALLBACK_URL = "oauth://cpsimpletwitter";

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	public void getHomeTimeline(int page, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/home_timeline.json");
        RequestParams params = new RequestParams();
        params.put("page", String.valueOf(page));
        client.get(apiUrl, params, handler);
	}

	public void getMentionsTimeline(int page, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");
        RequestParams params = new RequestParams();
        params.put("page", String.valueOf(page));
        client.get(apiUrl, params, handler);
	}

	public void getUserTimeline(int page, String screenName, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/user_timeline.json");
        RequestParams params = new RequestParams();
		params.put("screen_name", screenName);
        params.put("page", String.valueOf(page));
        client.get(apiUrl, params, handler);
	}

	public void getUserInfo(String screenName, AsyncHttpResponseHandler handler) {
	    String apiUrl = getApiUrl("users/show.json");
        RequestParams params = new RequestParams();
		params.put("screen_name", screenName);
		client.get(apiUrl, params, handler);
	}

    public void postTweet(String body, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", body);
        getClient().post(apiUrl, params, handler);
    }
}