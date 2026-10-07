import Foundation
import Capacitor

@objc(FacebookEventsPlugin)
public class FacebookEventsPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "FacebookEventsPlugin"
    public let jsName = "FacebookEvents"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "setAdvertiserTrackingEnabled", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "logEvent", returnType: CAPPluginReturnPromise)
    ]

    private let implementation = FacebookEvents()

    @objc func setAdvertiserTrackingEnabled(_ call: CAPPluginCall) {
        guard let enabled = call.getBool("enabled") else {
            call.reject("Missing enabled argument")
            return
        }

        implementation.setAdvertiserTrackingEnabled(enabled: enabled)
        call.resolve()
    }

    @objc func logEvent(_ call: CAPPluginCall) {
        guard let event = call.getString("event") else {
            call.reject("Missing event argument")
            return
        }

        let params = call.getObject("params") ?? [:]
        implementation.logEvent(event: event, params: params)
        call.resolve()
    }
}
