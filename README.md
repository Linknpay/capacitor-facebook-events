# capacitor-facebook-events

Facebook events tracking in Capacitor applications

## Install

```bash
npm i --save capacitor-facebook-events
npx cap sync
```

## Quick setup

1) Install via npm

2) Add the following to your `ios/App/App/Info.plist` file (replace `YOUR_FB_APP_ID` and `YOUR_FB_CLIENT_TOKEN` with values from your Facebook app)

```xml
<key>FacebookAppID</key>
<string>YOUR_FB_APP_ID</string>
<key>FacebookClientToken</key>
<string>YOUR_FB_CLIENT_TOKEN</string>
<key>CFBundleURLTypes</key>
<array>
  <dict>
    <key>CFBundleURLSchemes</key>
    <array>
      <string>fbYOUR_FB_APP_ID</string>
    </array>
  </dict>
</array>
```

3) Add the following to your `android/app/src/main/res/values/strings.xml` file (replace `YOUR_FB_APP_ID` and `YOUR_FB_CLIENT_TOKEN` accordingly)

```xml
<resources>
  <!-- Existing strings ... -->
  <string name="facebook_app_id">YOUR_FB_APP_ID</string>
  <string name="facebook_client_token">YOUR_FB_CLIENT_TOKEN</string>
</resources>
```

4) Add the following to your `android/app/src/main/AndroidManifest.xml` file (as is, don't replace anything):

```xml
<manifest ...>
  <application ...>
    <!-- Existing data ... -->

    <!-- Facebook App ID and Client Token -->
    <meta-data
        android:name="com.facebook.sdk.ApplicationId"
        android:value="@string/facebook_app_id" />
    <meta-data
        android:name="com.facebook.sdk.ClientToken"
        android:value="@string/facebook_client_token" />
    <meta-data android:name="com.facebook.sdk.AutoInitEnabled" android:value="true"/>
    <meta-data android:name="com.facebook.sdk.AutoLogAppEventsEnabled" android:value="true"/>
    <meta-data android:name="com.facebook.sdk.AdvertiserIDCollectionEnabled" android:value="true"/>
  </application>
</manifest>
```

5) Add or update your `ios/App/App/AppDelegate.swift` with the following (as is, don't replace anything):

```swift
import FBSDKCoreKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
  var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Initialize the Facebook SDK (reads FacebookAppID from Info.plist)
        ApplicationDelegate.shared.application(application, didFinishLaunchingWithOptions: launchOptions)

        Settings.shared.isAutoLogAppEventsEnabled = true
        Settings.shared.isAdvertiserIDCollectionEnabled = true

        // Uncomment this to enable verbose output of Facebook SDK to XCode console
        /* Settings.shared.loggingBehaviors = Set([
            .appEvents, .networkRequests, .informational,
            .graphAPIDebugInfo, .graphAPIDebugWarning, .developerErrors
        ]) */

        return true
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
      // Sends the built-in "Activate App" event (shows in Test Events)
      AppEvents.shared.activateApp()
    }
}
```

6) Sync your project

```bash
npx cap sync ios
npx cap sync android
```

Consult the Facebook / Meta SDK documentation for more details.


## API

<docgen-index>

* [`setAdvertiserTrackingEnabled(...)`](#setadvertisertrackingenabled)
* [`logEvent(...)`](#logevent)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### setAdvertiserTrackingEnabled(...)

```typescript
setAdvertiserTrackingEnabled(options: { enabled: boolean; }) => Promise<void>
```

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ enabled: boolean; }</code> |

--------------------


### logEvent(...)

```typescript
logEvent(options: { event: string; params?: any; }) => Promise<void>
```

| Param         | Type                                          |
| ------------- | --------------------------------------------- |
| **`options`** | <code>{ event: string; params?: any; }</code> |

--------------------

</docgen-api>

## Example Usage

### Logging a Registration Event
To log a registration event, use the 'fb_mobile_complete_registration' event name:

```typescript
import { FacebookEvents } from 'capacitor-facebook-events';

// ...

FacebookEvents.logEvent({
    event: 'fb_mobile_complete_registration',
    params: {
// Additional parameters (optional)
    }
});
```

### Logging a Purchase Event
For logging a purchase event, use the 'fb_mobile_purchase' event name with relevant parameters:

```typescript
import { FacebookEvents } from 'capacitor-facebook-events';

// ...

FacebookEvents.logEvent({
    event: 'fb_mobile_purchase',
    params: {
        fb_content_id: 'item_id', // Item ID
        fb_content_type: 'product',
        fb_currency: 'currency_code',
        _valueToSum: amount // Purchase amount
    }
});
```

For a comprehensive list of events, refer to the [Facebook App Events API documentation](https://developers.facebook.com/docs/marketing-api/app-event-api/).
