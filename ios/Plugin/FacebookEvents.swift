import Foundation
import FBSDKCoreKit

@objc public class FacebookEvents: NSObject {

    /// Configure l'état de suivi publicitaire
    @objc public func setAdvertiserTrackingEnabled(enabled: Bool) {
        FBSDKCoreKit.Settings.shared.isAdvertiserTrackingEnabled = enabled
    }

    /// Convertit un JSObject en paramètres pour le SDK Facebook
    private func convertJSObjectToFBParameters(_ jsObject: JSObject) -> [AppEvents.ParameterName: Any] {
        return jsObject.reduce(into: [AppEvents.ParameterName: Any]()) { result, item in
            let (key, value) = item
            if let stringValue = value as? String {
                result[AppEvents.ParameterName(key)] = stringValue
            }
        }
    }

    /// Enregistre un événement personnalisé
    @objc public func logEvent(event: String, params: JSObject) {
        let eventName = AppEvents.Name(event)

        if !params.isEmpty {
            let fbParameters = convertJSObjectToFBParameters(params)
            AppEvents.shared.logEvent(eventName, parameters: fbParameters)
        } else {
            AppEvents.shared.logEvent(eventName)
        }
    }
}
