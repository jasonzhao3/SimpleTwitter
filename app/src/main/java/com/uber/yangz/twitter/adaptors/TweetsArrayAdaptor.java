package com.uber.yangz.twitter.adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.uber.yangz.twitter.R;
import com.uber.yangz.twitter.activities.ProfileActivity;
import com.uber.yangz.twitter.models.Tweet;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class TweetsArrayAdaptor extends ArrayAdapter<Tweet> {

    // View lookup cache
    private static class ViewHolder {
        public ImageView ivAvatar;
        public TextView tvScreenname;
        public TextView tvBody;
    }

    public TweetsArrayAdaptor(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Tweet tweet = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tweet_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivAvatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            viewHolder.tvScreenname = (TextView) convertView.findViewById(R.id.tv_screen_name);
            viewHolder.tvBody = (TextView) convertView.findViewById(R.id.tv_body);
            viewHolder.ivAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), ProfileActivity.class);
                    i.putExtra("screenName", tweet.getUser().getScreenName());
                    getContext().startActivity(i);
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String displayName = tweet.getUser().getDisplayName() + " • " + tweet.getCreatedAt();
        viewHolder.tvScreenname.setText(displayName);
        viewHolder.tvBody.setText(tweet.getBody());
        Picasso.with(getContext()).
                load(tweet.getUser().getProfileImageUrl()).
                transform(new RoundedCornersTransformation(5, 5)).
                into(viewHolder.ivAvatar);

        return convertView;
    }
}
