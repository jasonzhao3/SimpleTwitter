package com.uber.yangz.twitter.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.uber.yangz.twitter.R;
import com.uber.yangz.twitter.adaptors.TweetsArrayAdaptor;
import com.uber.yangz.twitter.models.Tweet;
import com.uber.yangz.twitter.utils.TwitterApp;
import com.uber.yangz.twitter.utils.TwitterClient;

import java.util.ArrayList;

public class BaseTimelineFragment extends Fragment {

    protected TwitterClient client;
    protected SwipeRefreshLayout swipeContainer;
    protected ArrayList<Tweet> tweetsArray;
    protected TweetsArrayAdaptor tweetsAdaptor;
    protected ListView lvTweets;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_base_timeline, container, false);
        lvTweets = (ListView) v.findViewById(R.id.lv_tweets);
        lvTweets.setAdapter(tweetsAdaptor);

        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tweetsArray = new ArrayList<>();
        tweetsAdaptor = new TweetsArrayAdaptor(getActivity(), tweetsArray);

        client = TwitterApp.getRestClient();
    }

    public void prependTweet(Tweet tweet) {
        tweetsAdaptor.insert(tweet, 0);
        tweetsAdaptor.notifyDataSetChanged();
    }
}
