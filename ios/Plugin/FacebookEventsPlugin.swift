import Foundation
import Capacitor

@objc(FacebookEventsPlugin)
public class FacebookEventsPlugin: CAPPlugin {
    private let facebookEvents = FacebookEvents()

    @objc func setAdvertiserTrackingEnabled(_ call: CAPPluginCall) {
        guard let enabled = call.getBool("enabled") else {
            call.reject("Missing enabled argument")
            return
        }

        facebookEvents.setAdvertiserTrackingEnabled(enabled: enabled)
        call.resolve()
    }

    @objc func logEvent(_ call: CAPPluginCall) {
        guard let event = call.getString("event") else {
            call.reject("Missing event argument")
            return
        }

        let params = call.getObject("params") ?? [:]
        facebookEvents.logEvent(event: event, params: params as NSDictionary)
        call.resolve()
    }
}
