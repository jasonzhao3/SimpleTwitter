package com.uber.yangz.twitter.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.uber.yangz.twitter.R;
import com.uber.yangz.twitter.models.Tweet;
import com.uber.yangz.twitter.utils.TwitterApp;
import com.uber.yangz.twitter.utils.TwitterClient;

import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    private TwitterClient client;
    private TextView tvWordCount;
    private Button btnSubmitTweet;

    private static int TWEET_WC_LIMIT = 140;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        getSupportActionBar().hide();

        client = TwitterApp.getRestClient();
        btnSubmitTweet = (Button) findViewById(R.id.btn_submit_tweet);

        btnSubmitTweet.setEnabled(false);
        btnSubmitTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            EditText etName = (EditText) findViewById(R.id.et_tweet_editor);
            String tweetBody = etName.getText().toString();

            client.postTweet(tweetBody, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Intent data = new Intent();
                    Tweet tweet = new Tweet(response);
                    data.putExtra("tweet", Parcels.wrap(tweet));
                    data.putExtra("code", 200);
                    setResult(RESULT_OK, data);
                    finish();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Toast.makeText(getApplicationContext(), "Failed to post tweet", Toast.LENGTH_LONG).show();
                }
            });
            }
        });

        tvWordCount = (TextView) findViewById(R.id.tv_wc_hint);
        tvWordCount.setText(String.valueOf(TWEET_WC_LIMIT));
        EditText etTweetEditor = (EditText) findViewById(R.id.et_tweet_editor);
        etTweetEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int wcLeft = TWEET_WC_LIMIT - charSequence.length();
                tvWordCount.setText(String.valueOf(wcLeft));

                if (wcLeft < 0) {
                    tvWordCount.setTextColor(Color.RED);
                } else {
                    tvWordCount.setTextColor(Color.GRAY);
                }

                if (wcLeft < 0 || wcLeft == TWEET_WC_LIMIT) {
                    btnSubmitTweet.setEnabled(false);
                } else {
                    btnSubmitTweet.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
