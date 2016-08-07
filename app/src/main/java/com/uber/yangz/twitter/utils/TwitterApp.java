package com.uber.yangz.twitter.utils;

import android.content.Context;


public class TwitterApp extends com.activeandroid.app.Application {
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		TwitterApp.context = this;
	}

	public static TwitterClient getRestClient() {
		return (TwitterClient) TwitterClient.getInstance(TwitterClient.class, TwitterApp.context);
	}
}