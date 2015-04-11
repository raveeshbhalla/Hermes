Hermes
====================
An easy way to add Google Cloud Messaging to your Android app


# Why?
I've found GCM to be a key component for any service that wants to provide an engaging UX. Once set up, GCM can be used not only to send push messages for product features, but also to re-engage users that haven't been active and even to [find out which of your users have uninstalled your app](http://retention.io).

At the same time, GCM registration isn't as straightforward as it should be. Not only do you need to write quite a bit of code, [there are several edge cases that you need to take care of](https://blog.pushbullet.com/2014/02/12/keeping-google-cloud-messaging-for-android-working-reliably-techincal-post/).

I decided to build Hermes as a way to minimize the time and effort required to add GCM to any application, with a focus on ensuring all the complexities surrounding re-registering and exponential backoffs are automatically handled.

# How?
Registering a user on GCM with Hermes is ridiculously simple:

        Hermes.register(context, GOOGLE_CLOUD_PROJECT_ID);

A [BroadcastReceiver must be extended](https://github.com/raveeshbhalla/Hermes/blob/master/hermesexample/src/main/java/in/raveesh/hermesexample/GcmBroadcastReceiver.java) as well, and a few lines added to the [AndroidManifest as shown in the example](https://github.com/raveeshbhalla/Hermes/blob/master/hermesexample/src/main/AndroidManifest.xml).

# Integration
Just add the following lines to your build.gradle
    repositories {
        maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
    }

    dependencies {
        compile 'in.raveesh:hermes:0.1.0-SNAPSHOT'
    }

# TODOs
- Add documentation
- Provide better step-by-step guidance
- Add re-registration related BroadcastReceivers

Hermes then takes care of the rest. There are a few customizations and callbacks being added, but that is not part of the initial scope of the project.

# Why Hermes?
[At Haptik](http://haptik.co) we use the names of various Greek Gods for naming various products internally. [Hermes is one of the Olympian Gods](http://en.wikipedia.org/wiki/Hermes), and acts as a messenger of the Gods.
