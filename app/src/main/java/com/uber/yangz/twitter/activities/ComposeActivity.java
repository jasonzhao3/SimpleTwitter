package com.uber.yangz.twitter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uber.yangz.twitter.R;
import com.uber.yangz.twitter.utils.TwitterApp;
import com.uber.yangz.twitter.utils.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    private TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        client = TwitterApp.getRestClient();
        Button btnTweet = (Button) findViewById(R.id.btn_submit_tweet);
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etName = (EditText) findViewById(R.id.et_tweet_editor);
                String tweetBody = etName.getText().toString();

                client.postTweet(tweetBody, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Intent data = new Intent();
                        // Pass relevant data back as a result
                        data.putExtra("tweet", "asdf");
                        data.putExtra("code", 200);
                        setResult(RESULT_OK, data); // set result code and bundle data for response
                        finish();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        Toast.makeText(getApplicationContext(), "Failed to post tweet", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
