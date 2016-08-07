# *SimpleTwitter*

**SimpleTwitter** is an android app that supports viewing a Twitter timeline and composing a new tweet.

Submitted by: **Yang Zhao**

## User Stories

The following **required** functionality is completed:

* [x] User can sign in to Twitter using OAuth login
* [x] User can view the tweets from their home timeline
    * [x] User should be displayed the username, name, and body for each tweet
    * [x] User should be displayed the relative timestamp for each tweet "8m", "7h"
    * [x] User can view more tweets as they scroll with infinite pagination
* [x] User can compose a new tweet
    * [x] User can click a “Compose” icon in the Action Bar on the top right
    * [x] User can then enter a new tweet and post this to twitter
    * [x] User is taken back to home timeline with new tweet visible in timeline

The following **optional** features are implemented:

* [x] "Compose" button is using a FloatingActionButton
* [x] Use Parcelable instead of Serializable using the popular Parceler library.
* [x] While composing a tweet, user can see a character counter with characters remaining for tweet out of 140
* [x] If the tweet content is invalid (aka empty or over 140 characters), tweet button is disabled and word count label is red.
* [x] User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh)
* [x] Links in tweets are clickable and will launch the web browser
* [x] Custom app icon with a black larry bird :p


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/jgutEFw.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).
