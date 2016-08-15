package com.uber.yangz.twitter.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.uber.yangz.twitter.R;
import com.uber.yangz.twitter.models.User;
import com.uber.yangz.twitter.utils.TwitterApp;
import com.uber.yangz.twitter.utils.TwitterClient;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeaderFragment extends Fragment {

    private TwitterClient client;
    private TextView tvScreenName;
    private TextView tvTagline;
    private TextView tvCountStat;
    private ImageView ivProfileImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_header, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        tvScreenName = (TextView) v.findViewById(R.id.tv_user_name);
        tvTagline = (TextView) v.findViewById(R.id.tv_tagline);
        tvCountStat = (TextView) v.findViewById(R.id.tv_count_stat);
        ivProfileImage = (ImageView) v.findViewById(R.id.iv_profile_image);

        OnGetScreenNameListener listener = (OnGetScreenNameListener)getContext();
        String screenName = listener.onGetScreenName();
        client.getUserInfo(screenName, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                User user = new User(response);

                tvScreenName.setText(user.getDisplayName());
                tvTagline.setText(user.getTagline());
                tvCountStat.setText(user.getCountStat());
                Picasso.with(getContext()).
                        load(user.getProfileImageUrl()).
                        transform(new RoundedCornersTransformation(5, 5)).
                        into(ivProfileImage);
            }
        });
    }

    public interface OnGetScreenNameListener {
        String onGetScreenName();
    }
}
