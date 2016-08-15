package com.uber.yangz.twitter.adaptors;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.uber.yangz.twitter.fragments.HomeTimelineFragment;
import com.uber.yangz.twitter.fragments.MentionsTimelineFragment;

public class TimelineFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    private String tableTitles[] = new String[] { "Home", "Mentions" };

    public TimelineFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return tableTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeTimelineFragment();
            case 1:
                return new MentionsTimelineFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tableTitles[position];
    }
}
