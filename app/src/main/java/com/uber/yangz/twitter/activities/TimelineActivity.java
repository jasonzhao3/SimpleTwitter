package com.uber.yangz.twitter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.uber.yangz.twitter.R;
import com.uber.yangz.twitter.adaptors.TweetsArrayAdaptor;
import com.uber.yangz.twitter.models.Tweet;
import com.uber.yangz.twitter.utils.EndlessScrollListener;
import com.uber.yangz.twitter.utils.TwitterApp;
import com.uber.yangz.twitter.utils.TwitterClient;

import org.json.JSONArray;
import org.parceler.Parcels;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private TwitterClient client;
    private ArrayList<Tweet> tweetsArray;
    private TweetsArrayAdaptor tweetsAdaptor;
    private ListView lvTweets;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvTweets = (ListView) findViewById(R.id.lv_tweets);
        tweetsArray = new ArrayList<>();
        tweetsAdaptor = new TweetsArrayAdaptor(this, tweetsArray);
        lvTweets.setAdapter(tweetsAdaptor);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_compose_tweet);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ComposeActivity.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        client = TwitterApp.getRestClient();
        populateHomeTimeline(0);

        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                populateHomeTimeline(page);
                return true;
            }
        });
    }

    private void populateHomeTimeline(int page) {
        client.getHomeTimeline(page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                tweetsAdaptor.addAll(Tweet.fromJSONArray(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            Tweet newTweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
            tweetsAdaptor.insert(newTweet, 0);
            tweetsAdaptor.notifyDataSetChanged();
        }
    }
}
