package com.uber.yangz.twitter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.uber.yangz.twitter.R;
import com.uber.yangz.twitter.adaptors.TimelineFragmentPagerAdapter;
import com.uber.yangz.twitter.fragments.HomeTimelineFragment;
import com.uber.yangz.twitter.models.Tweet;

import org.parceler.Parcels;


public class TimelineActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 20;
    private TimelineFragmentPagerAdapter timelinePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_timeline);
        timelinePageAdapter = new TimelineFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(timelinePageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_timeline);
        tabLayout.setupWithViewPager(viewPager);

        // floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_compose_tweet);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ComposeActivity.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            Tweet newTweet = Parcels.unwrap(data.getParcelableExtra("tweet"));

            HomeTimelineFragment homeTimelineFragment = (HomeTimelineFragment) timelinePageAdapter.getRegisteredFragment(0);
            homeTimelineFragment.prependTweet(newTweet);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    public void onProfileView(MenuItem mi) {
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
        i.putExtra("screenName", "yang_bot2");
        startActivity(i);
    }
}
