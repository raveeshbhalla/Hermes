Hermes
====================
An easy way to add Google Cloud Messaging to your Android app


# Why?
I've found GCM to be a key component for any service that wants to provide an engaging UX. Once set up, GCM can be used not only to send push messages for product features, but also to re-engage users that haven't been active and even to [find out which of your users have uninstalled your app](http://retention.io).

At the same time, GCM registration isn't as straightforward as it should be. Not only do you need to write quite a bit of code, [there are several edge cases that you need to take care of](https://blog.pushbullet.com/2014/02/12/keeping-google-cloud-messaging-for-android-working-reliably-techincal-post/).

I decided to build Hermes as a way to minimize the time and effort required to add GCM to any application, with a focus on ensuring all the complexities surrounding re-registering and exponential backoffs are automatically handled.
