package com.uber.yangz.twitter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.uber.yangz.twitter.models.Tweet;
import com.uber.yangz.twitter.utils.EndlessScrollListener;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class MentionsTimelineFragment extends BaseTimelineFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateMentionsTimeline(0);
            }
        });

        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                populateMentionsTimeline(page);
                return true;
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateMentionsTimeline(0);
    }

    private void populateMentionsTimeline(int page) {
        final int currPage = page;
        client.getMentionsTimeline(page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (currPage == 0) {
                    tweetsAdaptor.clear();
                }
                tweetsAdaptor.addAll(Tweet.fromJSONArray(response));
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Snackbar.make(lvTweets, throwable.getMessage(), Snackbar.LENGTH_LONG).show();
                swipeContainer.setRefreshing(false);
            }
        });
    }
}
