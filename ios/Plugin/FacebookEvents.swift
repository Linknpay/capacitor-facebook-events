import Foundation
import Capacitor
import FBSDKCoreKit

@objc public class FacebookEvents: NSObject {

    /// Configure l'état de suivi publicitaire
    @objc public func setAdvertiserTrackingEnabled(enabled: Bool) {
        Settings.shared.isAdvertiserTrackingEnabled = enabled
    }

    /// Convertit un NSDictionary en paramètres pour le SDK Facebook
    private func convertNSDictionaryToFBParameters(_ dict: NSDictionary?) -> [AppEvents.ParameterName: Any] {
        var parameters: [AppEvents.ParameterName: Any] = [:]
        if let dict = dict {
            for (key, value) in dict {
                if let keyString = key as? String, let value = value as? String {
                    parameters[AppEvents.ParameterName(keyString)] = value
                }
            }
        }
        return parameters
    }

    /// Enregistre un événement personnalisé
    @objc public func logEvent(event: String, params: NSDictionary?) {
        let eventName = AppEvents.Name(event)

        if let params = params, params.count > 0 {
            let fbParameters = convertNSDictionaryToFBParameters(params)
            AppEvents.shared.logEvent(eventName, parameters: fbParameters)
        } else {
            AppEvents.shared.logEvent(eventName)
        }
    }
}
