import Foundation
import FBSDKCoreKit

@objc public class FacebookEvents: NSObject {

    /// Sets the advertiser tracking state on the Facebook SDK.
    @objc public func setAdvertiserTrackingEnabled(enabled: Bool) {
        Settings.shared.isAdvertiserTrackingEnabled = enabled
    }

    /// Logs a custom event, forwarding any parameters to the Facebook SDK.
    public func logEvent(event: String, params: [String: Any]) {
        let eventName = AppEvents.Name(event)
        let parameters = convertToFBParameters(params)

        if parameters.isEmpty {
            AppEvents.shared.logEvent(eventName)
        } else {
            AppEvents.shared.logEvent(eventName, parameters: parameters)
        }
    }

    /// Converts JS parameters into Facebook SDK parameters.
    /// Strings and numbers (including booleans) are preserved; anything else is stringified.
    /// This mirrors the Android implementation so `_valueToSum` and similar numeric
    /// parameters are not dropped.
    func convertToFBParameters(_ dict: [String: Any]) -> [AppEvents.ParameterName: Any] {
        var parameters: [AppEvents.ParameterName: Any] = [:]
        for (key, value) in dict {
            let name = AppEvents.ParameterName(key)
            switch value {
            case is NSNull:
                continue
            case let string as String:
                parameters[name] = string
            case let number as NSNumber:
                parameters[name] = number
            default:
                parameters[name] = String(describing: value)
            }
        }
        return parameters
    }
}
