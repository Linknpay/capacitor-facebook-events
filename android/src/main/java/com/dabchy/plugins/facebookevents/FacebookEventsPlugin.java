package com.dabchy.plugins.facebookevents;

import android.app.Application;
import android.content.Context;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "FacebookEvents")
public class FacebookEventsPlugin extends Plugin {

    private static final String LOG_TAG = "FacebookEventsPlugin";

    private FacebookEvents facebookEvents;

    @Override
    public void load() {
        facebookEvents = new FacebookEvents(this);
        initializeFacebookSdk();
    }

    @Override
    protected void handleOnStart() {
        super.handleOnStart();
        Application application = resolveApplication();

        if (application == null) {
            Logger.warn(LOG_TAG, "Unable to activate Facebook App Events because the application reference is null.");
            return;
        }

        initializeFacebookSdk();
        AppEventsLogger.activateApp(application);
    }

    @Override
    protected void handleOnStop() {
        super.handleOnStop();
        Application application = resolveApplication();

        if (application != null) {
            AppEventsLogger.deactivateApp(application);
        }
    }

    @Override
    protected void handleOnDestroy() {
        super.handleOnDestroy();
        if (facebookEvents != null) {
            facebookEvents.reset();
        }
    }

    @PluginMethod
    public void setAdvertiserTrackingEnabled(PluginCall call) {
        Boolean enabled = call.getBoolean("enabled");

        if (enabled == null) {
            call.reject("Missing enabled argument");
            return;
        }

        initializeFacebookSdk();
        FacebookSdk.setAdvertiserIDCollectionEnabled(enabled);
        FacebookSdk.setAutoLogAppEventsEnabled(enabled);
        FacebookSdk.setAutoInitEnabled(enabled);
        call.resolve();
    }

    @PluginMethod
    public void logEvent(PluginCall call) {
        String event = call.getString("event");
        if (event == null || event.trim().isEmpty()) {
            call.reject("Missing event argument");
            return;
        }

        JSObject params = call.getObject("params", new JSObject());
        initializeFacebookSdk();

        if (facebookEvents == null) {
            facebookEvents = new FacebookEvents(this);
        }

        facebookEvents.logEvent(event, params);
        call.resolve();
    }

    private void initializeFacebookSdk() {
        Application application = resolveApplication();
        if (application == null) {
            Logger.warn(LOG_TAG, "Unable to initialize the Facebook SDK because the application reference is null.");
            return;
        }

        Context appContext = application.getApplicationContext();
        if (appContext == null) {
            Logger.warn(LOG_TAG, "Unable to initialize the Facebook SDK because the application context is null.");
            return;
        }

        synchronized (FacebookEventsPlugin.class) {
            if (!FacebookSdk.isInitialized()) {
                FacebookSdk.sdkInitialize(appContext);
            }

            FacebookSdk.setAutoInitEnabled(true);
            FacebookSdk.setAutoLogAppEventsEnabled(true);
            FacebookSdk.setAdvertiserIDCollectionEnabled(true);
        }
    }

    @Nullable
    private Application resolveApplication() {
        AppCompatActivity activity = getActivity();
        if (activity != null) {
            return activity.getApplication();
        }

        Context context = getContext();
        if (context != null) {
            Context appContext = context.getApplicationContext();
            if (appContext instanceof Application) {
                return (Application) appContext;
            }
        }

        return null;
    }
}
