package com.dabchy.plugins.facebookevents;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.JSObject;

@CapacitorPlugin(name = "FacebookEvents")
public class FacebookEventsPlugin extends Plugin {

    private final FacebookEvents facebookEvents = new FacebookEvents(this);

    @PluginMethod
    public void setAdvertiserTrackingEnabled(PluginCall call) {
        call.resolve();
    }

    @PluginMethod
    public void logEvent(PluginCall call) {
        String event = call.getString("event");
        JSObject params = call.getObject("params", new JSObject());
        facebookEvents.logEvent(event, params);
        call.resolve();
    }
}
