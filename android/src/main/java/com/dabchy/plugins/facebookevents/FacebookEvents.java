package com.dabchy.plugins.facebookevents;

import android.content.Context;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import java.util.Iterator;

public class FacebookEvents {

    private static final String LOG_TAG = "FacebookEvents";

    private final Plugin plugin;
    private AppEventsLogger logger;

    public FacebookEvents(Plugin plugin) {
        this.plugin = plugin;
    }

    public synchronized void reset() {
        logger = null;
    }

    public void logEvent(String event, JSObject params) {
        AppEventsLogger logger = getLogger();
        if (logger == null) {
            Logger.warn(LOG_TAG, "Unable to log event \"" + event + "\" because no valid context is available for the Facebook SDK.");
            return;
        }

        if (params != null && params.length() > 0) {
            Bundle parameters = new Bundle();
            for (Iterator<String> it = params.keys(); it.hasNext();) {
                String key = it.next();
                String value = params.getString(key);
                if (value != null) {
                    parameters.putString(key, value);
                }
            }
            logger.logEvent(event, parameters);
        } else {
            logger.logEvent(event);
        }
    }

    private synchronized AppEventsLogger getLogger() {
        if (logger != null) {
            return logger;
        }

        Context context = plugin.getContext();
        if (context == null) {
            context = plugin.getActivity();
        }

        if (context == null) {
            return null;
        }

        Context appContext = context.getApplicationContext();
        if (appContext != null) {
            context = appContext;
        }

        logger = AppEventsLogger.newLogger(context);
        return logger;
    }
}
