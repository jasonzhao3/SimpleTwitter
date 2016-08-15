package com.uber.yangz.twitter.activities;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uber.yangz.twitter.R;
import com.uber.yangz.twitter.fragments.HeaderFragment;
import com.uber.yangz.twitter.fragments.UserTimelineFragment;

public class ProfileActivity
        extends AppCompatActivity
        implements HeaderFragment.OnGetScreenNameListener, UserTimelineFragment.OnGetScreenNameListener {

    private String screenName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(R.color.twitter_blue));

        screenName = getIntent().getStringExtra("screenName");
        setTitle(screenName);
    }

    @Override
    public String onGetScreenName() {
        return getIntent().getStringExtra("screenName");
    }
}
