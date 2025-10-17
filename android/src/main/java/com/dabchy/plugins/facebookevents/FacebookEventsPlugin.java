package com.dabchy.plugins.facebookevents;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "FacebookEvents")
public class FacebookEventsPlugin extends Plugin {

    private FacebookEvents facebookEvents;

    @Override
    public void load() {
        facebookEvents = new FacebookEvents(this);
    }

    @Override
    protected void handleOnStart() {
        super.handleOnStart();
        AppEventsLogger.activateApp(getActivity().getApplication());
    }

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
