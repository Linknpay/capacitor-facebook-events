package com.dabchy.plugins.facebookevents;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.getcapacitor.JSObject;

import android.os.Bundle;

public class FacebookEvents {

    private final AppEventsLogger logger;

    public FacebookEvents(Plugin plugin) {
        logger = AppEventsLogger.newLogger(plugin.getContext());
    }

    public void logEvent(String event, JSObject params) {
        if (params.length() > 0) {
            Bundle parameters = new Bundle();
            for (String key : params.keys()) {
                parameters.putString(key, params.getString(key));
            }
            logger.logEvent(event, parameters);
        } else {
            logger.logEvent(event);
        }
    }
}
