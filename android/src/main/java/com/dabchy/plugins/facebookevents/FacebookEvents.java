package com.dabchy.plugins.facebookevents;

import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import java.util.Iterator;

public class FacebookEvents {

    private final AppEventsLogger logger;

    public FacebookEvents(Plugin plugin) {
        logger = AppEventsLogger.newLogger(plugin.getContext());
    }

    public void logEvent(String event, JSObject params) {
        if (params != null && params.length() > 0) {
            Bundle parameters = new Bundle();
            for (Iterator<String> it = params.keys(); it.hasNext();) {
                String key = it.next();
                parameters.putString(key, params.getString(key));
            }
            logger.logEvent(event, parameters);
        } else {
            // Log the event without parameters if params is null or empty
            logger.logEvent(event);
        }
    }
}
