package com.uber.yangz.twitter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uber.yangz.twitter.R;
import com.uber.yangz.twitter.fragments.HeaderFragment;
import com.uber.yangz.twitter.fragments.UserTimelineFragment;

public class ProfileActivity
        extends AppCompatActivity
        implements HeaderFragment.OnGetScreenNameListener, UserTimelineFragment.OnGetScreenNameListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @Override
    public String onGetScreenName() {
        return getIntent().getStringExtra("screenName");
    }
}
